package logging;

import basic.ObjectAttributeHelper;
import basic.Reject;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.Configuration;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

class Log4j2LogService implements LogService  {

    private static final Map<LogSeverity, Level> LEVELS_MAP;

    static {
        LEVELS_MAP = new HashMap<>();
        LEVELS_MAP.put(LogSeverity.DEBUG, Level.DEBUG);
        LEVELS_MAP.put(LogSeverity.INFO, Level.INFO);
        LEVELS_MAP.put(LogSeverity.WARNING, Level.WARN);
        LEVELS_MAP.put(LogSeverity.ERROR, Level.ERROR);
        LEVELS_MAP.put(LogSeverity.FATAL, Level.FATAL);
        LEVELS_MAP.put(LogSeverity.OFF, Level.OFF);
    }

    private static LogSeverity getSeverity(Level l) {
        return LEVELS_MAP.entrySet().stream().
                filter(entry -> entry.getValue().equals(l)).
                map(Map.Entry::getKey).findAny().
                orElseThrow(() -> Reject.developmentError("cannot find severity for level: " + l));
    }

    private Logger logger;

    Log4j2LogService(String name) {
        logger = name == null ? LogManager.getRootLogger() : LogManager.getLogger(name);

        if(name == null) {
            org.apache.logging.log4j.core.Logger coreLogger = coreLogger();
            if(coreLogger != null) {
                LoggerContext loggerContext = coreLogger.getContext();
                Configuration configuration = loggerContext.getConfiguration();
                logger.info("Diese Log4j2-Konfig wird genutzt: " + configuration.getConfigurationSource().getFile());
                logger.info(String.format("Log4j2-Konfig-Level: ROOT(%s) - Level->%s", logger.getClass().getName(), logger.getLevel()));
            }
        }
    }

    @Override
    public void setLogSeverity(LogSeverity level) {
        ObjectAttributeHelper.consumeIfNotNull(coreLogger(), logg -> logg.setLevel(LEVELS_MAP.get(level)));
    }

    @Override
    public LogSeverity getLogSeverity() {
        return getSeverity(logger.getLevel());
    }

    @Override
    public void log(LogSeverity severity, Supplier<String> message, Throwable reason) {
        logger.log(LEVELS_MAP.get(severity), message.get(), reason);
    }

    @Override
    public void putPropertyToLogConfigFileContext(String key, String obj) {
        ThreadContext.put(key, obj);
    }

    @Override
    public void removePropertyFromLogConfigFileContext(String key) {
        ThreadContext.remove(key);
    }

    private org.apache.logging.log4j.core.Logger coreLogger() {
        return logger instanceof org.apache.logging.log4j.core.Logger ? (org.apache.logging.log4j.core.Logger)logger : null;
    }

    @Override
    public void shutdown() {
        LogManager.shutdown(ObjectAttributeHelper.ifNotNull(coreLogger(), org.apache.logging.log4j.core.Logger::getContext));
    }
}
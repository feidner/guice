package logging;

import org.apache.commons.lang3.ArrayUtils;
import org.jetbrains.annotations.NotNull;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

public class LogManager {

    private static final String ROOT = "ROOT";
    public static final String MDC_SUBJECT_ENV = "MDC_SUBJECT_ENV";


    /**
     * The current log service.
     */
    private static Map<String, LogService> log = new HashMap<>();
    private static boolean single = false;

    static {
        Log4j2LogService logService = create(null);
        log.put(ROOT, logService);
    }

    @NotNull
    public static LogService getLogger() {
        return log.get(ROOT);
    }

    public static LogService getLogger(String name) {
        LogService logService;
        if(single) {
           logService = log.get(ROOT);
        } else {
            log.computeIfAbsent(name, LogManager::create);
            logService = log.get(name);
        }
        return logService;
    }

    private static Log4j2LogService create(String name) {
        return new Log4j2LogService(name);
    }

    static void setLogger(String name, LogService logService) {
        log.put(name == null ? ROOT : name, logService);
    }

    /**
     * Diese Methode blieb noch uebrig bei der Abschaffung von solchen Methoden: LogService.log(LogSeverity logSeverity, String message, Object[] args)
     * Hier kann man drueber diskutieren ob man solche Methoden mit Parameter "Object[] args" im LogService haben will?
     * Am besten waere es die Aaufrufe zu eliminieren indem man dort direkt MessageFormat.format(...) nutzt.
     */
    public static String getActualMessage(String message, Object ... args) {
        return ArrayUtils.isEmpty(args) ? message : MessageFormat.format(message, args);
    }

    /**
     * hfe: GreatSetupPersistence und GreatSetupPersistenceReporting sollen ALLES in eine eigene Datei und eine gemeinsame Datei loggen.
     * Sehr wahrscheinlich kann man das auch ueber die log4j2.xml steuern, ich habe aber noch keine Idee wie.
     */
    public static void useOnlyThisLogger(String singleLogger, Runnable runnable) {
        Log4j2LogService logService = create(singleLogger);
        LogService root = log.get(ROOT);
        log.put(ROOT, logService);
        LogManager.single = true;
        try {
            runnable.run();
        } finally {
            LogManager.single = false;
            log.put(ROOT, root);
        }
    }
}
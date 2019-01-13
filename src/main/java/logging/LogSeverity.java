package logging;

import java.util.Arrays;
import java.util.List;

public enum LogSeverity {
    DEBUG, INFO, WARNING, ERROR, FATAL, OFF;

    /**
    * Severity levels. Severity levels are ordered
    * like the following:
    * DEBUG < INFO < WARNING < ERROR < FATAL
    */

    private static final List<LogSeverity> ORDER = Arrays.asList(LogSeverity.values());
    public static final LogSeverity DEFAULT_SEVERITY = INFO;

    public static LogSeverity getActualSeverityLevel(LogSeverity level) {
        if (level.isSmaller(DEBUG)) {
            return DEBUG;
        }
        if (level.isBigger(FATAL)) {
            return FATAL;
        }
        return level;
    }

    public boolean isSmaller(LogSeverity level) {
        return ORDER.indexOf(this) < ORDER.indexOf(level);
    }

    public boolean isBigger(LogSeverity level) {
        return ORDER.indexOf(this) > ORDER.indexOf(level);
    }

    public boolean isSmallerOrEqual(LogSeverity level) {
        return ORDER.indexOf(this) <= ORDER.indexOf(level);
    }

    public boolean isBiggerOrEqual(LogSeverity level) {
        return ORDER.indexOf(this) >= ORDER.indexOf(level);
    }
}

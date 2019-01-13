package logging;

import java.util.function.Supplier;

public interface LogService {

	void setLogSeverity(LogSeverity l);

	LogSeverity getLogSeverity();

	// #################################

	void log(LogSeverity severity, Supplier<String> message, Throwable reason);

	// ###############

	default void fatal(String message) {
		fatal(message, null);
	}

	default void fatal(String message, Throwable reason) {
		log(LogSeverity.FATAL, () -> message, reason);
	}

	// ###############

	default void warn(String message) {
		warn(message, null);
	}

	default void warn(String message, Throwable reason) {
		log(LogSeverity.WARNING, () -> message, reason);
	}


	// ###############

	default void info(String message) {
		info(message, null);
	}

	default void info(String message, Throwable reason) {
		log(LogSeverity.INFO, () -> message, reason);
	}

	// ###############

	default void error(String message) {
		error(message, null);
	}

	default void error(String message, Throwable reason) {
		log(LogSeverity.ERROR, () -> message, reason);
	}

	// ###############

	default void debug(String message) {
		debug(message, null);
	}

	default void debug(String message, Throwable reason) {
		log(LogSeverity.DEBUG, () -> message, reason);
	}

	// ###############

	/*
	 * Hiermit lassen sich Properties setzen, die in der Konfigurationsdatei fuer das Logging verwendet werden koennen.
	 *
	 * In MDCLoggerManager wird die IP-Adresse mit dieser Methode gesetzt. Der key wird in der Datei ui-ulc-server/src/main/resources/log4j2.xml verwendet.
	 */
	default void putPropertyToLogConfigFileContext(String key, String value) {
	}

	default void removePropertyFromLogConfigFileContext(String key) {

	}

	default void shutdown() {

	}
}

package logging;

import basic.ExceptionRunnable;

public class LogManagerTest {

    public static void setLogger(String name, LogService logger, ExceptionRunnable runnable) throws Exception {
        LogService logService = LogManager.getLogger(name);
        LogManager.setLogger(name, logger);
        try {
            runnable.run();
        } finally {
            LogManager.setLogger(name, logService);
        }

    }
}
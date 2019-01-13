package logging;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Log4j2LogServiceTest {

    @Test
    void logSeverity_ChangeLogSeverity() {

        assertEquals(LogSeverity.INFO, LogManager.getLogger().getLogSeverity());
        LogManager.getLogger().setLogSeverity(LogSeverity.DEBUG);
        assertEquals(LogSeverity.DEBUG, LogManager.getLogger().getLogSeverity());
        LogManager.getLogger().setLogSeverity(LogSeverity.ERROR);
        assertEquals(LogSeverity.ERROR, LogManager.getLogger().getLogSeverity());

        LogManager.getLogger().setLogSeverity(LogSeverity.INFO);

        assertEquals(LogSeverity.INFO, LogManager.getLogger("great").getLogSeverity());
        LogManager.getLogger("great").setLogSeverity(LogSeverity.DEBUG);
        assertEquals(LogSeverity.DEBUG, LogManager.getLogger("great").getLogSeverity());

        assertEquals(LogSeverity.INFO, LogManager.getLogger().getLogSeverity());
    }

    @Test
    void logThrowable_GiveNullAsThrowable_ThenNoNPE() {
        LogManager.getLogger().info("huhu", null);
    }

}
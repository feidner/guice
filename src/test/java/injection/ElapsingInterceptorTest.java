package injection;

import com.google.inject.Guice;
import com.google.inject.Injector;
import logging.LogManager;
import org.junit.jupiter.api.Test;

class ElapsingInterceptorTest {

    @Test
    void elapsing_PrintMethodExecutionTime() {
        Injector injector = Guice.createInjector(new ApplicationModule());
        Hfe hfe = injector.getInstance(ElapsingInterceptorTest.Hfe.class);
        hfe.tuetwas();
    }

    public static class Hfe {
        @Elapsing
        public void tuetwas() {
            for(int i = 0; i <1000; i++) {
                LogManager.getLogger().info("schnell");
            }
        }
    }

}
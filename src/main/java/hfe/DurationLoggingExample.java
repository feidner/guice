package hfe;

import com.google.inject.Guice;
import com.google.inject.Injector;
import injection.ApplicationModule;
import injection.Elapsing;
import logging.LogManager;

public class DurationLoggingExample {

    @Elapsing
    public void logme() {
        for (int i = 0; i < 10000; i++) {
            LogManager.getLogger(getClass().getSimpleName()).info("bringts nicht");
        }
    }

    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new ApplicationModule());
        DurationLoggingExample example = injector.getInstance(DurationLoggingExample.class);
        example.logme();
    }
}

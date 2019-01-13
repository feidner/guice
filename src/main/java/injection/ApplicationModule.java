package injection;

import com.google.inject.AbstractModule;
import com.google.inject.matcher.Matchers;

public class ApplicationModule extends AbstractModule {
    @Override
    protected void configure() {
        bindInterceptor(Matchers.any(), Matchers.annotatedWith(Elapsing.class), new ElapsingInterceptor());
    }
}

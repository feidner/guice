package injection;

import basic.TimeService;
import logging.LogManager;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class ElapsingInterceptor implements MethodInterceptor {
        public Object invoke(MethodInvocation invocation) throws Throwable {
            return TimeService.duration(invocation::proceed, duration ->
                    LogManager.getLogger(invocation.getMethod().getDeclaringClass().getSimpleName()).
                            info(String.format("Methode '%s' brauchte %s millis", invocation.getMethod().getName(), duration)));
        }
}

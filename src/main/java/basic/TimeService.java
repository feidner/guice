package basic;


import java.time.Duration;
import java.time.LocalTime;
import java.util.function.Consumer;

public class TimeService {


    public static <T> T durationLog(ThrowableSupplier<T> runnable, Consumer<Long> duration) throws Throwable {
        LocalTime initialTime = LocalTime.now();
        T obj = runnable.get();
        duration.accept(Duration.between(initialTime, LocalTime.now()).toMillis());
        return obj;
    }

    public static <T> T duration(ThrowableSupplier<T> supplier, Consumer<Long> duration) {
        try {
            return durationLog(supplier, duration);
        } catch (Throwable ex) {
            // hfe: Exception ist nicht geplant
            throw Reject.developmentError(ex.getMessage(), ex);
        }
    }
}

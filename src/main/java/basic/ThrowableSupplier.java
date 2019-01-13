package basic;

@FunctionalInterface
public interface ThrowableSupplier<T> {
    T get() throws Throwable;
}

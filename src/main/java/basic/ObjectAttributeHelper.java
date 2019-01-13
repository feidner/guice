package basic;

import org.apache.commons.lang3.StringUtils;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;

public class ObjectAttributeHelper {
    private static final String BOOLEAN_TRUE = "1";
    private static final String BOOLEAN_FALSE = "0";

    private ObjectAttributeHelper() {

    }

    public static <T> void consumeIfNotNull(T obj, Consumer<T> consumer) {
        if(obj != null) {
            consumer.accept(obj);
        }
    }

    public static <T, R> R ifNotNull(T obj, Function<T, R> consumer) {
        return ifNotNull(obj, consumer, null);
    }

    public static <T, R> R ifNotNull(T obj, Function<T, R> consumer, R ifNullValue) {
        if(obj != null) {
            return consumer.apply(obj);
        }
        return ifNullValue;
    }

    public static <T> T valueOrDefault(T obj, T defaultObj)    {
        return Objects.isNull(obj) ? defaultObj : obj;
    }

    public static Boolean asBoolean(String stringRepresentation)    {
        if (StringUtils.isEmpty(stringRepresentation)) return null;
        return BOOLEAN_TRUE.equals(stringRepresentation);
    }

    public static String asBooleanString(Boolean boolValue)    {
        if (boolValue == null) return null;
        return boolValue ? BOOLEAN_TRUE : BOOLEAN_FALSE;
    }
}

package basic;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Map;

/**
 * This class provides some services that allow you to check for
 * - preconditions
 * - postconditions
 * - invariants
 */
public class Reject {
    private static final BigDecimal ZERO = new BigDecimal("0");

    public static void ifTrue(boolean condition)	{
		ifTrue("Condition should not be met", condition);
	}
	
	public static void ifTrue(String message, boolean condition)	{
		if (condition)	{
			throwRejectedException(message);
		}
	}
	
	public static void ifFalse(boolean condition)	{
		ifFalse("Condition should be met", condition);
	}
	
	public static void ifFalse(String message, boolean condition)	{
		if (!condition)	{
			throwRejectedException(message);
		}
	}
	
	public static void ifNull(Object o)	{
		ifNull("Reference should not be null", o);
	}
	
	public static void ifNull(String message, Object o)	{
		if (o == null)	{
			throwRejectedException(message);
		}
	}
	
	public static void ifNotNull(String message, Object o)	{
		if (o != null)	{
			throwRejectedException(message);
		}
	}
	
	public static void ifNotNull(Object o)	{
		ifNotNull("Reference is expected to be null", o);
	}
	
	public static void ifEmpty(String s)	{
		ifEmpty("String should not be empty", s);
	}
	
    public static void ifEmpty(String msg, String s)	{
        if (s == null || s.trim().length() == 0)	{
            throwRejectedException(msg);
        }
    }

	public static void ifNotEmpty(String s)	{
		ifNotEmpty("String should be empty", s);
	}
	
    public static void ifNotEmpty(String msg, String s)	{
        if (s != null && s.trim().length() > 0)	{
            throwRejectedException(msg);
        }
    }

	public static void ifEmpty(Collection<?> c)	{
		ifEmpty("Collection should not be empty", c);
	}
	
    public static void ifEmpty(String msg, Collection<?> c)	{
        if (c == null || c.isEmpty())	{
            throwRejectedException(msg);
        }
    }

	public static void ifEmpty(String msg, Map<?,?> c)	{
		if (c == null || c.isEmpty())	{
			throwRejectedException(msg);
		}
	}

	public static void ifNotSized(Collection<?> c, int size)	{
		ifNotSized("Collection size (" + getActualSize(c) + ") does not match the expected size (" + size + ")", c, size);
	}

    private static int getActualSize(Collection<?> c) {
        if (c == null)	{
            return 0;
        } else	{
            return c.size();
        }
    }

    public static void ifNotSized(String msg, Collection<?> c, int size)	{
        int actualSize = getActualSize(c);
        if (size != actualSize) throwRejectedException(msg);
    }

	public static void ifNotEmpty(Collection<?> c)	{
		ifNotEmpty("Collection should be empty", c);
	}

	public static void ifNotEmpty(String msg, Map<?,?> c)	{
		if (c != null && !c.isEmpty())	{
			throwRejectedException(msg);
		}
	}
	
    public static void ifNotEmpty(String msg, Collection<?> c)	{
        if (c != null && !c.isEmpty())	{
            throwRejectedException(msg);
        }
    }

	public static void ifEqual(Object one, Object two)	{
		ifEqual("Objects should not be equal", one, two);
	}
	
    public static void ifEqual(String msg, Object one, Object two)	{
        if (isEqual(one, two))	{
            throwRejectedException(msg);
        }
    }

	public static void ifNotEqual(Object one, Object two)	{
		ifNotEqual("Objects should be equal", one, two);
	}
	
    public static void ifNotEqual(String msg, Object one, Object two)	{
        if (!isEqual(one, two))	{
            throwRejectedException(msg);
        }
    }

	public static void ifNegative(int value)	{
		ifNegative("Value is not allowed to be negative", value);
	}
	
    public static void ifNegative(String msg, int value)	{
        if (value < 0) throwRejectedException(msg);
    }

    public static void ifNegative(String msg, BigDecimal value)	{
        if (value != null && ZERO.compareTo(value) > 0) throwRejectedException(msg);
    }

	private static boolean isEqual(Object one, Object two)	{
		if (one == null)	{
			return two == null;
		} else	{
			return two != null && one.equals(two);
		}
	}
		
	private static void throwRejectedException(String msg)	{
		throw developmentError(msg);
	}

	public static CodingErrorException developmentError(String s, Throwable e) {
		return new CodingErrorException(s, e);
	}

	public static CodingErrorException developmentError(String s) {
		return developmentError(s, null);
	}
}

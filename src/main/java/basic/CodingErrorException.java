package basic;

/**
 * These exceptions are thrown whenever a coding error is detected
 * at runtime. Coding errors might be:
 * - not meeting defined preconditions
 * - not meeting defined postconditions
 * - not meeting invariants
 * ...
 */

public class CodingErrorException extends RuntimeException	{

	static final long serialVersionUID = 0L;

	CodingErrorException(String description, Throwable e) {
		super(description, e);
	}
}
package org.kuali.common.util;

public class ObjectUtils {

	/**
	 * <p>
	 * This method returns <code>true</code> if the <code>toString()</code> methods on both objects return matching strings AND both objects are the exact same runtime type.
	 * </p>
	 * 
	 * <p>
	 * Returns <code>true</code> immediately if <code>main==other</code> (ie they are the same object).
	 * </p>
	 * 
	 * <p>
	 * Returns <code>false</code> immediately if <code>other==null</code> or is a different runtime type than <code>main</code>.
	 * </p>
	 * 
	 * <p>
	 * If neither one is <code>null</code>, and both are the exact same runtime type, then compare the <code>toString()</code> methods
	 * </p>
	 * 
	 * @param main
	 *            The object <code>other</code> is being compared to.
	 * @param other
	 *            The object being examined for equality with <code>main</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>main</cod> is <code>null</code> or <code>main.toString()</code> returns <code>null</code>
	 */
	public static boolean equalsByToString(Object main, Object other) {

		// Main can't be null
		if (main == null) {
			throw new NullPointerException("main is null");
		}

		// They are the same object
		if (main == other) {
			return true;
		}

		if (notEqual(main, other)) {
			return false; // Don't bother comparing the toString() methods
		} else {
			return main.toString().equals(other.toString());
		}
	}

	/**
	 * Return true if <code>main</code> is definitely not equal to <code>other</code>. More precisely, if <code>other</code> is null <b>OR</b> a different runtime type than
	 * <code>main</code>, return true
	 * 
	 * @param main
	 *            The object <code>other</code> is being compared to.
	 * @param other
	 *            The object being examined for equality with <code>main</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>main</cod> is <code>null</code>
	 */
	public static boolean notEqual(Object main, Object other) {
		// Main can't be null
		if (main == null) {
			throw new NullPointerException("main is null");
		} else if (other == null) {
			return true;
		} else {
			return main.getClass() != other.getClass();
		}
	}
}

package org.kuali.common.util;

public class ObjectUtils {

	/**
	 * <p>
	 * This method returns <code>true</code> if both <code>toString()</code> methods return matching strings AND both objects are the exact same runtime type.
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
	 * If neither one is <code>null</code>, and both are the exact same runtime type, then compare their respective <code>toString()</code> methods for equality.
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

		// They are the same object
		if (sameObject(main, other)) {
			return true;
		}

		// Only bother comparing the toString() methods if they are the exact same runtime type
		if (sameType(main, other)) {
			return main.toString().equals(other.toString());
		} else {
			return false;
		}
	}

	/**
	 * <p>
	 * This method returns <code>true</code> if both <code>hashCode()</code> methods return matching values AND both objects are the exact same runtime type.
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
	 * If neither one is <code>null</code>, and both are the exact same runtime type, then compare their respective <code>hashCode()</code> values.
	 * </p>
	 * 
	 * @param main
	 *            The object <code>other</code> is being compared to.
	 * @param other
	 *            The object being examined for equality with <code>main</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>main</cod> is <code>null</code>
	 */
	public static boolean equalsByHashcode(Object main, Object other) {

		// They are the same object
		if (sameObject(main, other)) {
			return true;
		}

		// Only bother comparing the hashcode's if they are the exact same runtime type
		if (sameType(main, other)) {
			return main.hashCode() == other.hashCode();
		} else {
			return false;
		}
	}

	public static boolean sameObject(Object main, Object other) {

		// Main can't be null
		if (main == null) {
			throw new NullPointerException("main is null");
		}

		// They are the same object
		return main == other;
	}

	public static boolean sameType(Object main, Object other) {

		// Main can't be null
		if (main == null) {
			throw new NullPointerException("main is null");
		}

		return other != null && main.getClass() == other.getClass();
	}
}

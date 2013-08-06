package org.kuali.common.util;

public class ObjectUtils {

	/**
	 * <p>
	 * This method returns <code>true</code> if both <code>toString()</code> methods return matching strings AND both objects are the exact same type.
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

		// Main can't be null
		if (main == null) {
			throw new NullPointerException("main is null");
		}

		// They are the same object
		if (main == other) {
			return true;
		}

		if (other == null || main.getClass() != other.getClass()) {
			// other being null or a different runtime type means they are not equal
			return false;
		} else {
			// Both toString() methods producing the exact same string constitutes equality
			return main.toString().equals(other.toString());
		}
	}

}

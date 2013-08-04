package org.kuali.common.util;

public class ObjectUtils {

	/**
	 * <p>
	 * This method returns <code>true</code> if both <code>toString()</code> methods return matching strings and both objects are the same type.
	 * </p>
	 * 
	 * <p>
	 * Returns <code>true</code> immediately if <code>main==other</code> (ie they are the same object).
	 * </p>
	 * <p>
	 * Returns <code>false</code> immediately if <code>other==null</code> OR is a different runtime type than <code>main</code>.
	 * </p>
	 * If both are the exact same runtime type, compare their respective <code>toString()</code> methods for equality.
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
		if (main == null) {
			throw new NullPointerException("main is null");
		}
		if (main == other) {
			return true;
		}
		if (other == null || main.getClass() != other.getClass()) {
			return false;
		} else {
			return main.toString().equals(other.toString());
		}
	}

}

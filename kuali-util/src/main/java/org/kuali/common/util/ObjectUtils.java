package org.kuali.common.util;

public class ObjectUtils {

	/**
	 * Return true immediately if <code>main==other</code>. Return false immediately if <code>other==null</code> OR is a different runtime type than <code>main</code>. If both are
	 * the exact same runtime type, compare their respective <code>toString()</code> results for equality.
	 * 
	 * @param main
	 *            The object <code>other</code> is being compared to.
	 * @param other
	 *            The object being examined for equality with <code>main</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>main==null</code> or <code>main.toString()</code> returns <code>null</code>
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

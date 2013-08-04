package org.kuali.common.util;

public class ObjectUtils {

	/**
	 * Return true immediately if <code>main==other</code><br>
	 * Return false immediately if <code>other==null</code> OR has a different runtime type than <code>main</code><br>
	 * 
	 * If both are the exact same runtime type, compare the strings returned by <code>toString()</code> for equality.
	 * 
	 * @throws NullPointerException
	 *             If main is <code>null</code>
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

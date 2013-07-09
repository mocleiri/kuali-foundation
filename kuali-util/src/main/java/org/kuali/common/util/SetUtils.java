package org.kuali.common.util;

import java.util.HashSet;
import java.util.Set;

public class SetUtils {

	/**
	 * Return a new <code>Set</code> containing only those elements that appear in both <code>a</code> and <code>b</code>
	 */
	public static <T> Set<T> intersection(Set<T> a, Set<T> b) {
		Set<T> result = new HashSet<T>();
		result.addAll(a);
		result.retainAll(b);
		return result;
	}

	/**
	 * Return a new <code>Set</code> containing all of the elements from both <code>a</code> and <code>b</code>
	 */
	public static <T> Set<T> union(Set<T> a, Set<T> b) {
		Set<T> result = new HashSet<T>();
		result.addAll(a);
		result.addAll(b);
		return result;
	}

	/**
	 * Return a new <code>Set</code> containing only those elements that appear in <code>a</code> but not <code>b</code>
	 */
	public static <T> Set<T> difference(Set<T> a, Set<T> b) {
		Set<T> result = new HashSet<T>();
		result.addAll(a);
		result.removeAll(b);
		return result;
	}

}

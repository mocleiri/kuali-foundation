/**
 * Copyright 2010-2013 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl2.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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

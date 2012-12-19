/**
 * Copyright 2010-2012 The Kuali Foundation
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

public class Counter {
	int count;

	public Counter() {
		this(0);
	}

	public Counter(int count) {
		super();
		this.count = count;
	}

	public int increment() {
		if (count == Integer.MAX_VALUE) {
			throw new IllegalStateException("Maximum integer value exceeded");
		} else {
			return ++count;
		}
	}

	public int decrement() {
		if (count == Integer.MIN_VALUE) {
			throw new IllegalStateException("Minimum integer value exceeded");
		} else {
			return --count;
		}
	}
}

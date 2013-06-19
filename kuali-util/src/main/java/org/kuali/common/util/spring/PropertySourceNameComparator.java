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
package org.kuali.common.util.spring;

import java.util.Comparator;

import org.springframework.core.env.PropertySource;

public class PropertySourceNameComparator implements Comparator<PropertySource<?>> {

	@Override
	public int compare(PropertySource<?> o1, PropertySource<?> o2) {
		String name1 = o1.getName();
		String name2 = o2.getName();
		return name1.compareTo(name2);
	}

}

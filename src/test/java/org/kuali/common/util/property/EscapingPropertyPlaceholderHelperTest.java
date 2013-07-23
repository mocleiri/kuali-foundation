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
package org.kuali.common.util.property;

import java.util.Properties;

import org.junit.Test;
import org.springframework.util.PropertyPlaceholderHelper;

public class EscapingPropertyPlaceholderHelperTest {

	@Test
	public void test() {
		PropertyPlaceholderHelper helper = Constants.DEFAULT_PROPERTY_PLACEHOLDER_HELPER;
		Properties properties = new Properties();
		properties.setProperty("a", "1");
		properties.setProperty("b", "2");
		properties.setProperty("d", "5");
		properties.setProperty("1.2", "foo");
		String original = "\\${${a}.${b}} ${${a}.${b}} ${c:${d:4}} \\${foo";
		String resolved = helper.replacePlaceholders(original, properties);
		System.out.println(resolved);
	}

	@Test
	public void test1() {
		PropertyPlaceholderHelper helper = Constants.DEFAULT_PROPERTY_PLACEHOLDER_HELPER;
		Properties properties = new Properties();
		properties.setProperty("kuali.encoding", "UTF-8");
		String original = "kuali.encoding=${kuali.encoding}  \\${user.home}";
		String resolved = helper.replacePlaceholders(original, properties);
		System.out.println(resolved);
	}
}

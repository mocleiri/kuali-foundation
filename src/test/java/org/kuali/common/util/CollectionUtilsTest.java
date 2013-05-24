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

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CollectionUtilsTest {

	private static final Logger logger = LoggerFactory.getLogger(CollectionUtilsTest.class);

	@Test
	public void testSplitEvenly() {
		try {
			logger.info("Hello World");
			List<String> elements = new ArrayList<String>();
			for (int i = 0; i < 100; i++) {
				elements.add(i + "");
			}
			List<List<String>> listOfLists = CollectionUtils.splitEvenly(elements, 7);
			logger.info("listOfLists.size()=" + listOfLists.size());
			for (List<String> strings : listOfLists) {
				logger.info(strings.size() + "");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

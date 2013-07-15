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
package org.kuali.common.util.config;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.service.DefaultSpringService;
import org.kuali.common.util.service.SpringService;

public class FeatureConfigTest {

	@Test
	public void test() {

		try {
			List<String> ids = new ArrayList<String>();
			ids.add("org.kuali.common:kuali-util:metainf:mpx");
			ids.add("org.kuali.common:kuali-util:metainf:sql");
			ids.add("org.kuali.common:kuali-util:metainf");
			ids.add("org.kuali.common:kuali-util:scm");
			System.setProperty("feature.ids", CollectionUtils.getCSV(ids));
			SpringService ss = new DefaultSpringService();
			ss.load(FeatureTestConfig.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

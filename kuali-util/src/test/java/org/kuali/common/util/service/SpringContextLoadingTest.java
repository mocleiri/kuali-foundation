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
package org.kuali.common.util.service;

import java.io.File;
import java.util.List;
import java.util.Properties;

import org.junit.Ignore;
import org.junit.Test;
import org.kuali.common.util.LocationUtils;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringContextLoadingTest {

	@Test
	@Ignore
	public void test() {
		try {

			Properties properties = new Properties();
			properties.setProperty("spring.message", "Good bye!");

			ClassPathXmlApplicationContext parent = new ClassPathXmlApplicationContext();
			parent.refresh();
			parent.getBeanFactory().registerSingleton("properties", properties);

			String location1 = "classpath:org/kuali/common/util/child-context.xml";
			String location2 = "/Users/jeffcaddel/ws/kuali-util/src/test/resources/org/kuali/common/util/child-context.xml";
			String[] locations = getLocations(new String[] { location1, location2 });

			ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(locations, false, parent);
			context.refresh();
			context.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected String[] getLocations(List<String> locations) {
		return getLocations(locations.toArray(new String[locations.size()]));
	}

	protected String[] getLocations(String[] locations) {
		for (int i = 0; i < locations.length; i++) {
			String location = locations[i];
			if (!LocationUtils.exists(location)) {
				throw new IllegalArgumentException("Location [" + location + "] does not exist");
			}
			if (LocationUtils.isExistingFile(location)) {
				File file = new File(location);
				locations[i] = LocationUtils.getURLString(file);
			}
		}
		return locations;
	}
}

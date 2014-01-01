package org.kuali.common.util.properties;

import java.util.Properties;

import org.junit.Test;

public class RicePropertiesLoaderTest {

	@Test
	public void test() {
		try {
			String location = "classpath:org/kuali/common/kuali-util/properties/breakfast.xml";
			RicePropertiesLoader loader = new RicePropertiesLoader();
			Properties properties = loader.load(location);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

package org.kuali.common.util.properties;

import java.util.Properties;

import org.junit.Test;
import org.kuali.common.util.PropertyUtils;

public class RicePropertiesLoaderTest {

	@Test
	public void test() {
		try {
			String location = "classpath:org/kuali/common/kuali-util/properties/breakfast.xml";
			RicePropertiesLoader loader = RicePropertiesLoader.builder().ignoreUnresolvablePlaceholdersInNestedConfigValues(true).build();
			Properties properties = loader.load(location);
			PropertyUtils.info(properties);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

package org.kuali.common.util.properties;

import java.util.Properties;

import org.junit.Test;
import org.kuali.common.util.PropertyUtils;

public class RicePropertiesLoaderTest {

	@Test
	public void test() {
		try {
			System.setProperty("milk.price", "4.44");
			System.setProperty("foo", "${bar}");
			String location = "classpath:org/kuali/common/kuali-util/properties/breakfast.xml";
			RicePropertiesLoader loader = RicePropertiesLoader.builder().systemPropertiesWin(true).allowUnresolvablePlaceholders(true).build();
			Properties properties = loader.load(location);
			PropertyUtils.info(properties);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

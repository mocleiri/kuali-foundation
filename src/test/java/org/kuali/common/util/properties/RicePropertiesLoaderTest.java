package org.kuali.common.util.properties;

import java.util.Properties;

import org.junit.Test;
import org.kuali.common.util.PropertyUtils;
import org.springframework.util.PropertyPlaceholderHelper;

public class RicePropertiesLoaderTest {

	@Test
	public void test() {
		try {
			System.setProperty("milk.price", "4.44");
			System.setProperty("foo", "${bar}");
			String location = "classpath:org/kuali/common/kuali-util/properties/breakfast.xml";
			PropertyPlaceholderHelper helper = RicePropertyPlaceholderHelper.builder().allowUnresolvablePlaceholders(true).build();
			RicePropertiesLoader loader = RicePropertiesLoader.builder().systemPropertiesWin(false).propertyPlaceholderHelper(helper).build();
			Properties properties = loader.load(location);
			PropertyUtils.info(properties);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

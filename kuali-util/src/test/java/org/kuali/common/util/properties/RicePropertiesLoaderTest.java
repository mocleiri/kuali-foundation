package org.kuali.common.util.properties;

import java.util.Properties;

import org.junit.Test;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.property.processor.ResolvingProcessor;

public class RicePropertiesLoaderTest {

	@Test
	public void test() {
		try {
			String location = "classpath:org/kuali/common/kuali-util/properties/breakfast.xml";
			RicePropertiesLoader loader = RicePropertiesLoader.builder().build();
			Properties properties = loader.load(location);
			ResolvingProcessor processor = new ResolvingProcessor();
			processor.process(properties);
			PropertyUtils.info(properties);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

package org.kuali.common.util.property;

import java.util.Properties;

import org.junit.Test;
import org.springframework.util.PropertyPlaceholderHelper;

public class PropertyPlaceholderHelperTest {

	@Test
	public void test() {
		PropertyPlaceholderHelper helper = Constants.DEFAULT_PROPERTY_PLACEHOLDER_HELPER;
		Properties properties = new Properties();
		properties.setProperty("a", "1");
		properties.setProperty("b", "2");
		properties.setProperty("1.2", "foo");
		String original = "\\${\\${a}.${b}} ${${a}.${b}}";
		String resolved = helper.replacePlaceholders(original, properties);
		System.out.println(resolved);
	}
}

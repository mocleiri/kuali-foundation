package org.kuali.common.devops.json;

import static com.google.common.collect.Sets.newTreeSet;

import java.util.Properties;
import java.util.SortedSet;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import org.kuali.common.util.Str;
import org.kuali.common.util.property.ImmutableProperties;

public class SimpleTest {

	@Test
	public void test() {
		try {

			Properties props = new Properties();
			props.setProperty("name", "jeff");
			props.setProperty("gender", "male\nyo\r\nhi");
			ImmutableProperties ip = ImmutableProperties.copyOf(props);
			ObjectMapper mapper = new ObjectMapper();
			String json = mapper.writeValueAsString(ip);
			ImmutableProperties properties = mapper.readValue(json, ImmutableProperties.class);
			System.out.println(json);
			print(properties);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	protected void print(Properties props) {
		SortedSet<String> keys = newTreeSet(props.stringPropertyNames());
		for (String key : keys) {
			String value = props.getProperty(key);
			System.out.println(key + "=" + Str.flatten(value, "${cr}", "${lf}"));
		}
	}

}

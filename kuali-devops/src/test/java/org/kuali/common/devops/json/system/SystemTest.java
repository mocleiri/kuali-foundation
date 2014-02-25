package org.kuali.common.devops.json.system;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;
import static com.google.common.collect.Maps.newTreeMap;

import java.util.List;
import java.util.SortedMap;

import org.junit.Test;
import org.kuali.common.devops.json.pojo.JacksonContext;
import org.kuali.common.devops.json.pojo.JacksonJsonService;
import org.kuali.common.devops.json.pojo.JsonService;
import org.kuali.common.util.PropertyUtils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class SystemTest {

	@Test
	public void test() {
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(FAIL_ON_UNKNOWN_PROPERTIES, false);
			JacksonContext context = JacksonContext.builder().mapper(mapper).build();
			JsonService service = new JacksonJsonService(context);
			List<String> keys = PropertyUtils.getStartsWithKeys(System.getProperties(), "os.");
			SortedMap<String, String> map = newTreeMap();
			for (String key : keys) {
				map.put(key, System.getProperty(key));
			}
			OperatingSystem os = OperatingSystem.builder().architecture("x86_64").name("Mac OS X").version("10.8.5").build();
			JVM jvm = JVM.builder().withOperatingSystem(os).build();
			System.out.println(service.writeString(jvm));
			System.out.println(service.writeString(map));
			JsonNode root = manual();
			System.out.println(service.writeString(root));
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	protected JsonNode manual() {
		JsonNodeFactory jnf = new JsonNodeFactory(true);

		ObjectNode os = new ObjectNode(jnf);
		os.put("arch", "x86_64");
		os.put("name", "Mac OS X");
		os.put("version", "10.8.5");

		ObjectNode root = new ObjectNode(jnf);
		root.put("os", os);
		return root;
	}

}

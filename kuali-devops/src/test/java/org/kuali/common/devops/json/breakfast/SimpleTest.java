package org.kuali.common.devops.json.breakfast;

import static com.google.common.collect.Lists.newArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.kuali.common.util.Str;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.MapLikeType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.google.common.collect.ImmutableMap;

public class SimpleTest {

	@Test
	public void test() {
		try {
			ObjectMapper mapper = new ObjectMapper();
			String json = mapper.writeValueAsString(ImmutableMap.<String, Object> of("name", "jeff", "age", 40 * 1D));
			TypeFactory factory = mapper.getTypeFactory();
			MapLikeType type = factory.constructMapLikeType(HashMap.class, String.class, Object.class);
			Map<String, Object> map = mapper.readValue(json, type);
			System.out.println(json);
			print(map);

			JsonNode node = mapper.readTree(json);
			List<Map.Entry<String, JsonNode>> fields = newArrayList(node.fields());
			for (Map.Entry<String, JsonNode> field : fields) {

			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	protected void print(Map<?, ?> map) {
		for (Object key : map.keySet()) {
			Object value = map.get(key);
			System.out.println(key + "=" + Str.flatten(value.toString(), "${cr}", "${lf}") + " - [" + value.getClass().getCanonicalName() + "]");
		}
	}

}

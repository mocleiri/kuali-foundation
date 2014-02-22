package org.kuali.common.devops.json;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.type.MapLikeType;
import org.junit.Test;
import org.kuali.common.util.Str;

import com.google.common.collect.ImmutableMap;

public class SimpleTest {

	@Test
	public void test() {
		try {
			ObjectMapper mapper = new ObjectMapper();
			String json = mapper.writeValueAsString(ImmutableMap.<String, Object> of("name", "jeff", "age", 40 * 1D));
			MapLikeType type = mapper.getTypeFactory().constructMapLikeType(HashMap.class, String.class, Object.class);
			Map<InputStream, OutputStream> map = mapper.readValue(json, type);
			System.out.println(json);
			print(map);
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

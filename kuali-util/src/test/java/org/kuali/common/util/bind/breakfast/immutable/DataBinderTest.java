package org.kuali.common.util.bind.breakfast.immutable;

import static com.google.common.collect.Maps.newHashMap;
import static org.kuali.common.util.ReflectionUtils.newInstance;

import java.util.Map;

import org.apache.commons.lang3.builder.Builder;
import org.junit.Test;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.validation.DataBinder;

public class DataBinderTest {

	@Test
	public void test() {
		try {
			Map<String, Object> milkMap = newHashMap();
			milkMap.put("type", "lowfat");
			milkMap.put("price", "2.29");
			Milk milk = build(Milk.Builder.class, milkMap);
			Map<String, Object> bowlMap = newHashMap();
			bowlMap.put("milk", milk);
			Bowl bowl = build(Bowl.Builder.class, bowlMap);
			System.out.println("milk.type=" + bowl.getMilk().getType());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected static <T> T build(Class<? extends Builder<T>> type, Map<String, Object> map) {
		MutablePropertyValues values = new MutablePropertyValues(map);
		Builder<T> builder = newInstance(type);
		DataBinder binder = new DataBinder(builder);
		binder.bind(values);
		return builder.build();
	}

}

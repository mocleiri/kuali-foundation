package org.kuali.common.util.bind.breakfast.immutable;

import static com.google.common.collect.Maps.newHashMap;

import java.util.Map;

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
			MutablePropertyValues milkValues = new MutablePropertyValues(milkMap);
			Milk.Builder milkBuilder = new Milk.Builder();
			DataBinder milkBinder = new DataBinder(milkBuilder);
			milkBinder.bind(milkValues);
			Milk milk = milkBuilder.build();
			Map<String, Object> bowlMap = newHashMap();
			bowlMap.put("milk", milk);
			MutablePropertyValues bowlValues = new MutablePropertyValues(bowlMap);
			Bowl.Builder bowlBuilder = new Bowl.Builder();
			DataBinder bowlBinder = new DataBinder(bowlBuilder);
			bowlBinder.bind(bowlValues);
			Bowl bowl = bowlBuilder.build();
			System.out.println("milk.type=" + bowl.getMilk().getType());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

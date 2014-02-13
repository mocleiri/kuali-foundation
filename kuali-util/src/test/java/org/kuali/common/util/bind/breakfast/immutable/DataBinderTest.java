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
			Map<String, String> original = newHashMap();
			original.put("type", "lowfat");
			original.put("price", "2.29");
			MutablePropertyValues mpvs = new MutablePropertyValues(original);
			Milk.Builder mb = new Milk.Builder();
			DataBinder binder = new DataBinder(mb);
			binder.bind(mpvs);
			Milk milk = mb.build();
			Bowl.Builder bb = new Bowl.Builder();
			Bowl bowl = bb.milk(milk).build();
			System.out.println("milk.type=" + bowl.getMilk().getType());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

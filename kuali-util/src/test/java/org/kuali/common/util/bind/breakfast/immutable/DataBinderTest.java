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
			original.put("milk.type", "lowfat");
			original.put("milk.price", "2.29");
			MutablePropertyValues mpvs = new MutablePropertyValues(original);
			Bowl.Builder builder = new Bowl.Builder();
			DataBinder binder = new DataBinder(builder);
			binder.bind(mpvs);
			Bowl bowl = builder.build();
			System.out.println("milk.type=" + bowl.getMilk().getType());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

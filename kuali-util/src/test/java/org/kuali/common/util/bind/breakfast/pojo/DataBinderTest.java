package org.kuali.common.util.bind.breakfast.pojo;

import java.util.Map;

import org.junit.Test;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.validation.DataBinder;

import com.google.common.collect.Maps;

public class DataBinderTest {

	@Test
	public void test() {
		try {

			Map<String, String> original = Maps.newHashMap();
			original.put("milk.type", "lowfat");
			MutablePropertyValues mpvs = new MutablePropertyValues(original);
			Bowl bowl = new Bowl();
			DataBinder binder = new DataBinder(bowl);
			binder.bind(mpvs);
			System.out.println("milk.type=" + bowl.getMilk().getType());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

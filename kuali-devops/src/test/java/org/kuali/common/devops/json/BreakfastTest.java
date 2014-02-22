package org.kuali.common.devops.json;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;

public class BreakfastTest {

	@Test
	public void test() {
		try {
			ObjectMapper mapper = new ObjectMapper();
			Milk milk = Milk.builder().price(2.29).type("lowfat").build();
			Bowl bowl = Bowl.builder().milk(milk).build();
			String json = mapper.writeValueAsString(bowl);
			System.out.println(json);
			Bowl newBowl = mapper.readValue(json, Bowl.class);
			System.out.println(newBowl.getMilk().getPrice());
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

}

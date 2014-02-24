package org.kuali.common.devops.json.pojo;

import static org.junit.Assert.assertEquals;
import static org.kuali.common.devops.json.pojo.Apple.newApple;
import static org.kuali.common.devops.json.pojo.Basket.newBasket;

import java.util.List;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.guava.GuavaModule;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;

public class BasketTest {

	@Test
	public void test() {
		try {
			Basket basket1 = newBasket("straw", createApples(), Optional.of(newApple("green")));
			ObjectMapper mapper = new ObjectMapper();
			mapper.registerModule(new GuavaModule());
			String json1 = mapper.writeValueAsString(basket1);
			System.out.println(json1);
			Basket basket2 = mapper.readValue(json1, Basket.class);
			String json2 = mapper.writeValueAsString(basket2);
			System.out.println(json2);
			assertEquals(json1, json2);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	protected List<Apple> createApples() {
		Apple a1 = newApple("red", 1.0);
		Apple a2 = newApple("green");
		return ImmutableList.of(a1, a2);
	}

}

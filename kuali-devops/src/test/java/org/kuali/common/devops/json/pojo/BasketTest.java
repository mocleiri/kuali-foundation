package org.kuali.common.devops.json.pojo;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableList;

public class BasketTest {

	@Test
	public void test() {
		try {
			Basket basket1 = getBasket();
			ObjectMapper mapper = new ObjectMapper();
			String json1 = mapper.writeValueAsString(basket1);
			System.out.println(json1);
			Basket basket2 = mapper.readValue(json1, Basket.class);
			String json2 = mapper.writeValueAsString(basket2);
			System.out.println(json2);
			Assert.assertEquals(json1, json2);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	protected Basket getBasket() {
		Apple a1 = Apple.create("red");
		Apple a2 = Apple.create("green");
		List<Apple> apples = ImmutableList.of(a1, a2);
		return new Basket("straw", apples);
	}

}

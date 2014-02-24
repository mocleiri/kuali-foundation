package org.kuali.common.devops.json.pojo;

import static org.junit.Assert.assertEquals;
import static org.kuali.common.devops.json.pojo.Apple.newApple;
import static org.kuali.common.devops.json.pojo.Basket.newBasket;
import static org.kuali.common.util.base.Exceptions.illegalState;

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
			String json1 = write(mapper, basket1);
			System.out.println(json1);
			Basket basket2 = read(mapper, json1, Basket.class);
			String json2 = write(mapper, basket2);
			System.out.println(json2);
			assertEquals(json1, json2);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	protected static <T> T read(ObjectMapper mapper, String json, Class<T> type) {
		try {
			return mapper.readValue(json, type);
		} catch (Exception e) {
			throw illegalState(e);
		}
	}

	protected static <T> String write(ObjectMapper mapper, T reference) {
		try {
			return mapper.writer().withDefaultPrettyPrinter().writeValueAsString(reference);
		} catch (Exception e) {
			throw illegalState(e);
		}
	}

	protected List<Apple> createApples() {
		Apple a1 = newApple("red", 1.0);
		Apple a2 = newApple("green");
		return ImmutableList.of(a1, a2);
	}

}

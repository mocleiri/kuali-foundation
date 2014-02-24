package org.kuali.common.devops.json.pojo;

import static java.lang.System.currentTimeMillis;
import static org.junit.Assert.assertEquals;
import static org.kuali.common.devops.json.pojo.Apple.newApple;
import static org.kuali.common.devops.json.pojo.JacksonJsonService.newJacksonJsonService;

import java.util.List;

import org.junit.Test;

import com.google.common.collect.ImmutableList;

public class BasketTest {

	@Test
	public void test() {
		try {
			System.out.println(currentTimeMillis());
			Basket basket1 = Basket.builder("straw").apples(createApples()).apple(newApple("green")).build();
			JsonService service = newJacksonJsonService();
			String json1 = service.writeString(basket1);
			System.out.println(json1);
			Basket basket2 = service.readString(json1, Basket.class);
			String json2 = service.writeString(basket2);
			System.out.println(json2);
			assertEquals(json1, json2);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	protected List<Apple> createApples() {
		Apple a1 = newApple("red");
		Apple a2 = newApple("green");
		return ImmutableList.of(a1, a2);
	}

}

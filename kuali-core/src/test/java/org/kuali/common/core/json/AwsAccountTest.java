package org.kuali.common.core.json;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.kuali.common.core.json.api.JsonService;
import org.kuali.common.core.json.jackson.JacksonJsonService;

import com.google.common.base.Optional;

public class AwsAccountTest {

	@Test
	public void test() {
		try {
			AwsAccount account = AwsAccount.builder().withName("name").withAccountNumber("123").withDescription(Optional.of("description")).build();
			JsonService service = new JacksonJsonService();
			String json1 = service.writeString(account);
			System.out.println(json1);
			AwsAccount deserialized = service.readString(json1, AwsAccount.class);
			String json2 = service.writeString(deserialized);
			assertEquals(json1, json2);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

}

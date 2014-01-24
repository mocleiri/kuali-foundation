package org.kuali.common.util.bind.test;

import java.util.Set;

import org.junit.Test;
import org.kuali.common.util.system.SystemProperties;

public class BindKeysTest {

	@Test
	public void test() {
		try {
			Set<String> keys = BindKeys.get(SystemProperties.class);
			System.out.println(String.format("----- %s Keys -----", keys.size()));
			for (String key : keys) {
				System.out.println(key);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

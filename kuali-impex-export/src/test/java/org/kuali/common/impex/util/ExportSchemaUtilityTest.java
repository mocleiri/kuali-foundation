package org.kuali.common.impex.util;

import org.junit.Test;

public class ExportSchemaUtilityTest {

	@Test
	public void test() {
		ExportSchemaUtility.main(new String[] { "classpath:riceenv1.properties" });
	}

}

package org.kuali.common.impex.util;

import org.junit.Test;

public class ExportSchemaUtilityTest {

	@Test
	public void test() {
		String[] args = { "classpath:rice.properties" };
		SchemaExportUtility.main(args);
	}

}

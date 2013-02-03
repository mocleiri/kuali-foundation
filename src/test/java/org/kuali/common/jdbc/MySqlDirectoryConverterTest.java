package org.kuali.common.jdbc;

import java.io.File;

import org.junit.Test;
import org.kuali.common.jdbc.convert.DirectoryContext;
import org.kuali.common.jdbc.convert.DirectoryConverter;
import org.kuali.common.jdbc.convert.MySqlConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MySqlDirectoryConverterTest {

	private static final Logger logger = LoggerFactory.getLogger(MySqlDirectoryConverterTest.class);

	@Test
	public void testConvert() {
		try {
			logger.info("");
			String basedir = "/Users/jeffcaddel/ws/spring-db-jc/ks-deployments/ks-cfg-dbs/ks-rice-db";
			String resourceDir = "src/main/resources/impex/sql/mysql";
			String filename = basedir + "/" + resourceDir;
			DirectoryContext context = new DirectoryContext();
			context.setArtifactId("ks-rice-db");
			context.setConverter(new MySqlConverter());
			context.setDatabase("mysql");
			context.setDirectory(new File(filename));
			DirectoryConverter dc = new DirectoryConverter();
			dc.convert(context);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

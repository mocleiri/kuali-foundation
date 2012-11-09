package org.kuali.common.morph;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class MorpherTest {
	private static final Logger logger = LoggerFactory.getLogger(MorpherTest.class);

	@Autowired
	private Properties properties = null;

	@Test
	public void test() {
		try {
			Assert.assertNotNull("properties is null.", properties);
			File dir = new File(properties.getProperty("clover.data.dir"));
			logger.info(dir.exists() + "");
			File[] files = dir.listFiles();
			for (File file : files) {
				logger.info(file.getCanonicalPath());
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	protected List<Table> getTables(List<File> files) {
		List<Table> tables = new ArrayList<Table>();
		return tables;
	}

}

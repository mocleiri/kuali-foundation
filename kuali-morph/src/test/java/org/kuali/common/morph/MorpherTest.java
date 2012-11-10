package org.kuali.common.morph;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
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
			long start = System.currentTimeMillis();
			Assert.assertNotNull("properties is null.", properties);
			File dir = new File(properties.getProperty("clover.data.dir"));
			logger.info(dir.exists() + "");
			File[] files = dir.listFiles();
			Arrays.sort(files);
			List<Table> tables = getTables(Arrays.asList(files));
			for (Table table : tables) {
				logger.info(table.getName());
			}
			long elapsed = System.currentTimeMillis() - start;
			logger.info("Elapsed: " + elapsed);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	protected List<Table> getTables(List<File> files) {
		List<Table> tables = new ArrayList<Table>();
		for (File file : files) {
			Table table = getTable(file);
			tables.add(table);
		}
		return tables;
	}

	protected Table getTable(File file) {
		try {
			String filename = file.getName().toUpperCase();
			int pos = filename.indexOf(".");
			String tablename = filename.substring(0, pos);

			List<String> lines = FileUtils.readLines(file);
			String line1 = lines.get(0);
			String[] columns = StringUtils.splitByWholeSeparatorPreserveAllTokens(line1, "|");

			List<String[]> rows = new ArrayList<String[]>();
			for (int i = 1; i < lines.size(); i++) {
				String line = lines.get(i);
				String[] row = StringUtils.splitByWholeSeparatorPreserveAllTokens(line, "|");
				if (row.length > columns.length) {
					throw new IllegalStateException("Column count doesn't match. [" + file.getAbsolutePath() + ",row " + i + "] columns=" + columns.length + " row=" + row.length);
				}
				rows.add(row);
			}

			Table table = new Table();
			table.setName(tablename);
			table.setColumns(Arrays.asList(columns));
			table.setRows(rows);
			return table;
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}
}

package org.kuali.common.morph;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kuali.common.util.LocationUtils;
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
			String outputDir = properties.getProperty("output.dir");
			long start = System.currentTimeMillis();
			Assert.assertNotNull("properties is null.", properties);
			File dir = new File(properties.getProperty("clover.data.dir"));
			logger.info(dir.exists() + "");
			File[] files = dir.listFiles();
			Arrays.sort(files);
			List<Table> tables = getTables(Arrays.asList(files));
			int rows = 0;
			for (Table table : tables) {
				logger.info(table.getName() + " columns:" + table.getColumns().size() + " rows:" + table.getRows().size());
				rows += table.getRows().size();
				String xml = getXml(table);
				File xmlFile = new File(outputDir + "/" + table.getName() + ".xml");
				logger.info(xmlFile.getAbsolutePath());
				FileUtils.writeStringToFile(xmlFile, xml);
			}
			logger.info("Total Rows: " + rows);
			long elapsed = System.currentTimeMillis() - start;
			logger.info("Elapsed: " + elapsed);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	protected String getXml(Table table) {
		StringBuilder sb = new StringBuilder();
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
		sb.append("<!DOCTYPE dataset SYSTEM \"data.dtd\">\n");
		sb.append("<dataset>\n");
		List<String[]> rows = table.getRows();
		List<String> columns = table.getColumns();
		for (int i = 0; i < rows.size(); i++) {
			int lineLength = 5 + table.getName().length();
			sb.append("    <" + table.getName());
			String[] row = rows.get(i);
			for (int j = 0; j < columns.size(); j++) {
				String column = columns.get(j);
				String value = row[j];
				if (!StringUtils.isBlank(value)) {
					lineLength = lineLength + 1 + column.length() + 1 + 1 + value.length() + 1;
					if (lineLength == -1) {
						sb.append("\n        ");
						lineLength = 0;
					}
					sb.append(" " + column.toUpperCase() + "=" + '"' + value + '"');
				}
			}
			sb.append(" />\n");
		}
		sb.append("</dataset>\n");
		return sb.toString();
	}

	protected String escape(String s) {
		return s.replace("<", "&lt;").replace("\"", "&quot;").replace("\n", "&#xa;").replace("\r", "&#xd;");
	}

	protected List<Table> getTables(List<File> files) {
		List<Table> tables = new ArrayList<Table>();
		for (File file : files) {
			Table table = getTable(file);
			tables.add(table);
		}
		return tables;
	}

	protected int appendLines(List<String> lines, int index, StringBuilder sb) {
		String line = lines.get(index);
		while (!line.endsWith("|")) {
			sb.append(line + "\n");
			index++;
		}
		return index;
	}

	protected Table getTable(File file) {
		try {
			String filename = file.getName().toUpperCase();
			int pos = filename.indexOf(".");
			String tablename = filename.substring(0, pos);

			String content = FileUtils.readFileToString(file);
			String headerLine = content.substring(0, content.indexOf("\n"));
			String[] columns = StringUtils.splitByWholeSeparatorPreserveAllTokens(headerLine, "|");

			List<String[]> rows = getRows(content, columns, file);

			Table table = new Table();
			table.setName(tablename);
			table.setColumns(Arrays.asList(columns));
			table.setRows(rows);
			return table;
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}

	protected List<String[]> getRows(String content, String[] columns, File file) {
		List<String> lines = LocationUtils.readLinesFromString(content);
		List<String[]> rows = new ArrayList<String[]>();
		for (int i = 1; i < lines.size(); i++) {
			String line = lines.get(i);
			while (!line.endsWith("|")) {
				i = i + 1;
				line = line + "\n" + lines.get(i);
			}
			String[] row = StringUtils.splitByWholeSeparatorPreserveAllTokens(line, "|");
			if (row.length != columns.length) {
				throw new IllegalStateException("Column count doesn't match. [" + file.getAbsolutePath() + ",row " + i + "] columns=" + columns.length + " row=" + row.length);
			}
			for (int j = 0; j < row.length; j++) {
				row[j] = escape(row[j]);
			}
			rows.add(row);
		}
		return rows;
	}
}

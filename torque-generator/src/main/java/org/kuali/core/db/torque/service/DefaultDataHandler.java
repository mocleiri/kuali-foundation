package org.kuali.core.db.torque.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.torque.engine.database.model.Column;
import org.codehaus.plexus.util.StringUtils;
import org.kuali.common.util.FormatUtils;
import org.kuali.core.db.torque.pojo.DumpTableContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultDataHandler implements DataHandler {

	private static final Logger logger = LoggerFactory.getLogger(DefaultDataHandler.class);

	private static final String FS = File.separator;
	private static final String LF = "\n";

	@Override
	public OutputStream openOutputStream(File workingDir, String tableName) throws IOException {
		File outputFile = new File(workingDir.getAbsolutePath() + FS + tableName + ".csv");
		return new BufferedOutputStream(FileUtils.openOutputStream(outputFile));
	}

	@Override
	public void startData(DumpTableContext context) throws IOException {
		Column[] columns = context.getColumns();
		String encoding = context.getImpexContext().getEncoding();
		String header = getColumnsHeader(columns) + LF;
		OutputStream out = context.getOutputStream();
		out.write(header.getBytes(encoding));
	}

	@Override
	public void doData(DumpTableContext context) {
		List<String[]> rows = context.getCurrentData();
		convert(rows);
	}

	protected void convert(List<String[]> rows) {
		for (String[] row : rows) {
			convert(row);
		}
	}

	protected void convert(String[] row) {
		for (int i = 0; i < row.length; i++) {
			row[i] = getConvertedString(row[i]);
		}
	}

	protected String getConvertedString(String s) {
		if (s == null) {
			return "NULL";
		}
		String converted = StringUtils.replace(s, "\r", "${cr}");
		converted = StringUtils.replace(s, "\n", "${lf}");
		converted = StringUtils.replace(s, "\"", "${quote}");
		return converted;
	}

	@Override
	public void finishData(DumpTableContext context) {
		if (context.getTotalRowCount() > 0) {
			long threadId = Thread.currentThread().getId();
			String tableName = context.getTableContext().getName();
			String trc = FormatUtils.getCount(context.getTotalRowCount());
			String tds = FormatUtils.getSize(context.getTotalDataSize());
			Object[] args = { threadId, tableName, trc, tds };
			logger.info("[{}] - Dumped [{}] Total Rows: {}  Total Size: {}", args);
		}
	}

	protected String getColumnsHeader(Column[] columns) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < columns.length; i++) {
			if (i != 0) {
				sb.append(",");
			}
			sb.append(columns[i]);
		}
		return sb.toString();
	}

}

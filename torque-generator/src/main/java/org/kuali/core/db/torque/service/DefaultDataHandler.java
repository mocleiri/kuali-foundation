package org.kuali.core.db.torque.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.torque.engine.database.model.Column;
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
		String filename = getFilename(workingDir, tableName);
		File outputFile = new File(filename);
		return new BufferedOutputStream(FileUtils.openOutputStream(outputFile));
	}

	protected String getFilename(File workingDir, String tableName) {
		return workingDir.getAbsolutePath() + FS + tableName + ".csv";
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
	public void doData(DumpTableContext context) throws IOException {
		String encoding = context.getImpexContext().getEncoding();
		format(context.getCurrentData());
		writeRows(context.getCurrentData(), encoding, context.getOutputStream());
	}

	protected void writeRows(List<String[]> rows, String encoding, OutputStream out) throws IOException {
		for (String[] row : rows) {
			String line = getLine(row);
			byte[] bytes = line.getBytes(encoding);
			out.write(bytes);
		}
	}

	protected String getLine(String[] row) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < row.length; i++) {
			if (i != 0) {
				sb.append(",");
			}
			sb.append('"');
			sb.append(row[i]);
			sb.append('"');
		}
		sb.append(LF);
		return sb.toString();
	}

	@Override
	public void finishData(DumpTableContext context) throws IOException {
		if (!CollectionUtils.isEmpty(context.getCurrentData())) {
			String encoding = context.getImpexContext().getEncoding();
			format(context.getCurrentData());
			writeRows(context.getCurrentData(), encoding, context.getOutputStream());
		}
		if (context.getTotalRowCount() > 0) {
			long threadId = Thread.currentThread().getId();
			String tableName = context.getTableContext().getName();
			String trc = FormatUtils.getCount(context.getTotalRowCount());
			String tds = FormatUtils.getSize(context.getTotalDataSize());
			Object[] args = { threadId, tableName, trc, tds };
			logger.info("[{}] - Dumped [{}] Total Rows: {}  Total Size: {}", args);
		} else {
			String filename = getFilename(context.getImpexContext().getWorkingDir(), context.getTableContext().getName());
			File emptyFile = new File(filename);
			FileUtils.forceDelete(emptyFile);
		}
	}

	protected void format(List<String[]> rows) {
		for (String[] row : rows) {
			format(row);
		}
	}

	protected void format(String[] row) {
		for (int i = 0; i < row.length; i++) {
			row[i] = ImpexUtils.format(row[i]);
		}
	}

	protected String getColumnsHeader(Column[] columns) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < columns.length; i++) {
			if (i != 0) {
				sb.append(",");
			}
			sb.append(columns[i].getName());
		}
		return sb.toString();
	}

}

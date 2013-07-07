package org.kuali.common.impex.data.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.kuali.common.impex.data.service.DumpDataContext;
import org.kuali.common.impex.model.Column;
import org.kuali.common.impex.model.util.ModelUtils;
import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.FormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DataHandler {

	private static final Logger logger = LoggerFactory.getLogger(DataHandler.class);

	protected static final String FS = File.separator;
	protected static final String LF = "\n";
	protected static final String DOUBLE_QUOTE = "\"";
	protected static final String COMMA = ",";

	public static final String MPX_EXTENSION = ".mpx";

	public static final String CARRIAGE_RETURN = "\r";

	public static final String MPX_NULL = "${mpx.null}";
	public static final String MPX_CARRIAGE_RETURN = "${mpx.cr}";
	public static final String MPX_LINEFEED = "${mpx.lf}";
	public static final String MPX_QUOTE = "${mpx.quote}";

	public static final String MPX_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSZZZZZ";

	public static File getFileForTable(DumpDataContext context, String tableName) {
		String filename = getFilename(context.getWorkingDir(), tableName);
		return new File(filename);
	}

	protected static String getFilename(File workingDir, String tableName) {
		// to keep file names consistent, capitalize the name of the table before requesting a file name
		tableName = tableName.toUpperCase();
		return workingDir.getAbsolutePath() + FS + tableName + MPX_EXTENSION;
	}

	public static void startData(DumpProgress progress) throws IOException {
		List<Column> columns = progress.getColumns();
		String encoding = progress.getContext().getEncoding();

		String header = ModelUtils.getCsvColumnNames(columns) + LF;
		OutputStream out = progress.getOutputStream();
		out.write(header.getBytes(encoding));
	}

	public static void doData(DumpProgress progress) throws IOException {
		String encoding = progress.getContext().getEncoding();
		formatMpx(progress.getCurrentData());
		writeRows(progress.getCurrentData(), encoding, progress.getOutputStream());
	}

	protected static void writeRows(List<List<String>> rows, String encoding, OutputStream out) throws IOException {
		for (List<String> row : rows) {
			String line = getLine(row);
			byte[] bytes = line.getBytes(encoding);
			out.write(bytes);
		}
	}

	/**
	 * Comma separated, all values enclosed in double quotes, always terminated by a linefeed
	 */
	protected static String getLine(List<String> row) {
		StringBuilder sb = new StringBuilder();
		boolean first = true;
		for (String s : row) {
			if (first) {
				first = false;
			} else {
				sb.append(COMMA);
			}
			sb.append(DOUBLE_QUOTE).append(s).append(DOUBLE_QUOTE);
		}
		sb.append(LF);
		return sb.toString();
	}

	public static void finishData(DumpProgress exportProgress) throws IOException {
		if (!CollectionUtils.isEmpty(exportProgress.getCurrentData())) {
			String encoding = exportProgress.getContext().getEncoding();
			formatMpx(exportProgress.getCurrentData());
			writeRows(exportProgress.getCurrentData(), encoding, exportProgress.getOutputStream());
		}
		if (exportProgress.getTotalRowCount() > 0) {
			long threadId = Thread.currentThread().getId();
			String tableName = exportProgress.getTableContext().getTable().getName();
			String trc = FormatUtils.getCount(exportProgress.getTotalRowCount());
			String tds = FormatUtils.getSize(exportProgress.getTotalDataSize());
			Object[] args = { threadId, tableName, trc, tds };
			logger.info("[{}] - Dumped [{}] Total Rows: {}  Total Size: {}", args);
		} else {
			String filename = getFilename(exportProgress.getContext().getWorkingDir(), exportProgress.getTableContext().getTable().getName());
			File emptyFile = new File(filename);
			FileUtils.forceDelete(emptyFile);
		}
	}

	protected static void formatMpx(List<List<String>> rows) {
		for (List<String> row : rows) {
			for (int i = 0; i < row.size(); i++) {
				row.set(i, formatMpx(row.get(i)));
			}
		}
	}

	public static String formatMpx(String s) {
		if (s == null) {
			return MPX_NULL;
		}
		String converted = StringUtils.replace(s, CARRIAGE_RETURN, MPX_CARRIAGE_RETURN);
		converted = StringUtils.replace(converted, LF, MPX_LINEFEED);
		converted = StringUtils.replace(converted, DOUBLE_QUOTE, MPX_QUOTE);
		return converted;
	}

}

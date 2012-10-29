package org.kuali.common.aws.s3;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;

public class CSVUtils {
	SimpleFormatter formatter = new SimpleFormatter();

	private static CSVUtils instance;

	public static synchronized CSVUtils getInstance() {
		if (instance == null) {
			instance = new CSVUtils();
		}
		return instance;
	}

	protected CSVUtils() {
		super();
	}

	public List<String> getLines(File file) {
		try {
			return FileUtils.readLines(file);
		} catch (IOException e) {
			throw new IllegalStateException("Can't read lines from " + file.getAbsolutePath(), e);
		}
	}

	public List<BucketSummaryLine> getBucketSummaryLines(List<String> lines) {
		List<BucketSummaryLine> summaryLines = new ArrayList<BucketSummaryLine>();
		// Start at 1 instead of zero in order to skip the first line since it is just the column headers
		for (int i = 1; i < lines.size(); i++) {
			String line = lines.get(i);
			String[] tokens = line.split(",");
			String bucket = tokens[0];
			Long files = new Long(tokens[1]);
			Long size = new Long(tokens[2]);
			Date date = formatter.parseDate(tokens[3]);
			BucketSummaryLine bsl = new BucketSummaryLine();
			bsl.setBucket(bucket);
			bsl.setFiles(files);
			bsl.setBytes(size);
			bsl.setDate(date);
			summaryLines.add(bsl);
		}
		return summaryLines;
	}

}

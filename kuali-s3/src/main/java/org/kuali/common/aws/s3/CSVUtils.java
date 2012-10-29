package org.kuali.common.aws.s3;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.io.FileUtils;
import org.kuali.common.aws.s3.pojo.BucketDeltaLine;
import org.kuali.common.aws.s3.pojo.BucketSummaryLine;
import org.kuali.common.aws.s3.pojo.BucketSummaryLineComparator;

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
			throw new IllegalStateException("Can't read lines from " + getCanonicalPath(file), e);
		}
	}

	public String getCanonicalPath(File file) {
		try {
			return file.getCanonicalPath();
		} catch (IOException e) {
			throw new IllegalArgumentException("Unable to determine canonical path for " + file, e);
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
			Long bytes = new Long(tokens[2]);
			Date date = formatter.parseDate(tokens[3]);
			BucketSummaryLine bsl = new BucketSummaryLine();
			bsl.setBucket(bucket);
			bsl.setFiles(files);
			bsl.setBytes(bytes);
			bsl.setDate(date);
			summaryLines.add(bsl);
		}
		return summaryLines;
	}

	public List<String> getBucketNames(List<BucketSummaryLine> summaryLines) {
		Map<String, String> map = new TreeMap<String, String>();
		for (BucketSummaryLine bsl : summaryLines) {
			map.put(bsl.getBucket(), bsl.getBucket());
		}
		return new ArrayList<String>(map.keySet());

	}

	public List<BucketDeltaLine> getBucketDeltaLines(String bucket, List<BucketSummaryLine> summaryLines) {
		List<BucketSummaryLine> matchingLines = getMatchingSummaryLines(bucket, summaryLines);
		List<BucketDeltaLine> bdls = new ArrayList<BucketDeltaLine>();
		for (int i = 0; i < matchingLines.size() - 1; i++) {
			BucketSummaryLine current = matchingLines.get(i);
			BucketSummaryLine next = matchingLines.get(i + 1);
			long fileDelta = next.getFiles() - current.getFiles();
			long byteDelta = next.getBytes() - current.getBytes();
			Date startDate = current.getDate();
			Date endDate = next.getDate();
			BucketDeltaLine bdl = new BucketDeltaLine();
			bdl.setFileDelta(fileDelta);
			bdl.setByteDelta(byteDelta);
			bdl.setStartDate(startDate);
			bdl.setEndDate(endDate);
			bdls.add(bdl);
		}
		return bdls;
	}

	public List<BucketSummaryLine> getMatchingSummaryLines(String bucket, List<BucketSummaryLine> summaryLines) {
		Collections.sort(summaryLines, new BucketSummaryLineComparator());
		List<BucketSummaryLine> matchingLines = new ArrayList<BucketSummaryLine>();
		for (BucketSummaryLine bsl : summaryLines) {
			if (bsl.getBucket().equals(bucket)) {
				matchingLines.add(bsl);
			}
		}
		return matchingLines;
	}

}

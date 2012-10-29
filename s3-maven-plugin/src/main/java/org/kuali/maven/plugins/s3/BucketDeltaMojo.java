/**
 * Copyright 2011-2012 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl2.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.maven.plugins.s3;

import java.io.File;
import java.util.Collections;
import java.util.List;

import org.apache.maven.plugin.AbstractMojo;
import org.kuali.common.aws.s3.CSVUtils;
import org.kuali.common.aws.s3.SimpleFormatter;
import org.kuali.common.aws.s3.pojo.BucketSummaryLine;
import org.kuali.common.aws.s3.pojo.BucketSummaryLineComparator;

import com.amazonaws.services.s3.model.AmazonS3Exception;

/**
 * Generate summary information for any S3 buckets "owned" by the provided <code>accessKey</code>
 *
 * @goal bucketdelta
 */
public class BucketDeltaMojo extends AbstractMojo {

	CSVUtils csvUtils = CSVUtils.getInstance();
	SimpleFormatter formatter = new SimpleFormatter();

	/**
	 * The file where the CSV summary is written. If the file already exists, it is appended to.
	 *
	 * @parameter expression="${s3.csvFile}" default-value="${project.build.directory}/s3/summary.csv"
	 */
	File csvFile;

	@Override
	public void execute() {
		try {
			List<String> lines = csvUtils.getLines(csvFile);
			List<BucketSummaryLine> summaryLines = csvUtils.getBucketSummaryLines(lines);
			Collections.sort(summaryLines, new BucketSummaryLineComparator());
			for (BucketSummaryLine summaryLine : summaryLines) {
				getLog().info(summaryLine.getBucket() + " " + formatter.getDate(summaryLine.getDate()));
			}
		} catch (Exception e) {
			throw new AmazonS3Exception("Unexpected error", e);
		}

	}

	public File getCsvFile() {
		return csvFile;
	}

	public void setCsvFile(File csvFile) {
		this.csvFile = csvFile;
	}

}

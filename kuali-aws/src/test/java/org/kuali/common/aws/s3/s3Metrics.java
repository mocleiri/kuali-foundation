/**
 * Copyright 2004-2012 The Kuali Foundation
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
package org.kuali.common.aws.s3;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.internal.StaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.BucketLifecycleConfiguration;
import com.amazonaws.services.s3.model.BucketLifecycleConfiguration.Rule;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3ObjectSummary;

public class s3Metrics {
	private static final String ACCESSKEY = "AKIAJFD5IM7IPVVUEBNA";
	private static final String SECRETYKEY = System.getProperty("secret.key");

	private static final Logger log = LoggerFactory.getLogger(s3Metrics.class);

	private Process process;

	public Process getProcess() {
		return process;
	}

	public void setProcess(Process process) {
		this.process = process;
	}

   	protected AWSCredentials getCredentials() {
		log.debug("access key: " + ACCESSKEY);
		return new BasicAWSCredentials(ACCESSKEY, SECRETYKEY);
	}

	protected AmazonS3Client getClient() {
		AWSCredentials credentials = getCredentials();
		return new AmazonS3Client(credentials);
	}

	public Boolean FileExists(String FNAME) {
		File f = new File(FNAME);

		return (f.exists());
	}

	public void writeLinesToFile(String filename, String linesToWrite, Boolean ForceNew) {
		PrintWriter pw = null;
		boolean appendToFile;
		appendToFile = false;
		String[] ltw = { linesToWrite };

		if (FileExists(filename)) {
			appendToFile = true;
		}

		if (ForceNew) {
			appendToFile = false;
		}

		try {

			if (appendToFile) {

				// If the file already exists, start writing at the end of it.
				pw = new PrintWriter(new FileWriter(filename, true));

			} else {

				pw = new PrintWriter(new FileWriter(filename));
				// this is equal to:
				// pw = new PrintWriter(new FileWriter(filename, false));

			}

			for (int i = 0; i < ltw.length; i++) {

				pw.println(ltw[i]);

			}
			pw.flush();

		} catch (IOException e) {
			e.printStackTrace();
		} finally {

			// Close the PrintWriter
			if (pw != null)
				pw.close();

		}

	}

	@Test
	// public void execute() throws InterruptedException {
	public void execute() {
		String bucketName = "maven.kuali.org";
		String HOME = System.getenv("HOME");
		AmazonS3Client client = getClient();

		Date now = new Date();
		String Date = DateFormat.getInstance().format(now);
		long nowmm = now.getTime();
		long yearmm = 60 * 60 * 365 * 24 * 1000L;
		// millaseconds in a day 86,400,000
		// millaseconds in a year 31536000000 need the "L"

		long cmpdate = nowmm - yearmm;

		List<S3Types> dataArray = new ArrayList<S3Types>();

		String resource_location = HOME + "/ws/kuali-aws/src/test/resources";
		String BucketChart01 = resource_location + "/BucketChart1.cvs"; // metric for Rice, Student, Ole
		String BucketChart01a = resource_location + "/BucketChart1a.cvs"; // Metric for Maven Build,Release,Snapshot
		String BucketChart02 = resource_location + "/BucketChart2.cvs"; // Data for Snapshot
		String BucketChart02a = resource_location + "/BucketChart2a.cvs"; // Data for External
		String BucketChart02b = resource_location + "/BucketChart2b.cvs"; // Data for Private
		String BucketChart03 = resource_location + "/BucketChart3.cvs"; // Data for Builds
		String BucketChart04 = resource_location + "/BucketChart4.cvs"; // Data for Release
		String BucketChart05 = resource_location + "/BucketChart5.cvs"; // Snapshot, pie, Keep Data
		String BucketChart06 = resource_location + "/BucketChart6.cvs"; // Snapshot, pie, Expire Data

		String BucketChart05a = resource_location + "/BucketChart5a.cvs"; // Release, pie, Keep Data
		String BucketChart06a = resource_location + "/BucketChart6a.cvs"; // Release, pie, Expire Data

		String BucketListA = resource_location + "/BucketListA.cvs"; // Table Listing of Release Areas Summary
		String BucketListB = resource_location + "/BucketListB.cvs"; // Snapshot Table Listing
		String BucketListC = resource_location + "/BucketListC.cvs"; // Release Table Listing
		String BucketListD = resource_location + "/BucketListD.cvs"; // Captures The Date of the Report
		String BucketListE = resource_location + "/BucketListE.cvs"; // Total Expire and Release for all Areas

		String BucketListExpired = resource_location + "/ExpiredList.txt";
		writeLinesToFile(BucketListExpired, "ExpiredFile", true);

		String CaptureDate = resource_location + "/CaptureDate.cvs"; // The date the report was ran
		writeLinesToFile(CaptureDate, "Captured " + Date, true);

		BucketLifecycleConfiguration bLC = client.getBucketLifecycleConfiguration(bucketName);
		List<Rule> s3SnapshotRule = bLC.getRules();
		Rule s3_snapshot_rule = s3SnapshotRule.get(0);
		String RuleName = s3_snapshot_rule.getId();
		String RulesStatus = s3_snapshot_rule.getStatus();
		int RulesExpiration = s3_snapshot_rule.getExpirationInDays();
		String DirName = s3_snapshot_rule.getPrefix();

		String PolicyLineTitle = "RuleName,DirName,RulesExpiration(days),RulesStatus";
		writeLinesToFile(BucketListD, PolicyLineTitle, true);
		System.out.println(PolicyLineTitle);
		String PolicyLine = RuleName + "," + DirName + "," + RulesExpiration + "," + RulesStatus;
		writeLinesToFile(BucketListD, PolicyLine, false);
		System.out.println(PolicyLine);

		String PrintLine;
		long Total = 0L;
		long KeepValue = 0L;
		long ExpireValue = 0L;
		long lineValue = 0L;
		System.out.println(Total + lineValue);
		long expireValueGB = 0L;
		long keepValueGB = 0L;
		long KeepTotalGB = 0L;
		long ExpireTotalGB = 0L;

		String relAreaName = "";
		// String BuildLine;
		String sbuildtotalGB = "";
		String sreleasetotalGB = "";
		String sexternaltotalGB = "";
		String sprivatetotalGB = "";
		String ssnapshottotalGB = "";

		// BucketListA - Total of the build Areas
		PrintLine = ("RelArea,CandidateExpire GB,KeepSpace GB,Total GB");
		writeLinesToFile(BucketListA, PrintLine, true);

		// BucketListE - Total of the build Areas
		PrintLine = ("Area,CandidateExpire GB,KeepSpace GB");
		writeLinesToFile(BucketListE, PrintLine, true);
		int build = 0;
		int release = 1;
		int external = 2;
		int privatex = 3;
		int snapshot = 4;

		String relAreaArray[] = {

		"builds/", "release/", "external/", "private/", "snapshot/" };

		String[] SubAreaName = new String[1000];
		long[] ProgramAreaValue = new long[4];
		long[][][] SubAreaValue = new long[relAreaArray.length][1000][100];
		// http://shrub.appspot.com/maven.kuali.org/builds/org/kuali/ole/
		ProgramAreaValue[0] = 0;
		ProgramAreaValue[1] = 0;
		ProgramAreaValue[2] = 0;
		ProgramAreaValue[3] = 0;
		int program_index;

		int bindex = 0;
		String program_name = "";
		for (String relArea : relAreaArray) {
			System.out.println(relArea);
			program_index = 4; // rice=0 student=1 ole=2 other=3

			String delimiter = "/";
			int expireindex = 0;
			int keepindex = 1;
			for (int i = 0; i < 1000; i++) {
				SubAreaValue[bindex][i][expireindex] = 0L;
				SubAreaValue[bindex][i][keepindex] = 0L;
			} // default the SubAreas
			String dirfile;
			String previous = "";
			int key = -1;
			int i = 0;

			ObjectListing listing = client.listObjects(bucketName, relArea);
			List<S3ObjectSummary> list = listing.getObjectSummaries();

			S3ObjectSummary file;
			int SizeList = list.size();

			while (listing.isTruncated() || (!list.isEmpty())) // loop until the relArea index is exhausted, next index is introduced
			{

				file = list.get(i);
				i = i + 1; // reset after 1000 records or end of the list
				dirfile = file.getKey();
				String tmpdir[] = dirfile.split(delimiter, 0); // This is the number of directories returned.

				int NoOfDirs = tmpdir.length;
				NoOfDirs--; // re adjusted for size

				if (NoOfDirs > 3) { // don't do unless the the tree structure is at least 3 dirs deep, which is the sub-area value, if it exists.
					String lastdir = tmpdir[3];
					program_name = tmpdir[3]; // third slot has program name
					// System.out.println("program name:"+program_name+".");
					program_index = 3;
					if (program_name.contentEquals("rice")) {
						program_index = 0;
					}
					if (program_name.contentEquals("student")) {
						program_index = 1;
					}
					if (program_name.contentEquals("ole")) {
						program_index = 2;
					}

					if (!(lastdir.equals(previous))) {
						key = key + 1;
						SubAreaName[key] = lastdir;
						previous = lastdir;
					} // lastdir == previous? nothings changed;already recorded group
						// lastdir != previous? somethings changed: new group
						// catches first group because key inits -1

					Date fileLastModified = file.getLastModified();
					long filemm = fileLastModified.getTime();
					long fileSize = file.getSize();
					ProgramAreaValue[program_index] = ProgramAreaValue[program_index] + fileSize; // 4 totals of all categories
					// System.out.println(ProgramAreaValue[program_index]+" "+fileSize);
					if (filemm < cmpdate) {
						SubAreaValue[bindex][key][expireindex] = SubAreaValue[bindex][key][expireindex] + fileSize; // used in Charts to collect details under Snapshot, Release
						// BucketListListExpired
						PrintLine = (dirfile);
						writeLinesToFile(BucketListExpired, PrintLine, false);
					} // if
					else {
						SubAreaValue[bindex][key][keepindex] = SubAreaValue[bindex][key][keepindex] + fileSize;
					}
					// Let track the size by program area:

				}// if directories are deeper than 2

				if ((i == SizeList)) { // get the next bundle of listings

					listing = client.listNextBatchOfObjects(listing);
					list.clear();
					list.addAll(listing.getObjectSummaries());
					SizeList = list.size();
					// System.out.println("Size of List: "+SizeList);
					i = 0; // reset record counter
				}
			} // until the area is exhausted - while

			S3Types ThisRelease = new S3Types(relArea, SubAreaValue, key, bindex); // ThisRelease is data structure
			System.out.println("Build Area: " + ThisRelease.build);
			dataArray.add(ThisRelease);

			System.out.println("build: " + dataArray.get(bindex).build);
			System.out.println("dataArray size: " + dataArray.size());
			System.out.println("dataArray getTotal: " + dataArray.get(bindex).ReleaseAreaTotalGB);

			String TitleK = "";
			String ValueK = "";
			String commaK = "";
			String TitleE = "";
			String ValueE = "";
			String commaE = "";
			// Null all these
			TitleK = "";
			ValueK = "";
			commaK = "";
			TitleE = "";
			ValueE = "";
			commaE = "";

			// Fall through here after the summary, and lets do totals and printing for each major area

			// Add up the Total for all Areas for Keep and Expire data
			KeepTotalGB = KeepTotalGB + ThisRelease.getReleaseAreaTotalKGB();
			ExpireTotalGB = ExpireTotalGB + ThisRelease.getReleaseAreaTotalEGB();

			// BucketListA
			PrintLine = (relArea + "," + Long.toString(ThisRelease.getReleaseAreaTotalEGB()) + "," + Long.toString(ThisRelease.getReleaseAreaTotalKGB()) + "," + Long
					.toString(ThisRelease.getReleaseAreaTotalGB()));
			writeLinesToFile(BucketListA, PrintLine, false);

			if (relArea == "release/") {
				System.out.println(relArea + " " + sreleasetotalGB);
				// BucketListC
				PrintLine = ("Date,RelArea,CandidateExpire,KeepSpace,Total");
				writeLinesToFile(BucketListC, PrintLine, true);
				TitleK = "";
				ValueK = "";
				commaK = "";
				TitleE = "";
				ValueE = "";
				commaE = "";

				for (int keyx = 0; keyx <= key; keyx++) {

					KeepValue = SubAreaValue[bindex][keyx][1];
					ExpireValue = SubAreaValue[bindex][keyx][0];
					lineValue = KeepValue + ExpireValue;
					relAreaName = SubAreaName[keyx];
					// Put in GBs
					expireValueGB = ExpireValue / 1000 / 1000L;
					keepValueGB = KeepValue / 1000 / 1000L;

					// Do this for each area
					// This is for pie charts, don't want a lot of zero entries
					if (KeepValue != 0L) {
						TitleK = TitleK + commaK + relAreaName;
						ValueK = ValueK + commaK + Long.toString(keepValueGB);
						commaK = ",";
					}
					if (ExpireValue != 0L) {
						TitleE = TitleE + commaE + relAreaName;
						ValueE = ValueE + commaE + Long.toString(expireValueGB);
						commaE = ",";
					}

					PrintLine = (Date + "," + relAreaName + "," + Long.toString(expireValueGB) + "," + Long.toString(keepValueGB) + "," + Long
							.toString(keepValueGB + expireValueGB));
					writeLinesToFile(BucketListC, PrintLine, false);
				} // for loop to sum and print info

				writeLinesToFile(BucketChart05a, TitleK, true);
				writeLinesToFile(BucketChart06a, TitleE, true);
				writeLinesToFile(BucketChart05a, ValueK, false);
				writeLinesToFile(BucketChart06a, ValueE, false);
			}

			if (relArea == "snapshot/") {

				// BucketListB
				PrintLine = ("Date,RelArea,CandidateExpire,KeepSpace,Total");
				writeLinesToFile(BucketListB, PrintLine, true);

				for (int keyx = 0; keyx <= key; keyx++) {

					KeepValue = SubAreaValue[bindex][keyx][1];
					ExpireValue = SubAreaValue[bindex][keyx][0];
					lineValue = KeepValue + ExpireValue;

					relAreaName = SubAreaName[keyx];

					// Put in GBs
					expireValueGB = ExpireValue / 1000 / 1000L;
					keepValueGB = KeepValue / 1000 / 1000L;
					// lineValueGB = lineValue/1000/1000L;

					// Do this for each area
					// This is for pie charts, don't want a lot of zero entries
					if (KeepValue != 0L) {
						TitleK = TitleK + commaK + relAreaName;
						ValueK = ValueK + commaK + Long.toString(keepValueGB);
						commaK = ",";
					}
					if (ExpireValue != 0L) {
						TitleE = TitleE + commaE + relAreaName;
						ValueE = ValueE + commaE + Long.toString(expireValueGB);
						commaE = ",";
					}

					PrintLine = (Date + "," + relAreaName + "," + Long.toString(expireValueGB) + "," + Long.toString(keepValueGB) + "," + Long
							.toString(keepValueGB + expireValueGB));
					writeLinesToFile(BucketListB, PrintLine, false);
				} // for loop to sum and print info

				// Print Pie Charts for Keep and Expire
				writeLinesToFile(BucketChart05, TitleK, true);
				writeLinesToFile(BucketChart05, ValueK, false);
				writeLinesToFile(BucketChart06, TitleE, true);
				writeLinesToFile(BucketChart06, ValueE, false);

			}
			bindex++;
		} // for each major area

		// debug
		// System.out.println("build: "+dataArray.get(1).build);
		// System.out.println("dataArray size: "+dataArray.size());
		// System.out.println("dataArray getTotal: "+dataArray.get(1).ReleaseAreaTotalGB);

		sbuildtotalGB = Long.toString(dataArray.get(build).ReleaseAreaTotalGB);
		sreleasetotalGB = Long.toString(dataArray.get(release).ReleaseAreaTotalGB);
		sexternaltotalGB = Long.toString(dataArray.get(external).ReleaseAreaTotalGB);
		sprivatetotalGB = Long.toString(dataArray.get(privatex).ReleaseAreaTotalGB);
		ssnapshottotalGB = Long.toString(dataArray.get(snapshot).ReleaseAreaTotalGB);

		// PrintLine = ("A, "+sRicetotalGB|","+sStudenttotalGB+","+sOletotalGB);

		String sRicetotalGB = Long.toString(ProgramAreaValue[0] / 1000 / 1000L);
		String sStudenttotalGB = Long.toString(ProgramAreaValue[1] / 1000 / 1000L);
		String sOletotalGB = Long.toString(ProgramAreaValue[2] / 1000 / 1000L);
		String sOthertotalGB = Long.toString(ProgramAreaValue[3] / 1000 / 1000L);

		PrintLine = (" A, Expire-GB, Keep-GB");
		writeLinesToFile(BucketChart03, PrintLine, true);
		PrintLine = ("builds" + "," + Long.toString(dataArray.get(build).ReleaseAreaTotalEGB) + "," + Long.toString(dataArray.get(build).ReleaseAreaTotalKGB));
		writeLinesToFile(BucketChart03, PrintLine, false);

		// Chart01
		PrintLine = (" Program, Rice, Student, Ole, Other");
		writeLinesToFile(BucketChart01, PrintLine, true);
		// PrintLine = ("A, "+sbuildtotalGB +","+ssnapshottotalGB +","+sreleasetotalGB);
		PrintLine = ("A, " + sRicetotalGB + "," + sStudenttotalGB + "," + sOletotalGB + "," + sOthertotalGB);
		writeLinesToFile(BucketChart01, PrintLine, false);

		// Chart01a
		PrintLine = (" RelArea, Builds, Snapshot, Release, External, Private");
		writeLinesToFile(BucketChart01a, PrintLine, true);
		PrintLine = ("A, " + sbuildtotalGB + "," + ssnapshottotalGB + "," + sreleasetotalGB + "," + sexternaltotalGB + "," + sprivatetotalGB);

		writeLinesToFile(BucketChart01a, PrintLine, false);

		System.out.println("BucketChart01a: " + PrintLine);

		// BucketChart02
		PrintLine = (" A, Expire-GB, Keep-GB");
		writeLinesToFile(BucketChart02, PrintLine, true);
		PrintLine = ("snapshot" + "," + Long.toString(dataArray.get(snapshot).ReleaseAreaTotalEGB) + "," + Long.toString(dataArray.get(snapshot).ReleaseAreaTotalKGB));
		writeLinesToFile(BucketChart02, PrintLine, false);

		// BucketListA - details
		// PrintLine = ("snapshots" +","+Long.toString(dataArray.get(snapshot).ReleaseAreaTotalEGB)
		// +","+Long.toString(dataArray.get(snapshot).ReleaseAreaTotalKGB)+","+Long.toString(dataArray.get(snapshot).ReleaseAreaTotalGB));
		// writeLinesToFile(BucketListA, PrintLine, false);

		// BucketChart04
		PrintLine = (" A, Expire-GB, Keep-GB");
		writeLinesToFile(BucketChart04, PrintLine, true);
		PrintLine = ("release" + "," + Long.toString(dataArray.get(release).ReleaseAreaTotalEGB) + "," + Long.toString(dataArray.get(release).ReleaseAreaTotalKGB));
		writeLinesToFile(BucketChart04, PrintLine, false);

		// BucketChart02a
		PrintLine = (" A, Expire-GB, Keep-GB");
		writeLinesToFile(BucketChart02a, PrintLine, true);
		PrintLine = ("external" + "," + Long.toString(dataArray.get(external).ReleaseAreaTotalEGB) + "," + Long.toString(dataArray.get(external).ReleaseAreaTotalKGB));
		writeLinesToFile(BucketChart02a, PrintLine, false);

		// BucketChart02b
		PrintLine = (" A, Expire-GB, Keep-GB");
		writeLinesToFile(BucketChart02b, PrintLine, true);
		PrintLine = ("private" + "," + Long.toString(dataArray.get(privatex).ReleaseAreaTotalEGB) + "," + Long.toString(dataArray.get(privatex).ReleaseAreaTotalKGB));
		writeLinesToFile(BucketChart02b, PrintLine, false);

		System.out.println("BucketChart01a: " + PrintLine);

		// BucketListE
		PrintLine = ("A ," + Long.toString(ExpireTotalGB) + "," + Long.toString(KeepTotalGB));
		writeLinesToFile(BucketListE, PrintLine, false);

	};

}; // subroutine

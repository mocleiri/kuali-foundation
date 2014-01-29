package org.kuali.common.aws.status;

import static com.google.common.base.Preconditions.checkArgument;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.kuali.common.aws.status.model.DnsmeRecord;
import org.kuali.common.util.file.CanonicalFile;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;

public class MergeTest {

	private static final Splitter SPLITTER = Splitter.on(',');

	private static final String DNSME = "./target/dnsme/records.csv";
	private static final String AWS = GetStatusTest.OUTPUT_PATH;

	@Test
	public void test() {
		try {
			System.setProperty("dnsme.input", "/Users/jcaddel/sts/3.1.0.RELEASE/workspace/kuali-dns/target/dnsme/records.csv");
			File dnsme = new CanonicalFile(System.getProperty("dnsme.input", DNSME));
			File aws = new CanonicalFile(System.getProperty("aws.input", AWS));
			checkArgument(dnsme.exists(), "[%s] does not exist", dnsme);
			checkArgument(aws.exists(), "[%s] does not exist", aws);
			List<DnsmeRecord> drecs = getDnsmeRecords(dnsme);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	protected List<DnsmeRecord> getDnsmeRecords(File file) throws IOException {
		List<String> lines = FileUtils.readLines(file);
		List<DnsmeRecord> records = Lists.newArrayList();
		for (String line : lines) {
			List<String> tokens = Lists.newArrayList(SPLITTER.split(line));
			DnsmeRecord record = new DnsmeRecord();
			record.setDns(tokens.get(0));
			record.setAlias(tokens.get(1));
			record.setType(tokens.get(2));
			record.setTtl(tokens.get(3));
			records.add(record);
		}
		return records;

	}

}

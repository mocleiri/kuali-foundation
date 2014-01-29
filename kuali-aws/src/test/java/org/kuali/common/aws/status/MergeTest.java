package org.kuali.common.aws.status;

import static com.google.common.base.Preconditions.checkArgument;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.kuali.common.aws.status.model.AwsRecord;
import org.kuali.common.aws.status.model.DnsmeRecord;
import org.kuali.common.aws.status.model.Environment;
import org.kuali.common.util.file.CanonicalFile;
import org.kuali.common.util.log.LoggerUtils;
import org.slf4j.Logger;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;

public class MergeTest {

	private static final Splitter SPLITTER = Splitter.on(',');

	private static final String DNSME = "./target/dnsme/records.csv";
	private static final String AWS = GetStatusTest.OUTPUT_PATH;
	private static final String KUALI = "./target/kuali/envs.csv";

	private static final Logger logger = LoggerUtils.make();

	@Test
	public void test() {
		try {
			System.setProperty("dnsme.input", "/Users/jcaddel/sts/3.1.0.RELEASE/workspace/kuali-dns/target/dnsme/records.csv");
			File dnsme = new CanonicalFile(System.getProperty("dnsme.input", DNSME));
			File aws = new CanonicalFile(System.getProperty("aws.input", AWS));
			checkArgument(dnsme.exists(), "[%s] does not exist", dnsme);
			checkArgument(aws.exists(), "[%s] does not exist", aws);
			List<DnsmeRecord> drecs = getDnsmeRecords(dnsme);
			List<AwsRecord> arecs = getAwsRecords(aws);
			List<Environment> envs = merge(drecs, arecs);
			logger.info("Merged %s records", envs.size());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	protected List<Environment> merge(List<DnsmeRecord> drecs, List<AwsRecord> arecs) {
		List<Environment> envs = Lists.newArrayList();
		for (AwsRecord arec : arecs) {
			DnsmeRecord drec = findRecord(arec, drecs);
			Environment env = new Environment();
			env.setProject(arec.getProject());
			env.setId(arec.getEnv());
			env.setDns(drec.getDns() + ".kuali.org");
			env.setType(arec.getType());
			envs.add(env);
		}
		return envs;
	}

	protected DnsmeRecord findRecord(AwsRecord arec, List<DnsmeRecord> drecs) {
		for (DnsmeRecord drec : drecs) {
			if (drec.getAlias().equals(arec.getDns())) {
				return drec;
			}
		}
		throw new IllegalStateException(String.format("No corresponding DNSME entry for [%s=%s]", arec.getEnv(), arec.getDns()));
	}

	protected List<AwsRecord> getAwsRecords(File file) throws IOException {
		List<String> lines = FileUtils.readLines(file);
		List<AwsRecord> records = Lists.newArrayList();
		for (int i = 1; i < lines.size(); i++) {
			String line = lines.get(i);
			List<String> tokens = Lists.newArrayList(SPLITTER.split(line));
			AwsRecord record = new AwsRecord();
			record.setProject(tokens.get(0));
			record.setEnv(tokens.get(1));
			record.setDns(tokens.get(2));
			record.setType(tokens.get(3));
			records.add(record);
		}
		return records;

	}

	protected List<DnsmeRecord> getDnsmeRecords(File file) throws IOException {
		List<String> lines = FileUtils.readLines(file);
		List<DnsmeRecord> records = Lists.newArrayList();
		for (int i = 1; i < lines.size(); i++) {
			String line = lines.get(i);
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

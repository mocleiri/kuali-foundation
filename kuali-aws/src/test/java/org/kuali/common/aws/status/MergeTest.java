package org.kuali.common.aws.status;

import static com.google.common.base.Preconditions.checkArgument;

import java.io.File;

import org.junit.Test;
import org.kuali.common.util.file.CanonicalFile;
import org.kuali.common.util.log.LoggerUtils;
import org.slf4j.Logger;

import com.google.common.base.Joiner;

public class MergeTest {

	private static final Logger logger = LoggerUtils.make();
	private static final Joiner JOINER = Joiner.on(',');
	private static final Joiner LINES = Joiner.on('\n');

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
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

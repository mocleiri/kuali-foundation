/**
 * Copyright 2004-2014 The Kuali Foundation
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

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;

public class MergeTest {

	private static final Splitter SPLITTER = Splitter.on(',');
	private static final Joiner JOINER = Joiner.on(',');

	private static final String DNSME = "./target/dnsme/records.csv";
	private static final String AWS = GetStatusTest.OUTPUT_PATH;
	private static final String KUALI = "./target/kuali/envs.csv";

	private static final Logger logger = LoggerUtils.make();

	@Test
	public void test() {
		try {
			// System.setProperty("dnsme.in", "/Users/jcaddel/sts/3.1.0.RELEASE/workspace/kuali-dns/target/dnsme/records.csv");
			File dnsme = new CanonicalFile(System.getProperty("dnsme.in", DNSME));
			File aws = new CanonicalFile(System.getProperty("aws.in", AWS));
			File kuali = new CanonicalFile(System.getProperty("kuali.out", KUALI));
			checkArgument(dnsme.exists(), "[%s] does not exist", dnsme);
			checkArgument(aws.exists(), "[%s] does not exist", aws);
			List<DnsmeRecord> drecs = getDnsmeRecords(dnsme);
			List<AwsRecord> arecs = getAwsRecords(aws);
			List<Environment> envs = merge(drecs, arecs);
			logger.info(String.format("Merged %s records", envs.size()));
			List<String> lines = getLines(envs);
			FileUtils.writeLines(kuali, lines);
			logger.info(String.format("created -> [%s]", kuali));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	protected List<String> getLines(List<Environment> envs) {
		List<String> lines = Lists.newArrayList();
		lines.add("project,env,fqdn,type");
		for (Environment env : envs) {
			lines.add(getLine(env));
		}
		return lines;
	}

	protected String getLine(Environment env) {
		List<String> tokens = Lists.newArrayList();
		tokens.add(env.getProject());
		tokens.add(env.getId());
		tokens.add(env.getDns());
		tokens.add(env.getType());
		return JOINER.join(tokens);
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
		throw new IllegalStateException(String.format("No corresponding DNSME entry for %s - [%s=%s]", arec.getProject(), arec.getEnv(), arec.getDns()));
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

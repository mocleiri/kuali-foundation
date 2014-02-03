package org.kuali.common.devops.status;

import java.util.Map;

import org.junit.Test;
import org.kuali.common.devops.logic.DNS;
import org.kuali.common.util.log.LoggerUtils;
import org.slf4j.Logger;

public class DNSTest {

	private static final Logger logger = LoggerUtils.make();

	@Test
	public void test() {
		Map<String, String> all = DNS.getCNAMERecords(false);
		logger.info(String.format("cname records -> %s", all.size()));
		Map<String, String> unambiguous = DNS.getUnambiguousCNAMERecords(false);
		logger.info(String.format("unambiguous cname records -> %s", unambiguous.size()));
	}
}

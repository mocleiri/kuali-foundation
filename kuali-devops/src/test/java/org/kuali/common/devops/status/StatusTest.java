package org.kuali.common.devops.status;

import static com.google.common.base.Preconditions.checkState;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.kuali.common.devops.util.AwsRecord;
import org.kuali.common.devops.util.Environment;
import org.kuali.common.devops.util.Fqdns;
import org.kuali.common.devops.util.Instances;
import org.kuali.common.util.log.LoggerUtils;
import org.slf4j.Logger;

import com.amazonaws.services.ec2.model.Instance;
import com.google.common.collect.Lists;

public class StatusTest {

	private static final Logger logger = LoggerUtils.make();

	@Test
	public void test() {
		try {
			Map<String, String> fqdns = Fqdns.getMap();
			Map<String, List<Instance>> instances = Instances.getMap();
			List<AwsRecord> records = Instances.getRecords(instances);
			List<Environment> envs = merge(records, fqdns);
			logger.info("Located {} managed environments", envs.size());
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	protected List<Environment> merge(List<AwsRecord> records, Map<String, String> fqdns) {
		List<Environment> envs = Lists.newArrayList();
		for (AwsRecord record : records) {
			Environment env = new Environment();
			env.setId(record.getEnv());
			env.setProject(record.getProject());
			env.setType(record.getType());
			String fqdn = fqdns.get(record.getDns());
			checkState(fqdn != null, "No DNSME record for %s [%s=%s]", record.getProject(), record.getEnv(), record.getDns());
			env.setDns(fqdn);
			envs.add(env);
		}
		return envs;
	}

}

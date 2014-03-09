package org.kuali.common.devops.ci;

import static java.lang.String.format;
import static org.kuali.common.util.log.Loggers.newLogger;

import org.junit.Test;
import org.kuali.common.devops.logic.Auth;
import org.slf4j.Logger;

import com.amazonaws.auth.AWSCredentials;

public class CreateBuildSlaveAMI {

	private static final Logger logger = newLogger();

	@Test
	public void test() {
		try {
			AWSCredentials creds = Auth.getAwsCredentials("foundation");
			logger.info(format("access key id: %s", creds.getAWSAccessKeyId()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

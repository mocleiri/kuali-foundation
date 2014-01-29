package org.kuali.common.aws.status;

import java.util.List;

import org.jasypt.util.text.TextEncryptor;
import org.junit.Test;
import org.kuali.common.aws.Credentials;
import org.kuali.common.aws.ec2.api.EC2Service;
import org.kuali.common.aws.ec2.impl.DefaultEC2Service;
import org.kuali.common.aws.ec2.model.EC2ServiceContext;
import org.kuali.common.util.enc.EncUtils;
import org.kuali.common.util.log.LoggerUtils;
import org.kuali.common.util.wait.DefaultWaitService;
import org.kuali.common.util.wait.WaitService;
import org.slf4j.Logger;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.ec2.model.Instance;
import com.google.common.collect.Lists;

public class GetStatusTest {

	private static final Logger logger = LoggerUtils.make();

	@Test
	public void test() {
		try {
			String password = Passwords.getEncPassword();
			List<AWSCredentials> creds = getCredentials(password);
			logger.info(String.format("Located %s sets of credentials", creds.size()));
			WaitService ws = new DefaultWaitService();
			for (AWSCredentials credentials : creds) {
				EC2ServiceContext context = EC2ServiceContext.create(credentials);
				EC2Service service = new DefaultEC2Service(context, ws);
				List<Instance> instances = service.getInstances();
				logger.info(String.format("Located %s instances for %s", instances.size(), credentials.getAWSAccessKeyId()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected List<AWSCredentials> getCredentials(String password) {
		TextEncryptor enc = EncUtils.getTextEncryptor(password);
		List<AWSCredentials> list = Lists.newArrayList();
		for (AWSCredentials credentials : Credentials.values()) {
			String accessKey = credentials.getAWSAccessKeyId();
			String secretKey = credentials.getAWSSecretKey();
			if (EncUtils.isEncrypted(secretKey)) {
				secretKey = enc.decrypt(EncUtils.unwrap(secretKey));
			}
			list.add(new BasicAWSCredentials(accessKey, secretKey));
		}
		return list;
	}

}

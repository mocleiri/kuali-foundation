package org.kuali.common.aws.ec2;

import static org.kuali.common.util.log.Loggers.newLogger;

import java.util.List;

import org.junit.Test;
import org.kuali.common.aws.Credentials;
import org.kuali.common.aws.ec2.impl.DefaultEC2Service;
import org.kuali.common.aws.ec2.model.EC2ServiceContext;
import org.kuali.common.aws.status.Auth;
import org.kuali.common.util.wait.DefaultWaitService;
import org.slf4j.Logger;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.services.ec2.model.Image;
import com.amazonaws.services.ec2.model.Tag;

public class DefaultEC2ServiceTest {

	private static final Logger logger = newLogger();

	@Test
	public void test() {
		try {
			AWSCredentials credentials = Auth.getCredentials(Credentials.FOUNDATION);
			EC2ServiceContext context = EC2ServiceContext.create(credentials);
			DefaultEC2Service service = new DefaultEC2Service(context, new DefaultWaitService());
			List<Image> images = service.getMyImages();
			logger.info(images.size() + "");
			for (Image image : images) {
				List<Tag> tags = image.getTags();
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

}

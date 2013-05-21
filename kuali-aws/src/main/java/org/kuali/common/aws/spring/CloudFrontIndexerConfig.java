package org.kuali.common.aws.spring;

import org.kuali.common.aws.s3.BucketConstants;
import org.kuali.common.aws.s3.BucketContext;
import org.kuali.common.util.spring.SpringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class CloudFrontIndexerConfig {

	@Autowired
	Environment env;

	@Bean
	public BucketContext awsBucketContext() {
		String delimiter = SpringUtils.getProperty(env, "s3.delimiter", BucketConstants.DEFAULT_DELIMITER);
		int maxKeys = SpringUtils.getInteger(env, "s3.maxKeys", BucketConstants.DEFAULT_MAX_KEYS);
		String bucket = SpringUtils.getProperty(env, "s3.bucket");

		BucketContext context = new BucketContext();
		context.setDelimiter(delimiter);
		context.setMaxKeys(maxKeys);
		context.setName(bucket);
		return context;
	}

}

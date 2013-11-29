package org.kuali.common.aws.spring;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;

public interface AwsCredentialsConfig {

	AWSCredentialsProvider awsCredentialsProvider();

	AWSCredentials awsCredentials();

}

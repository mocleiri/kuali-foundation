package org.kuali.common.aws.spring;

import org.kuali.common.aws.model.AwsAccount;

import com.google.common.base.Optional;

public interface AwsAccountConfig {

	Optional<AwsAccount> awsAccount();

}

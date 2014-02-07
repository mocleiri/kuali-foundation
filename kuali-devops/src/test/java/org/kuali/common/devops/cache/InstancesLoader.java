package org.kuali.common.devops.cache;

import static org.kuali.common.util.base.Precondition.checkNotBlank;

import java.util.List;

import org.kuali.common.devops.logic.Auth;
import org.kuali.common.devops.logic.Instances;
import org.kuali.common.devops.model.EC2Instance;

import com.amazonaws.auth.AWSCredentials;
import com.google.common.cache.CacheLoader;

public final class InstancesLoader extends CacheLoader<String, List<EC2Instance>> {

	@Override
	public List<EC2Instance> load(String account) {
		checkNotBlank(account, "account");
		AWSCredentials credentials = Auth.getAwsCredentials(account);
		return Instances.queryAmazon(credentials);
	}

}

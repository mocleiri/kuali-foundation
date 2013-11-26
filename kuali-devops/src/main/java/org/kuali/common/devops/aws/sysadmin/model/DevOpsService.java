package org.kuali.common.devops.aws.sysadmin.model;

import org.kuali.common.dns.util.DnsUtils;
import org.kuali.common.util.Assert;

import com.google.common.base.Optional;

public final class DevOpsService {

	private final String name;
	private final String instanceId;
	private final Optional<String> fqdn;

	public static class Builder {

		// Required
		private final String name;
		private final String instanceId;

		// Optional
		private Optional<String> fqdn = Optional.absent(); // Defaults to the public DNS name supplied by Amazon if no custom FQDN is supplied

		public Builder(String name, String instanceId) {
			this.instanceId = instanceId;
			this.name = name;
		}

		public DevOpsService build() {
			Assert.noBlanks(instanceId, name);
			Assert.noNulls(fqdn);
			if (fqdn.isPresent()) {
				DnsUtils.validateFQDN(fqdn.get());
			}
			return new DevOpsService(this);
		}

	}

	private DevOpsService(Builder builder) {
		this.instanceId = builder.instanceId;
		this.name = builder.name;
		this.fqdn = builder.fqdn;
	}

	public String getInstanceId() {
		return instanceId;
	}

	public Optional<String> getFqdn() {
		return fqdn;
	}

	public String getName() {
		return name;
	}

}

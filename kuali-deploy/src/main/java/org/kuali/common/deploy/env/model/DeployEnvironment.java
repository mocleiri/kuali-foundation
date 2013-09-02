package org.kuali.common.deploy.env.model;

import org.kuali.common.deploy.dns.model.DnsContext;
import org.kuali.common.util.Assert;

public final class DeployEnvironment {

	public DeployEnvironment(String groupId, int id, String name, DnsContext dns) {
		Assert.noBlanks(groupId, name);
		Assert.noNulls(dns);
		Assert.isTrue(id > 0, "id must be a positive integer");
		this.id = id;
		this.groupId = groupId;
		this.name = name;
		this.dns = dns;
	}

	private final int id;
	private final String groupId;
	private final String name;
	private final DnsContext dns;

	public int getId() {
		return id;
	}

	public String getGroupId() {
		return groupId;
	}

	public String getName() {
		return name;
	}

	public DnsContext getDns() {
		return dns;
	}

}

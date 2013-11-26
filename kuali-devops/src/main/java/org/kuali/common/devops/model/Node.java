package org.kuali.common.devops.model;

import org.kuali.common.util.Assert;

public final class Node {

	private final String name;
	private final String fqdn;
	private final String instanceId;

	public static class Builder {

		// Required
		private final String name;
		private String fqdn;
		private final String instanceId;

		// Optional

		public Builder(String name, String fqdn, String instanceId) {
			this.name = name;
			this.fqdn = fqdn;
			this.instanceId = instanceId;
		}

		public Node build() {
			Assert.noBlanks(name, fqdn, instanceId);
			return new Node(this);
		}

	}

	private Node(Builder builder) {
		this.instanceId = builder.instanceId;
		this.name = builder.name;
		this.fqdn = builder.fqdn;
	}

	public String getName() {
		return name;
	}

	public String getInstanceId() {
		return instanceId;
	}

	public String getFqdn() {
		return fqdn;
	}

}

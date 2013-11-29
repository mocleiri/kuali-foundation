package org.kuali.common.devops.model;

import org.kuali.common.util.Assert;

public final class Node {

	private final String name;
	private final String fqdn;

	public static class Builder {

		// Required
		private final String name;
		private final String fqdn;

		public Builder(String name, String fqdn) {
			this.name = name;
			this.fqdn = fqdn;
		}

		public Node build() {
			Assert.noBlanks(name, fqdn);
			return new Node(this);
		}

	}

	private Node(Builder builder) {
		this.name = builder.name;
		this.fqdn = builder.fqdn;
	}

	public String getName() {
		return name;
	}

	public String getFqdn() {
		return fqdn;
	}

}

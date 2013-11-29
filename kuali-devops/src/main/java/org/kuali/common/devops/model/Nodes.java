package org.kuali.common.devops.model;

public enum Nodes {

	NEXUS("nexus", "nexus.kuali.org"), //
	CI("ci", "ci.kuali.org"), //
	SONAR("sonar", "sonar.kuali.org"); //

	private final Node node;

	private Nodes(String name, String fqdn) {
		this.node = new Node.Builder(name, fqdn).build();
	}

	public Node getNode() {
		return node;
	}

}

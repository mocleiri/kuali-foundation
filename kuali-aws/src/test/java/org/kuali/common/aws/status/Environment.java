package org.kuali.common.aws.status;

public class Environment {

	String project;
	String id;
	String amazonDns;
	String friendlyDns;
	String type;

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAmazonDns() {
		return amazonDns;
	}

	public void setAmazonDns(String amazonDns) {
		this.amazonDns = amazonDns;
	}

	public String getFriendlyDns() {
		return friendlyDns;
	}

	public void setFriendlyDns(String friendlyDns) {
		this.friendlyDns = friendlyDns;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}

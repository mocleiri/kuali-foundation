package org.kuali.common.util;

import java.util.Properties;

public class Project {

	// org.kuali
	String orgId;

	// org/kuali
	String orgPath;

	// kuali
	String orgCode;

	// org.kuali.student.web
	String groupId;

	// org.kuali.student
	String groupIdBase;

	// student
	String groupCode;

	// ks-with-rice-bundled
	String artifactId;

	// 2.0.0
	String version;

	// Optional - typically "webapp" or something similar
	String classifier;

	// KS with Rice Bundled
	String name;

	// Kuali Student bundled as a completely standalone application that includes Kuali Rice bundled inside
	String description;

	// UTF-8
	String encoding;

	// Jar, war
	String packaging;

	// Allow for storage of miscellaneous other properties related to the project
	Properties properties;

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getGroupIdBase() {
		return groupIdBase;
	}

	public void setGroupIdBase(String groupIdBase) {
		this.groupIdBase = groupIdBase;
	}

	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	public String getArtifactId() {
		return artifactId;
	}

	public void setArtifactId(String artifactId) {
		this.artifactId = artifactId;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	public String getOrgPath() {
		return orgPath;
	}

	public void setOrgPath(String orgPath) {
		this.orgPath = orgPath;
	}

	public String getClassifier() {
		return classifier;
	}

	public void setClassifier(String classifier) {
		this.classifier = classifier;
	}

	public String getPackaging() {
		return packaging;
	}

	public void setPackaging(String packaging) {
		this.packaging = packaging;
	}

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

}

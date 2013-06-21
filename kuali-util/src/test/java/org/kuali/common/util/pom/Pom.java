package org.kuali.common.util.pom;

public class Pom {

	Line artifactId;
	Line description;
	Line name;
	Line version;
	Line packaging;
	Line endParentTag;

	public Line getArtifactId() {
		return artifactId;
	}

	public void setArtifactId(Line artifactId) {
		this.artifactId = artifactId;
	}

	public Line getDescription() {
		return description;
	}

	public void setDescription(Line description) {
		this.description = description;
	}

	public Line getName() {
		return name;
	}

	public void setName(Line name) {
		this.name = name;
	}

	public Line getVersion() {
		return version;
	}

	public void setVersion(Line version) {
		this.version = version;
	}

	public Line getPackaging() {
		return packaging;
	}

	public void setPackaging(Line packaging) {
		this.packaging = packaging;
	}

	public Line getEndParentTag() {
		return endParentTag;
	}

	public void setEndParentTag(Line endParentTag) {
		this.endParentTag = endParentTag;
	}

}

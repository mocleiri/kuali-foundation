package org.kuali.common.aws.s3.cloudfront;

import org.kuali.common.aws.s3.BucketContext;
import org.kuali.common.util.Project;

public class CloudFrontHtmlGeneratorContext {

	public static final String DEFAULT_CSS = "http://s3browse.ks.kuali.org/css/style.css";

	String css = DEFAULT_CSS;
	Project project;
	BucketContext bucketContext;

	public String getCss() {
		return css;
	}

	public void setCss(String css) {
		this.css = css;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public BucketContext getBucketContext() {
		return bucketContext;
	}

	public void setBucketContext(BucketContext bucketContext) {
		this.bucketContext = bucketContext;
	}

}

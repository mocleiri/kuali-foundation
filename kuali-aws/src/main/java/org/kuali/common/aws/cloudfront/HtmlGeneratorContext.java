package org.kuali.common.aws.cloudfront;

import org.kuali.common.aws.s3.BucketContext;
import org.kuali.common.util.Project;

public class HtmlGeneratorContext {

	public static final String DEFAULT_CSS = "http://s3browse.ks.kuali.org/css/style.css";

	String css = DEFAULT_CSS;
	String dateDisplayFormat = CloudFrontConstants.DATE_DISPLAY_FORMAT;
	String dateDisplayTimeZone = CloudFrontConstants.DATE_DISPLAY_TIMEZONE;
	String googleAnalyticsAccount = CloudFrontConstants.GOOGLE_ANALYTICS_ACCOUNT;
	String encoding = CloudFrontConstants.ENCODING;

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

	public String getDateDisplayFormat() {
		return dateDisplayFormat;
	}

	public void setDateDisplayFormat(String dateDisplayFormat) {
		this.dateDisplayFormat = dateDisplayFormat;
	}

	public String getDateDisplayTimeZone() {
		return dateDisplayTimeZone;
	}

	public void setDateDisplayTimeZone(String dateDisplayTimeZone) {
		this.dateDisplayTimeZone = dateDisplayTimeZone;
	}

	public String getGoogleAnalyticsAccount() {
		return googleAnalyticsAccount;
	}

	public void setGoogleAnalyticsAccount(String googleAnalyticsAccount) {
		this.googleAnalyticsAccount = googleAnalyticsAccount;
	}

	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

}

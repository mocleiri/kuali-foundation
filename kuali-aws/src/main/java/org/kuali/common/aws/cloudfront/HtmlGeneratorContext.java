package org.kuali.common.aws.cloudfront;

public class HtmlGeneratorContext {

	String css = CloudFrontConstants.DEFAULT_CSS;
	String dateDisplayFormat = CloudFrontConstants.DATE_DISPLAY_FORMAT;
	String dateDisplayTimeZone = CloudFrontConstants.DATE_DISPLAY_TIMEZONE;
	String googleAnalyticsAccount = CloudFrontConstants.GOOGLE_ANALYTICS_ACCOUNT;
	String encoding = CloudFrontConstants.ENCODING;

	public String getCss() {
		return css;
	}

	public void setCss(String css) {
		this.css = css;
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

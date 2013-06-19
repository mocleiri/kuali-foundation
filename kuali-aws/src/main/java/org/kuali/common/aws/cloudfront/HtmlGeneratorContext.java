package org.kuali.common.aws.cloudfront;

public class HtmlGeneratorContext {

	String css = CloudFrontConstants.DEFAULT_CSS;
	String dateDisplayFormat = CloudFrontConstants.DATE_DISPLAY_FORMAT;
	String dateDisplayTimeZone = CloudFrontConstants.DATE_DISPLAY_TIMEZONE;
	String encoding = CloudFrontConstants.ENCODING;
	String googleAnalyticsAccount = CloudFrontConstants.GOOGLE_ANALYTICS_ACCOUNT;
	String googleAnalyticsDomainName = CloudFrontConstants.GOOGLE_ANALYTICS_DOMAIN_NAME;

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

	public String getGoogleAnalyticsDomainName() {
		return googleAnalyticsDomainName;
	}

	public void setGoogleAnalyticsDomainName(String googleAnalyticsDomainName) {
		this.googleAnalyticsDomainName = googleAnalyticsDomainName;
	}

}

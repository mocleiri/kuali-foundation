/**
 * Copyright 2004-2014 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl2.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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

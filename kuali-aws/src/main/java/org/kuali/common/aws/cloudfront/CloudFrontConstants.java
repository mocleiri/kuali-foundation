package org.kuali.common.aws.cloudfront;

import java.util.Arrays;
import java.util.List;

import com.amazonaws.services.s3.model.CannedAccessControlList;

public abstract class CloudFrontConstants {

	public static final String DATE_DISPLAY_FORMAT = "EEE, dd MMM yyyy HH:mm:ss z";
	public static final String DATE_DISPLAY_TIMEZONE = "UTC";
	public static final String GOOGLE_ANALYTICS_ACCOUNT = "UA-16781661-1";
	public static final String ENCODING = "UTF-8";
	public static final String DEFAULT_CACHE_CONTROL = "max-age=3600, must-revalidate";
	public static final CannedAccessControlList DEFAULT_ACL = CannedAccessControlList.PublicRead;
	public static final List<String> DEFAULT_WELCOME_FILES = Arrays.asList("index.html", "welcome.html", "portal.html");

}

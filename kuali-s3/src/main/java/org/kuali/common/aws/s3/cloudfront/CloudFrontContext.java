package org.kuali.common.aws.s3.cloudfront;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CloudFrontContext {

	public static final List<String> DEFAULT_WELCOME_FILES = Arrays.asList("index.html", "welcome.html", "portal.html");

	List<String> welcomeFiles = new ArrayList<String>(DEFAULT_WELCOME_FILES);

}

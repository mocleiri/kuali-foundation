package org.kuali.common.aws.s3.cloudfront;

import java.util.List;

public interface CloudFrontHtmlGenerator {

	String getDirectoryListing(HtmlContext context, String prefix, List<String[]> data);

}

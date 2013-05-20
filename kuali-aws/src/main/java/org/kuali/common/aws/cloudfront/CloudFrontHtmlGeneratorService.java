package org.kuali.common.aws.cloudfront;

import java.util.List;

public interface CloudFrontHtmlGeneratorService {

	String getDirectoryListing(CloudFrontHtmlGeneratorContext context, String prefix, List<String[]> data);

}

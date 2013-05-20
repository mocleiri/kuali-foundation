package org.kuali.common.aws.cloudfront;

import java.util.List;

public interface HtmlGeneratorService {

	String getDirectoryListing(HtmlGeneratorContext context, String prefix, List<String[]> data);

}

package org.kuali.common.aws.s3.cloudfront;

import java.util.List;

public interface ObjectListingConverter {

	List<String[]> convert(ObjectListingConversionContext context);

}

package org.kuali.common.aws.s3.cloudfront;

import java.util.List;

import com.amazonaws.services.s3.model.ObjectListing;

public interface ObjectListingConverter {

	List<String[]> convert(ObjectListingConverterContext context, ObjectListing listing);

}

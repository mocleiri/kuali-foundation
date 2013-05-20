package org.kuali.common.aws.cloudfront;

import java.util.List;

import com.amazonaws.services.s3.model.ObjectListing;

public interface ObjectListingConverterService {

	List<String[]> convert(ObjectListingConverterContext context, ObjectListing listing);

}

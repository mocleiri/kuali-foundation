package org.kuali.common.aws.cloudfront;

import java.util.List;

import com.amazonaws.services.s3.model.ObjectListing;

public interface ListingConverterService {

	List<IndexContext> convert(ListingConverterContext context, List<ObjectListing> listings);

}

package org.kuali.common.aws.cloudfront;

import java.util.List;

import com.amazonaws.services.s3.model.ObjectListing;

public interface ConverterService {

	List<String[]> convert(ConverterContext context, ObjectListing listing);

}

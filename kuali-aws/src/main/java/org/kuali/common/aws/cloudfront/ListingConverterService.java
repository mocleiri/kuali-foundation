package org.kuali.common.aws.cloudfront;

import java.util.List;

public interface ListingConverterService {

	List<String[]> getIndexData(IndexDataContext context);

}

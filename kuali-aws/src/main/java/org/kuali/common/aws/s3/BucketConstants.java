package org.kuali.common.aws.s3;

import org.kuali.common.util.FormatUtils;

public abstract class BucketConstants {

	public static final int DEFAULT_PREFIX_ESTIMATE = 100;
	public static final String DEFAULT_DELIMITER = "/";
	public static final int DEFAULT_MAX_KEYS = 1000;
	public static final int DEFAULT_MAX_LISTINGS = 10000;
	public static final int DEFAULT_LISTINGS_TIMEOUT_MILLIS = (int) FormatUtils.getMillis("10m");

}

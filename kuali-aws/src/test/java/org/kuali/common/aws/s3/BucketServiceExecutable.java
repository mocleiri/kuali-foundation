package org.kuali.common.aws.s3;

import org.kuali.common.util.Assert;
import org.kuali.common.util.FormatUtils;
import org.kuali.common.util.execute.Executable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BucketServiceExecutable implements Executable {

	private static final Logger logger = LoggerFactory.getLogger(BucketServiceExecutable.class);

	BucketService service;
	ObjectListingsContext context;

	@Override
	public void execute() {
		Assert.notNull(service, "service is null");
		Assert.notNull(context, "context is null");

		ListingResult result = service.getObjectListings(context);

		String elapsed = FormatUtils.getTime(result.getElapsed());
		String size = FormatUtils.getCount(result.getListings().size());

		logger.info("Time: {} Count: {}", elapsed, size);

	}

}

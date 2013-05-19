package org.kuali.common.aws.s3.cloudfront;

import org.codehaus.plexus.util.StringUtils;

public class DefaultCloudFrontService implements CloudFrontService {

	@Override
	public void createOrUpdateBrowsableIndexObjects(DirectoryContext ctx) {

		String bucket = ctx.getBucket();
		String dirPrefix = ctx.getListing().getPrefix();
		String dirNoSlashPrefix = StringUtils.substring(dirPrefix, 0, dirPrefix.length() - 1);

		String welcomeFileKey = CloudFrontUtils.getWelcomeFileKey(ctx.getListing(), ctx.getWelcomeFiles());
		if (welcomeFileKey == null) {
			CloudFrontUtils.getPutIndexObjectRequest(bucket, ctx.getCacheControl(), ctx.getIndexHtml(), dirPrefix);
		} else {
			CloudFrontUtils.getCopyObjectRequest(bucket, welcomeFileKey, dirPrefix);
		}

	}

}

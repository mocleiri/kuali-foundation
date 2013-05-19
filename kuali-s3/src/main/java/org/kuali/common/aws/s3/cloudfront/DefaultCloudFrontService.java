package org.kuali.common.aws.s3.cloudfront;

import org.codehaus.plexus.util.StringUtils;

public class DefaultCloudFrontService implements CloudFrontService {

	@Override
	public void createOrUpdateBrowsableIndexObjects(DirectoryContext ctx) {

		String bucket = ctx.getBucket();
		String dirKey = ctx.getListing().getPrefix();
		String dirNoSlashKey = StringUtils.substring(dirKey, 0, dirKey.length() - ctx.getDelimiter().length());
		String html = ctx.getIndexHtml();

		String welcomeFileKey = CloudFrontUtils.getWelcomeFileKey(ctx.getListing(), ctx.getWelcomeFiles());
		if (welcomeFileKey == null) {
			CloudFrontUtils.getPutIndexObjectRequest(bucket, ctx.getCacheControl(), html, dirKey);
		} else {
			CloudFrontUtils.getCopyObjectRequest(bucket, welcomeFileKey, dirKey);
		}
		CloudFrontUtils.getPutIndexObjectRequest(bucket, ctx.getCacheControl(), html, dirNoSlashKey);

	}

}

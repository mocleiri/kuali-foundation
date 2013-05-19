package org.kuali.common.aws.s3.cloudfront;

import org.codehaus.plexus.util.StringUtils;

public class DefaultCloudFrontService implements CloudFrontService {

	@Override
	public void createOrUpdateBrowsableIndexObjects(DirectoryContext ctx) {

		String bucket = ctx.getBucket();
		String dirKey = ctx.getListing().getPrefix();
		String dirNoSlashKey = StringUtils.substring(dirKey, 0, dirKey.length() - ctx.getDelimiter().length());
		String html = ctx.getIndexHtml();

		// Create s3://bucket/foo
		CloudFrontUtils.getPutIndexObjectRequest(bucket, ctx.getCacheControl(), html, dirNoSlashKey);
		String welcomeFileKey = CloudFrontUtils.getWelcomeFileKey(ctx.getListing(), ctx.getWelcomeFiles());
		if (welcomeFileKey == null) {
			// Create s3://bucket/foo/bar/
			CloudFrontUtils.getPutIndexObjectRequest(bucket, ctx.getCacheControl(), html, dirKey);
		} else {
			// Copy s3://bucket/foo/bar/index.html -> s3://bucket/foo/bar/
			CloudFrontUtils.getCopyObjectRequest(bucket, welcomeFileKey, dirKey);
		}

	}

}

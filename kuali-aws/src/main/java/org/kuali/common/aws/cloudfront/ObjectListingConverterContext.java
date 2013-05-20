package org.kuali.common.aws.cloudfront;

import org.kuali.common.aws.s3.BucketContext;

public class ObjectListingConverterContext {

	public static final String DEFAULT_FILE_IMAGE = "http://s3browse.ks.kuali.org/images/page_white.png";
	public static final String DEFAULT_DIR_IMAGE = "http://s3browse.ks.kuali.org/images/folder.png";
	public static final String DEFAULT_BROWSE_KEY = "browse.html";

	String fileImage = DEFAULT_FILE_IMAGE;
	String dirImage = DEFAULT_DIR_IMAGE;
	String browseKey = DEFAULT_BROWSE_KEY;
	String dateDisplayFormat = CloudFrontConstants.DATE_DISPLAY_FORMAT;
	String dateDisplayTimeZone = CloudFrontConstants.DATE_DISPLAY_TIMEZONE;

	BucketContext bucketContext;

	public BucketContext getBucketContext() {
		return bucketContext;
	}

	public void setBucketContext(BucketContext bucketContext) {
		this.bucketContext = bucketContext;
	}

	public String getFileImage() {
		return fileImage;
	}

	public void setFileImage(String fileImage) {
		this.fileImage = fileImage;
	}

	public String getDirImage() {
		return dirImage;
	}

	public void setDirImage(String dirImage) {
		this.dirImage = dirImage;
	}

	public String getBrowseKey() {
		return browseKey;
	}

	public void setBrowseKey(String browseKey) {
		this.browseKey = browseKey;
	}

	public String getDateDisplayFormat() {
		return dateDisplayFormat;
	}

	public void setDateDisplayFormat(String dateDisplayFormat) {
		this.dateDisplayFormat = dateDisplayFormat;
	}

	public String getDateDisplayTimeZone() {
		return dateDisplayTimeZone;
	}

	public void setDateDisplayTimeZone(String dateDisplayTimeZone) {
		this.dateDisplayTimeZone = dateDisplayTimeZone;
	}

}

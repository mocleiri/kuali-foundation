package org.kuali.common.aws.cloudfront;

public class ListingConverterContext {

	String fileImage = CloudFrontConstants.DEFAULT_FILE_IMAGE;
	String dirImage = CloudFrontConstants.DEFAULT_DIR_IMAGE;
	String browseKey = CloudFrontConstants.DEFAULT_BROWSE_KEY;
	String dateDisplayFormat = CloudFrontConstants.DATE_DISPLAY_FORMAT;
	String dateDisplayTimeZone = CloudFrontConstants.DATE_DISPLAY_TIMEZONE;

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

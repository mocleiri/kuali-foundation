package org.kuali.common.aws.s3.cloudfront;

import org.kuali.common.aws.s3.BucketContext;

import com.amazonaws.services.s3.model.ObjectListing;

public class ObjectListingConversionContext {

	BucketContext bucketContext;
	ObjectListing listing;
	String fileImage;
	String dirImage;
	String browseKey;

	public BucketContext getBucketContext() {
		return bucketContext;
	}

	public void setBucketContext(BucketContext bucketContext) {
		this.bucketContext = bucketContext;
	}

	public ObjectListing getListing() {
		return listing;
	}

	public void setListing(ObjectListing listing) {
		this.listing = listing;
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

}

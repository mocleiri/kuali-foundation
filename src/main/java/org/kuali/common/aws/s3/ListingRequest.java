package org.kuali.common.aws.s3;

import java.util.List;

import org.kuali.common.util.inform.PercentCompleteInformer;

public class ListingRequest {

	// IllegalStateException is thrown if either of these is exceeded
	int maxListings = BucketConstants.DEFAULT_MAX_LISTINGS;
	int timeoutMillis = BucketConstants.DEFAULT_LISTINGS_TIMEOUT_MILLIS;

	String prefix;
	List<String> includes;
	List<String> excludes;
	boolean recursive;
	PercentCompleteInformer informer;

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public List<String> getIncludes() {
		return includes;
	}

	public void setIncludes(List<String> includes) {
		this.includes = includes;
	}

	public List<String> getExcludes() {
		return excludes;
	}

	public void setExcludes(List<String> excludes) {
		this.excludes = excludes;
	}

	public boolean isRecursive() {
		return recursive;
	}

	public void setRecursive(boolean recursive) {
		this.recursive = recursive;
	}

	public PercentCompleteInformer getInformer() {
		return informer;
	}

	public void setInformer(PercentCompleteInformer informer) {
		this.informer = informer;
	}

	public int getMaxListings() {
		return maxListings;
	}

	public void setMaxListings(int maxListings) {
		this.maxListings = maxListings;
	}

	public int getTimeoutMillis() {
		return timeoutMillis;
	}

	public void setTimeoutMillis(int timeoutMillis) {
		this.timeoutMillis = timeoutMillis;
	}

}

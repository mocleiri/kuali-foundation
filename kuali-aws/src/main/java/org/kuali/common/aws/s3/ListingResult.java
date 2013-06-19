package org.kuali.common.aws.s3;

import java.util.List;

import com.amazonaws.services.s3.model.ObjectListing;

public class ListingResult {

	long startTime;
	long stopTime;
	long elapsed;
	List<ObjectListing> listings;

	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	public long getStopTime() {
		return stopTime;
	}

	public void setStopTime(long stopTime) {
		this.stopTime = stopTime;
	}

	public long getElapsed() {
		return elapsed;
	}

	public void setElapsed(long elapsed) {
		this.elapsed = elapsed;
	}

	public List<ObjectListing> getListings() {
		return listings;
	}

	public void setListings(List<ObjectListing> listings) {
		this.listings = listings;
	}

}

package org.kuali.common.impex.data.service.impl;

import org.kuali.common.util.LongCounter;

public class TableTracker {

	// Tracks total amount of data in the table
	LongCounter totalDataSize = new LongCounter();
	// Tracks total number of rows in the table
	LongCounter totalRowCount = new LongCounter();
	// Tracks number of rows since we last emptied our memory cache to disk
	LongCounter currentRowCount = new LongCounter();
	// Tracks total amount of data we've accumulated since we last emptied our memory cache to disk
	LongCounter currentDataSize = new LongCounter();

	public LongCounter getTotalDataSize() {
		return totalDataSize;
	}

	public void setTotalDataSize(LongCounter totalDataSize) {
		this.totalDataSize = totalDataSize;
	}

	public LongCounter getTotalRowCount() {
		return totalRowCount;
	}

	public void setTotalRowCount(LongCounter totalRowCount) {
		this.totalRowCount = totalRowCount;
	}

	public LongCounter getCurrentRowCount() {
		return currentRowCount;
	}

	public void setCurrentRowCount(LongCounter currentRowCount) {
		this.currentRowCount = currentRowCount;
	}

	public LongCounter getCurrentDataSize() {
		return currentDataSize;
	}

	public void setCurrentDataSize(LongCounter currentDataSize) {
		this.currentDataSize = currentDataSize;
	}

}

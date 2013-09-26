package org.kuali.common.util.file.model;

import org.kuali.common.util.Assert;

public final class FileExtension implements Comparable<FileExtension> {

	public FileExtension(String value, int count) {
		Assert.noBlanks(value);
		Assert.isTrue(count >= 0, "count is negative");
		this.value = value;
		this.count = count;
	}

	private final String value;
	private final int count;

	@Override
	public int compareTo(FileExtension other) {
		return Double.compare(count, other.getCount());
	}

	public String getValue() {
		return value;
	}

	public int getCount() {
		return count;
	}

}

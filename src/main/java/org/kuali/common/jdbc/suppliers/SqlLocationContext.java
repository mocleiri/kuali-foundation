package org.kuali.common.jdbc.suppliers;

import org.kuali.common.jdbc.reader.SqlReader;
import org.kuali.common.util.Assert;

public final class SqlLocationContext {

	public static final int DEFAULT_MAX_COUNT = 50;
	public static final int DEFAULT_MAX_SIZE = 50 * 1024;

	public SqlLocationContext(String encoding, SqlReader reader) {
		this(encoding, reader, DEFAULT_MAX_COUNT, DEFAULT_MAX_SIZE);
	}

	public SqlLocationContext(String encoding, SqlReader reader, int maxCount, int maxSize) {
		Assert.noBlanks(encoding);
		Assert.noNulls(reader);
		Assert.isTrue(maxCount > 0, "max count must be a positive integer");
		Assert.isTrue(maxSize >= 0, "max size is negative");
		this.encoding = encoding;
		this.reader = reader;
		this.maxCount = maxCount;
		this.maxSize = maxSize;
	}

	private final String encoding;
	private final SqlReader reader;
	private final int maxCount;
	private final int maxSize;

	public String getEncoding() {
		return encoding;
	}

	public SqlReader getReader() {
		return reader;
	}

	public int getMaxCount() {
		return maxCount;
	}

	public int getMaxSize() {
		return maxSize;
	}

}

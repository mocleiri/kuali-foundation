package org.kuali.common.impex.data.impl;

import org.kuali.common.impex.data.SqlProducer;
import org.kuali.common.impex.model.Schema;
import org.kuali.common.util.Assert;

public final class MpxLocationContext {

	public static final String DEFAULT_EXTENSION = "mpx";

	public MpxLocationContext(String encoding, SqlProducer producer, Schema schema) {
		this(encoding, producer, schema, DEFAULT_EXTENSION);
	}

	public MpxLocationContext(String encoding, SqlProducer producer, Schema schema, String extension) {
		Assert.noNulls(producer, schema);
		Assert.noBlanks(encoding);
		this.encoding = encoding;
		this.producer = producer;
		this.schema = schema;
		this.extension = extension;
		this.suffix = "." + extension;
	}

	private final String encoding;
	private final SqlProducer producer;
	private final Schema schema;
	private final String extension;
	private final String suffix;

	public String getEncoding() {
		return encoding;
	}

	public SqlProducer getProducer() {
		return producer;
	}

	public Schema getSchema() {
		return schema;
	}

	public String getExtension() {
		return extension;
	}

	public String getSuffix() {
		return suffix;
	}

}

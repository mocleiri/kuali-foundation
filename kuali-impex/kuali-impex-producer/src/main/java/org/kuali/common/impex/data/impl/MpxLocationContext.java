package org.kuali.common.impex.data.impl;

import org.kuali.common.impex.data.SqlProducer;
import org.kuali.common.impex.model.Schema;
import org.kuali.common.util.Assert;

public final class MpxLocationContext {

	// The MPX specification dictates "mpx" be the file extension
	public static final String DEFAULT_EXTENSION = "mpx";

	// The MPX specification dictates UTF-8 be the encoding scheme
	public static final String DEFAULT_ENCODING = "UTF-8";

	public MpxLocationContext(Schema schema, SqlProducer producer) {
		this(schema, producer, DEFAULT_EXTENSION, DEFAULT_ENCODING);
	}

	public MpxLocationContext(Schema schema, SqlProducer producer, String encoding, String extension) {
		Assert.noNulls(producer, schema);
		Assert.noBlanks(encoding, extension);
		this.schema = schema;
		this.producer = producer;
		this.encoding = encoding;
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

package org.kuali.common.jdbc.suppliers;

import org.apache.commons.lang3.StringUtils;
import org.kuali.common.util.Assert;

public class SqlSupplierFactory implements LocationSupplierFactory {

	public static final String DEFAULT_EXTENSION = "sql";

	public SqlSupplierFactory(SqlLocationContext context) {
		this(DEFAULT_EXTENSION, context);
	}

	public SqlSupplierFactory(String extension, SqlLocationContext context) {
		Assert.noBlanks(extension);
		Assert.noNulls(context);
		this.extension = extension;
		this.context = context;
		this.suffix = "." + extension;
	}

	private final SqlLocationContext context;
	private final String extension;
	private final String suffix;

	@Override
	public SqlSupplier getSupplier(String location) {
		Assert.noBlanks(location);
		Assert.isTrue(isMatch(location));
		return new SqlLocationSupplier(location, context);
	}

	@Override
	public boolean isMatch(String location) {
		return StringUtils.endsWith(location, suffix);
	}

	public String getExtension() {
		return extension;
	}

	public SqlLocationContext getContext() {
		return context;
	}

}

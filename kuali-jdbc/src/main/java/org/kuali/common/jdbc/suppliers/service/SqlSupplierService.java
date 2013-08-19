package org.kuali.common.jdbc.suppliers.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.kuali.common.jdbc.suppliers.SqlSupplier;
import org.kuali.common.util.Assert;
import org.kuali.common.util.LocationUtils;

public class SqlSupplierService implements SupplierService {

	public static final String DEFAULT_EXTENSION = "sql";

	public SqlSupplierService(String encoding) {
		this(encoding, DEFAULT_EXTENSION);
	}

	public SqlSupplierService(String encoding, String extension) {
		Assert.noBlanks(encoding, extension);
		this.encoding = encoding;
		this.extension = extension;
	}

	private final String encoding;
	private final String extension;

	@Override
	public List<SqlSupplier> getSuppliers(String resourcesLocation) {
		Assert.noBlanks(resourcesLocation);
		Assert.isTrue(LocationUtils.exists(resourcesLocation));
		List<String> locations = LocationUtils.getLocations(resourcesLocation, encoding);
		LocationUtils.validateExists(locations);
		for (String location : locations) {
			Assert.isTrue(StringUtils.endsWithIgnoreCase(location, extension));
		}
		return null;
	}

	@Override
	public SqlSupplier getSupplier(String location) {
		return null;
	}

	public String getEncoding() {
		return encoding;
	}

	public String getExtension() {
		return extension;
	}

}

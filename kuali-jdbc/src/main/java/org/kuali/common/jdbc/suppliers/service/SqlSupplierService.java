package org.kuali.common.jdbc.suppliers.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.kuali.common.jdbc.suppliers.SqlLocationSupplier;
import org.kuali.common.jdbc.suppliers.SqlSupplier;
import org.kuali.common.jdbc.suppliers.model.SqlLocationContext;
import org.kuali.common.util.Assert;
import org.kuali.common.util.LocationUtils;

public class SqlSupplierService implements SupplierService {

	public static final String DEFAULT_EXTENSION = "sql";

	public SqlSupplierService(String extension, SqlLocationContext context) {
		Assert.noBlanks(extension);
		Assert.noNulls(context);
		this.extension = extension;
		this.context = context;
	}

	private final SqlLocationContext context;
	private final String extension;

	@Override
	public List<SqlSupplier> getSuppliers(String resourcesLocation) {
		Assert.noBlanks(resourcesLocation);
		Assert.isTrue(LocationUtils.exists(resourcesLocation));
		List<String> locations = LocationUtils.getLocations(resourcesLocation, context.getEncoding());
		LocationUtils.validateExists(locations);
		for (String location : locations) {
			Assert.isTrue(StringUtils.endsWithIgnoreCase(location, extension));
		}
		return null;
	}

	@Override
	public SqlSupplier getSupplier(String location) {
		return new SqlLocationSupplier(location, context);
	}

	public String getExtension() {
		return extension;
	}

	public SqlLocationContext getContext() {
		return context;
	}

}

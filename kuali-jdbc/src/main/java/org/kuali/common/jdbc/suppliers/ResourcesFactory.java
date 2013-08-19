package org.kuali.common.jdbc.suppliers;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.kuali.common.util.Assert;
import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.LocationUtils;

public final class ResourcesFactory {

	public static final String DEFAULT_EXTENSION = "resources";

	public ResourcesFactory(List<SupplierFactory> factories) {
		this(DEFAULT_EXTENSION, factories);
	}

	public ResourcesFactory(String extension, List<SupplierFactory> factories) {
		Assert.noBlanks(extension);
		Assert.isFalse(CollectionUtils.isEmpty(factories), "Must provide at least one factory");
		this.factories = CollectionUtils.unmodifiableCopy(factories);
		this.extension = extension;
		this.suffix = "." + extension;
	}

	private final String extension;
	private final String suffix;
	private final List<SupplierFactory> factories;

	public List<SqlSupplier> getSuppliers(String location) {
		Assert.noBlanks(location);
		Assert.isTrue(isMatch(location));
		List<String> resources = LocationUtils.getLocations(location);
		List<SqlSupplier> suppliers = new ArrayList<SqlSupplier>();
		for (String resource : resources) {
			SupplierFactory factory = findFactory(resource);
			SqlSupplier supplier = factory.getSupplier(location);
			suppliers.add(supplier);
		}
		return suppliers;
	}

	protected SupplierFactory findFactory(String location) {
		for (SupplierFactory factory : factories) {
			if (factory.isMatch(location)) {
				return factory;
			}
		}
		throw new IllegalStateException("Unable to location a factory for [" + location + "]");
	}

	public boolean isMatch(String location) {
		return StringUtils.endsWith(location, suffix);
	}

	public String getExtension() {
		return extension;
	}

	public String getSuffix() {
		return suffix;
	}

	public List<SupplierFactory> getFactories() {
		return factories;
	}

}

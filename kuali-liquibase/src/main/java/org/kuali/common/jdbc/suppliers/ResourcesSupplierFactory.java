package org.kuali.common.jdbc.suppliers;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.kuali.common.util.Assert;
import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.ListUtils;
import org.kuali.common.util.LocationUtils;

public final class ResourcesSupplierFactory {

	public static final String DEFAULT_EXTENSION = "resources";

	public ResourcesSupplierFactory(LocationSupplierFactory factory) {
		this(CollectionUtils.singletonList(factory));
	}

	public ResourcesSupplierFactory(List<LocationSupplierFactory> factories) {
		this(DEFAULT_EXTENSION, factories);
	}

	public ResourcesSupplierFactory(String extension, List<LocationSupplierFactory> factories) {
		Assert.noBlanks(extension);
		Assert.isFalse(CollectionUtils.isEmpty(factories), "Must provide at least one factory");
		this.factories = ListUtils.newImmutableArrayList(factories);
		this.extension = extension;
		this.suffix = "." + extension;
	}

	private final String extension;
	private final String suffix;
	private final List<LocationSupplierFactory> factories;

	public List<SqlSupplier> getSuppliers(List<String> resourceLocations) {
		List<SqlSupplier> suppliers = new ArrayList<SqlSupplier>();
		for (String resourceLocation : resourceLocations) {
			suppliers.addAll(getSuppliers(resourceLocation));
		}
		return suppliers;
	}

	public List<SqlSupplier> getSuppliers(String resourcesLocation) {
		Assert.noBlanks(resourcesLocation);
		Assert.isTrue(isMatch(resourcesLocation));
		List<String> resources = LocationUtils.getLocations(resourcesLocation);
		List<SqlSupplier> suppliers = new ArrayList<SqlSupplier>();
		for (String resource : resources) {
			SqlSupplier supplier = getSupplier(resource);
			suppliers.add(supplier);
		}
		return suppliers;
	}

	public SqlSupplier getSupplier(String location) {
		Assert.noBlanks(location);
		LocationSupplierFactory factory = findFactory(location);
		return factory.getSupplier(location);
	}

	protected LocationSupplierFactory findFactory(String location) {
		for (LocationSupplierFactory factory : factories) {
			if (factory.isMatch(location)) {
				return factory;
			}
		}
		throw new IllegalStateException("Unable to locate a factory for [" + location + "]");
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

	public List<LocationSupplierFactory> getFactories() {
		return factories;
	}

}

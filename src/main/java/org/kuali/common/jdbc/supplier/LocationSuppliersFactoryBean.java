package org.kuali.common.jdbc.supplier;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.kuali.common.util.Assert;
import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.LocationUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.FactoryBean;

public class LocationSuppliersFactoryBean implements FactoryBean<List<LocationSupplier>> {

	public static final String DEFAULT_LIST_SUFFIX = ".list";

	String listSuffix = DEFAULT_LIST_SUFFIX;
	Properties properties;
	String property;
	Map<String, LocationSupplierSourceBean> extensionMappings;

	@Override
	public List<LocationSupplier> getObject() throws Exception {

		// Make sure we are configured correctly
		Assert.notNull(properties, "properties is null");
		Assert.notNull(property, "property is null");
		Assert.notNull(extensionMappings, "extensionMappings is null");

		// Get a list of locations using properties, prefix, and listSuffix
		List<String> locations = getLocations(properties, property, listSuffix);

		// Convert the locations into LocationSupplier's based on extension
		return getSuppliers(locations, extensionMappings);
	}

	protected List<LocationSupplier> getSuppliers(List<String> locations, Map<String, LocationSupplierSourceBean> mappings) {
		// Allocate some storage for our suppliers
		List<LocationSupplier> suppliers = new ArrayList<LocationSupplier>();

		// Cycle through the list of locations, creating one supplier per location
		for (String location : locations) {

			// Extract the extension from the location
			String extension = FilenameUtils.getExtension(location);

			// The map holds the concrete LocationSupplier implementation to use for each extension
			LocationSupplierSourceBean sourceBean = mappings.get(extension);

			// Unknown extension type
			if (sourceBean == null) {
				throw new IllegalArgumentException("Unknown extension [" + extension + "]");
			}

			// Create a new instance of the impl class
			Class<? extends LocationSupplier> supplierClass = sourceBean.getSupplierClass();
			LocationSupplier implementation = getNewInstance(supplierClass);

			// Configure the impl with default properties from the source bean
			if (sourceBean.getSupplierInstance() != null) {
				BeanUtils.copyProperties(sourceBean.getSupplierInstance(), implementation);
			}

			// Store the location on the impl
			implementation.setLocation(location);

			// Add it to the list
			suppliers.add(implementation);
		}

		// Return the fully configured list of suppliers
		return suppliers;
	}

	protected <T> T getNewInstance(Class<T> instanceClass) {
		try {
			return instanceClass.newInstance();
		} catch (IllegalAccessException e) {
			throw new IllegalArgumentException(e);
		} catch (InstantiationException e) {
			throw new IllegalArgumentException(e);
		}
	}

	protected List<String> getLocations(Properties properties, String property, String listSuffix) {

		// Extract the list of property keys (comma delimited)
		String csv = properties.getProperty(property);

		// If no keys were provided, we are done
		if (StringUtils.isBlank(csv)) {
			return new ArrayList<String>();
		}

		// Parse the property keys into a list
		List<String> keys = CollectionUtils.getTrimmedListFromCSV(csv);

		// Allocate some storage for the locations we find
		List<String> locations = new ArrayList<String>();
		for (String key : keys) {

			// This is a either a list of locations or a location itself
			String value = properties.getProperty(key);

			if (StringUtils.endsWithIgnoreCase(key, listSuffix)) {
				// If the key ends with .list, it's a list of locations
				locations.addAll(LocationUtils.getLocations(value));
			} else {
				// Otherwise it is a location itself
				locations.add(value);
			}
		}
		return locations;
	}

	@Override
	public Class<?> getObjectType() {
		return List.class;
	}

	@Override
	public boolean isSingleton() {
		return false;
	}

	public String getListSuffix() {
		return listSuffix;
	}

	public void setListSuffix(String listSuffix) {
		this.listSuffix = listSuffix;
	}

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	public Map<String, LocationSupplierSourceBean> getExtensionMappings() {
		return extensionMappings;
	}

	public void setExtensionMappings(Map<String, LocationSupplierSourceBean> extensionMappings) {
		this.extensionMappings = extensionMappings;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

}

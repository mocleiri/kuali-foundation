package org.kuali.common.jdbc.supplier;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FilenameUtils;
import org.kuali.common.util.Assert;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.FactoryBean;

public class LocationSupplierFactoryBean implements FactoryBean<List<LocationSupplier>> {

	List<String> locations;
	Map<String, LocationSupplierSourceBean> mappings;

	@Override
	public List<LocationSupplier> getObject() throws Exception {

		// Make sure we are configured correctly
		Assert.notNull(locations, "locations is null");
		Assert.notNull(mappings, "mappings is null");

		// Cycle through the list of locations, creating one supplier per location
		List<LocationSupplier> suppliers = new ArrayList<LocationSupplier>();
		for (String location : locations) {

			// Extract the extension from the location
			String extension = FilenameUtils.getExtension(location);

			// The map holds the concrete implementation to use for each extension
			LocationSupplierSourceBean sourceBean = mappings.get(extension);

			// Unknown extension type
			if (sourceBean == null) {
				throw new IllegalArgumentException("Unknown extension [" + extension + "]");
			}

			// Create a new instance of the impl class
			Class<? extends LocationSupplier> supplierClass = sourceBean.getSupplierClass();
			LocationSupplier implementation = supplierClass.newInstance();

			// Store the location on the impl
			implementation.setLocation(location);

			// Configure the impl with anything else it needs
			if (sourceBean.getInstance() != null) {
				BeanUtils.copyProperties(sourceBean.getInstance(), implementation);
			}

			// Add it to the list
			suppliers.add(implementation);
		}

		// Return the fully configured list of suppliers
		return suppliers;
	}

	@Override
	public Class<?> getObjectType() {
		return List.class;
	}

	@Override
	public boolean isSingleton() {
		return false;
	}

}

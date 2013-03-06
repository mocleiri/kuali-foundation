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
		Assert.notNull(locations, "locations is null");
		Assert.notNull(mappings, "mappings is null");
		List<LocationSupplier> suppliers = new ArrayList<LocationSupplier>();
		for (String location : locations) {
			String extension = FilenameUtils.getExtension(location);
			LocationSupplierSourceBean source = mappings.get(extension);
			if (source == null) {
				throw new IllegalArgumentException("Unknown extension [" + extension + "]");
			}
			Class<? extends LocationSupplier> supplierClass = source.getSupplierClass();
			LocationSupplier instance = supplierClass.newInstance();
			if (source.getInstance() != null) {
				BeanUtils.copyProperties(source.getInstance(), instance);
			}
			suppliers.add(instance);
		}
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

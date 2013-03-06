package org.kuali.common.jdbc.supplier;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.kuali.common.util.Assert;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.FactoryBean;

public class SqlSupplierFactoryBean implements FactoryBean<List<SqlSupplier>> {

	List<String> locations;
	List<LocationExtensionMapping> mappings;

	@Override
	public List<SqlSupplier> getObject() throws Exception {
		Assert.notNull(locations, "locations is null");
		Assert.notNull(mappings, "mappings is null");
		List<SqlSupplier> suppliers = new ArrayList<SqlSupplier>();
		for (String location : locations) {
			String extension = FilenameUtils.getExtension(location);
			LocationExtensionMapping mapping = getMapping(extension, mappings);
			Class<? extends SqlSupplier> supplierClass = mapping.getSupplierClass();
			SqlSupplier instance = supplierClass.newInstance();
			if (mapping.getSourceBean() != null) {
				BeanUtils.copyProperties(mapping.getSourceBean(), instance);
			}
		}
		return suppliers;
	}

	protected LocationExtensionMapping getMapping(String extension, List<LocationExtensionMapping> mappings) {
		for (LocationExtensionMapping mapping : mappings) {
			if (StringUtils.equals(extension, mapping.getExtension())) {
				return mapping;
			}
		}
		throw new IllegalArgumentException("Unknown extension [" + extension + "]");
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

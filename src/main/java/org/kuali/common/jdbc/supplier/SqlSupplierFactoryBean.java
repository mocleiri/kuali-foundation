package org.kuali.common.jdbc.supplier;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FilenameUtils;
import org.kuali.common.util.Assert;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.FactoryBean;

public class SqlSupplierFactoryBean implements FactoryBean<List<SqlSupplier>> {

	List<String> locations;
	Map<String, SqlSupplierSourceBean> mappings;

	@Override
	public List<SqlSupplier> getObject() throws Exception {
		Assert.notNull(locations, "locations is null");
		Assert.notNull(mappings, "mappings is null");
		List<SqlSupplier> suppliers = new ArrayList<SqlSupplier>();
		for (String location : locations) {
			String extension = FilenameUtils.getExtension(location);
			SqlSupplierSourceBean source = mappings.get(extension);
			if (source == null) {
				throw new IllegalArgumentException("Unknown extension [" + extension + "]");
			}
			Class<? extends SqlSupplier> supplierClass = source.getSupplierClass();
			SqlSupplier instance = supplierClass.newInstance();
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

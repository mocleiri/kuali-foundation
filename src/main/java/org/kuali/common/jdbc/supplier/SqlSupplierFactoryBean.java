package org.kuali.common.jdbc.supplier;

import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.kuali.common.jdbc.SqlReader;
import org.kuali.common.util.Assert;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.FactoryBean;

public class SqlSupplierFactoryBean implements FactoryBean<SqlSupplier> {

	String location;
	SqlReader reader;
	String encoding;
	List<LocationExtensionMapping> mappings;

	@Override
	public SqlSupplier getObject() throws Exception {
		Assert.notBlank(location, "location is null");
		Assert.notNull(mappings, "mappings is null");
		String extension = FilenameUtils.getExtension(location);
		LocationExtensionMapping mapping = getMapping(extension, mappings);
		Class<? extends SqlSupplier> supplierClass = mapping.getSupplierClass();
		SqlSupplier instance = supplierClass.newInstance();
		BeanUtils.copyProperties(this, instance);
		return instance;
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
	public Class<? extends SqlSupplier> getObjectType() {
		return SqlSupplier.class;
	}

	@Override
	public boolean isSingleton() {
		return false;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public SqlReader getReader() {
		return reader;
	}

	public void setReader(SqlReader reader) {
		this.reader = reader;
	}

	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	public List<LocationExtensionMapping> getMappings() {
		return mappings;
	}

	public void setMappings(List<LocationExtensionMapping> mappings) {
		this.mappings = mappings;
	}

}

package org.kuali.common.util.property;

import java.util.List;
import java.util.Properties;

import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.PropertyUtils;
import org.springframework.beans.factory.FactoryBean;

public class PropertyFactoryBean implements FactoryBean<Properties> {

	String locs;
	List<String> locations;
	String encoding;

	@Override
	public Properties getObject() throws Exception {
		List<String> list = CollectionUtils.getList(locations, locs);
		return PropertyUtils.getProperties(list, encoding);
	}

	@Override
	public Class<Properties> getObjectType() {
		return Properties.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

	public List<String> getLocations() {
		return locations;
	}

	public void setLocations(List<String> locations) {
		this.locations = locations;
	}

	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	public String getLocs() {
		return locs;
	}

	public void setLocs(String locs) {
		this.locs = locs;
	}

}

package org.kuali.common.impex;

import org.kuali.common.impex.KualiDatabase;
import org.kuali.common.util.Assert;
import org.kuali.core.db.torque.KualiXmlToAppData;
import org.springframework.beans.factory.FactoryBean;

public class KualiDatabaseFactoryBean implements FactoryBean<KualiDatabase> {

	String location;
	String databaseVendor;
    private Boolean isSingleton = Boolean.FALSE;

    @Override
	public KualiDatabase getObject() throws Exception {

		Assert.notNull(location, "location is null");
		Assert.notNull(databaseVendor, "databaseVendor is null");

		KualiXmlToAppData parser = new KualiXmlToAppData(databaseVendor);
		return parser.parseResource(location);
	}

	@Override
	public Class<KualiDatabase> getObjectType() {
		return KualiDatabase.class;
	}

	@Override
	public boolean isSingleton() {
		return isSingleton;
	}

    public void setSingleton(Boolean singleton) {
        isSingleton = singleton;
    }

    public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDatabaseVendor() {
		return databaseVendor;
	}

	public void setDatabaseVendor(String databaseVendor) {
		this.databaseVendor = databaseVendor;
	}

}

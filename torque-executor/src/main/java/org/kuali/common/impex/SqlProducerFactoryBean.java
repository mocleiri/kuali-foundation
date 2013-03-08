package org.kuali.common.impex;

import org.apache.torque.engine.platform.Platform;
import org.apache.torque.engine.platform.PlatformFactory;
import org.kuali.common.impex.service.SqlProducer;
import org.springframework.beans.factory.FactoryBean;

public class SqlProducerFactoryBean implements FactoryBean<SqlProducer> {

	String databaseVendor;

	@Override
	public SqlProducer getObject() throws Exception {
		Platform platform = PlatformFactory.getPlatformFor(databaseVendor);
		return platform.getSqlProducer();
	}

	@Override
	public Class<SqlProducer> getObjectType() {
		return SqlProducer.class;
	}

	@Override
	public boolean isSingleton() {
		return false;
	}

	public String getDatabaseVendor() {
		return databaseVendor;
	}

	public void setDatabaseVendor(String databaseVendor) {
		this.databaseVendor = databaseVendor;
	}

}

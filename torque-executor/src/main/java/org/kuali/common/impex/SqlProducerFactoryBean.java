package org.kuali.common.impex;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.torque.engine.platform.Platform;
import org.apache.torque.engine.platform.PlatformFactory;
import org.kuali.common.impex.service.SqlProducer;
import org.springframework.beans.factory.FactoryBean;

public class SqlProducerFactoryBean implements FactoryBean<SqlProducer> {

	String databaseVendor;
	int batchRowCountLimit = 50;
	int batchDataSizeLimit = 50 * 1024;

	@Override
	public SqlProducer getObject() throws Exception {
		Platform platform = PlatformFactory.getPlatformFor(databaseVendor);
		SqlProducer producer = platform.getSqlProducer();
		BeanUtils.copyProperty(producer, "batchRowCountLimit", batchRowCountLimit);
		BeanUtils.copyProperty(producer, "batchDataSizeLimit", batchDataSizeLimit);
		return producer;
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

	public int getBatchRowCountLimit() {
		return batchRowCountLimit;
	}

	public void setBatchRowCountLimit(int batchRowCountLimit) {
		this.batchRowCountLimit = batchRowCountLimit;
	}

	public int getBatchDataSizeLimit() {
		return batchDataSizeLimit;
	}

	public void setBatchDataSizeLimit(int batchDataSizeLimit) {
		this.batchDataSizeLimit = batchDataSizeLimit;
	}

}

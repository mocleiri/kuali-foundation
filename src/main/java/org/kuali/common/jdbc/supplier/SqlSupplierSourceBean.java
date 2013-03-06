package org.kuali.common.jdbc.supplier;

public class SqlSupplierSourceBean {

	Class<? extends SqlSupplier> supplierClass;
	Object instance;

	public Class<? extends SqlSupplier> getSupplierClass() {
		return supplierClass;
	}

	public void setSupplierClass(Class<? extends SqlSupplier> supplierClass) {
		this.supplierClass = supplierClass;
	}

	public Object getInstance() {
		return instance;
	}

	public void setInstance(Object instance) {
		this.instance = instance;
	}

}

package org.kuali.common.jdbc.supplier;

public class LocationSupplierSourceBean {

	Class<? extends LocationSupplier> supplierClass;
	Object instance;

	public Class<? extends LocationSupplier> getSupplierClass() {
		return supplierClass;
	}

	public void setSupplierClass(Class<? extends LocationSupplier> supplierClass) {
		this.supplierClass = supplierClass;
	}

	public Object getInstance() {
		return instance;
	}

	public void setInstance(Object instance) {
		this.instance = instance;
	}

}

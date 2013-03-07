package org.kuali.common.jdbc.supplier;

public class LocationSupplierSourceBean {

	Class<? extends LocationSupplier> supplierClass;
	LocationSupplier instance;

	public Class<? extends LocationSupplier> getSupplierClass() {
		return supplierClass;
	}

	public void setSupplierClass(Class<? extends LocationSupplier> supplierClass) {
		this.supplierClass = supplierClass;
	}

	public LocationSupplier getInstance() {
		return instance;
	}

	public void setInstance(LocationSupplier instance) {
		this.instance = instance;
	}

}

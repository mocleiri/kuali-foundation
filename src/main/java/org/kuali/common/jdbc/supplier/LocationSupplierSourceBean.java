package org.kuali.common.jdbc.supplier;

public class LocationSupplierSourceBean {

	Class<? extends LocationSupplier> supplierClass;
	LocationSupplier supplierInstance;

	public Class<? extends LocationSupplier> getSupplierClass() {
		return supplierClass;
	}

	public void setSupplierClass(Class<? extends LocationSupplier> supplierClass) {
		this.supplierClass = supplierClass;
	}

	public LocationSupplier getSupplierInstance() {
		return supplierInstance;
	}

	public void setSupplierInstance(LocationSupplier supplierInstance) {
		this.supplierInstance = supplierInstance;
	}

}

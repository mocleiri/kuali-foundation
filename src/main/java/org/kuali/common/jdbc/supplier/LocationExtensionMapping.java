package org.kuali.common.jdbc.supplier;

public class LocationExtensionMapping {

	String extension;
	Class<? extends SqlSupplier> supplierClass;

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public Class<? extends SqlSupplier> getSupplierClass() {
		return supplierClass;
	}

	public void setSupplierClass(Class<? extends SqlSupplier> supplierClass) {
		this.supplierClass = supplierClass;
	}

}

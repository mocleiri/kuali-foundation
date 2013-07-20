package org.kuali.common.util.spring.profile;

public class DefaultDatabase implements Database {

	String vendor;

	@Override
	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

}

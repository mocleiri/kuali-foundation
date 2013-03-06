package org.kuali.common.jdbc.supplier;


public interface LocationSupplier extends SqlSupplier {

	String getLocation();

	void setLocation(String location);

}

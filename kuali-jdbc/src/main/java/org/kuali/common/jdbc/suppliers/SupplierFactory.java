package org.kuali.common.jdbc.suppliers;


public interface SupplierFactory {

	SqlSupplier getSupplier(String location);

	boolean isMatch(String location);

}

package org.kuali.common.jdbc.suppliers;

/**
 * Get an appropriate supplier based on information contained in the location (eg file extension)
 */
public interface LocationSupplierFactory {

	SqlSupplier getSupplier(String location);

	boolean isMatch(String location);

}

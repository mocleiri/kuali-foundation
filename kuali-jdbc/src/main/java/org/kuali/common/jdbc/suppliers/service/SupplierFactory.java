package org.kuali.common.jdbc.suppliers.service;

import org.kuali.common.jdbc.suppliers.SqlSupplier;

public interface SupplierFactory {

	SqlSupplier getSupplier(String location);

	boolean isMatch(String location);

}

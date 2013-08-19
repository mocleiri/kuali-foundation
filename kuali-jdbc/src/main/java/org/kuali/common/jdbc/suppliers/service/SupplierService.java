package org.kuali.common.jdbc.suppliers.service;

import java.util.List;

import org.kuali.common.jdbc.suppliers.SqlSupplier;

public interface SupplierService {

	List<SqlSupplier> getSuppliers(String resourcesLocation);

}

package org.kuali.common.jdbc.supplier;

import java.util.ArrayList;
import java.util.List;

import org.kuali.common.jdbc.DefaultSqlReader;
import org.kuali.common.jdbc.SqlReader;

public class ComplexStringSupplierTest {

	public void test() {
		List<String> sql = new ArrayList<String>();
		sql.add("select sysdate from dual");
		sql.add("select 1 from dual");

		SqlReader reader = new DefaultSqlReader();

		ComplexStringSupplier supplier = new ComplexStringSupplier();

		supplier.setStrings(sql);

	}

}

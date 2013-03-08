package org.kuali.common.jdbc.supplier;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.kuali.common.jdbc.DefaultSqlReader;
import org.kuali.common.jdbc.SqlMetaData;
import org.kuali.common.jdbc.SqlReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

public class ComplexStringSupplierTest {

	private static final Logger logger = LoggerFactory.getLogger(ComplexStringSupplierTest.class);

	@Test
	public void test() {
		String singleSqlStatement = "select sysdate from dual\n/\n";
		int count = 5;
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < count; i++) {
			sb.append(singleSqlStatement);
		}
		List<String> list = new ArrayList<String>();
		list.add(sb.toString());
		list.add(sb.toString());
		list.add(sb.toString());
		list.add("select sysdate from dual");

		SqlReader reader = new DefaultSqlReader();

		ComplexStringSupplier supplier = new ComplexStringSupplier();
		supplier.setStrings(list);
		supplier.setReader(reader);

		supplier.fillInMetaData();
		SqlMetaData smd = supplier.getMetaData();

		Assert.isTrue(smd.getCount() == count * 3 + 1);

		supplier.open();
		List<String> sql = supplier.getSql();
		while (sql != null) {
			for (String s : sql) {
				logger.info(s);
			}
			sql = supplier.getSql();
		}
		supplier.close();

	}

}

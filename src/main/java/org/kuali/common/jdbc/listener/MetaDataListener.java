package org.kuali.common.jdbc.listener;

import java.util.List;

import org.kuali.common.jdbc.context.JdbcContext;
import org.kuali.common.jdbc.supplier.SqlSupplier;
import org.kuali.common.util.FormatUtils;

public class MetaDataListener extends NoOpSqlListener {

	@Override
	public void beforeMetaData(SqlMetaDataEvent event) {
		JdbcContext context = event.getContext();
		List<SqlSupplier> suppliers = context.getSuppliers();
		System.out.print("[INFO] Accumulating SQL metadata from " + suppliers.size() + " suppliers - ");
	}

	@Override
	public void afterMetaData(SqlMetaDataEvent event) {
		String elapsed = FormatUtils.getTime(event.getStopTimeMillis() - event.getStartTimeMillis());
		System.out.println("[" + elapsed + "]");
	}

}

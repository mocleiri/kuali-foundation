package org.kuali.common.jdbc;

import org.kuali.common.jdbc.context.SqlBucketContext;
import org.kuali.common.threads.ElementHandler;
import org.kuali.common.threads.ListIteratorContext;

public class SqlBucketHandler implements ElementHandler<SqlBucketContext> {

	@Override
	public void handleElement(ListIteratorContext<SqlBucketContext> context, int index, SqlBucketContext element) {
		JdbcService service = element.getService();
		service.executeSql(element.getContext());
	}

}

package org.kuali.common.jdbc.threads;

import org.kuali.common.jdbc.JdbcService;
import org.kuali.common.threads.ElementHandler;
import org.kuali.common.threads.ListIteratorContext;

public class SqlBucketHandler implements ElementHandler<SqlBucketContext> {

	@Override
	public void handleElement(ListIteratorContext<SqlBucketContext> context, int index, SqlBucketContext element) {
		JdbcService service = element.getService();
		service.executeSql(element.getContext());
	}

}

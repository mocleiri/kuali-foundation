package org.kuali.core.db.torque.service;

import org.kuali.common.util.FormatUtils;
import org.kuali.core.db.torque.pojo.DumpTableContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultDataHandler implements DataHandler {

	private static final Logger logger = LoggerFactory.getLogger(DefaultDataHandler.class);

	@Override
	public void startData(DumpTableContext context) {
	}

	@Override
	public void doData(DumpTableContext context) {
		// System.out.print(".");
	}

	@Override
	public void finishData(DumpTableContext context) {
		if (context.getTotalRowCount() > 0) {
			long threadId = Thread.currentThread().getId();
			String tableName = context.getTableContext().getName();
			String trc = FormatUtils.getCount(context.getTotalRowCount());
			String tds = FormatUtils.getSize(context.getTotalDataSize());
			Object[] args = { threadId, tableName, trc, tds };
			logger.info("Thread - {} - Dumped [{}] Total Rows: {}  Total Size: {}", args);
		}
	}

}

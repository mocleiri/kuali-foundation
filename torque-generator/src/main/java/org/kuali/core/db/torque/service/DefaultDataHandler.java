package org.kuali.core.db.torque.service;

import org.kuali.common.util.FormatUtils;
import org.kuali.core.db.torque.pojo.DumpTableContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultDataHandler implements DataHandler {

	private static final Logger logger = LoggerFactory.getLogger(DefaultDataHandler.class);

	@Override
	public void handleData(DumpTableContext context) {
		handle(context);
	}

	@Override
	public void finalizeData(DumpTableContext context) {
		handle(context);
	}

	protected void handle(DumpTableContext context) {
		String crc = FormatUtils.getCount(context.getCurrentRowCount());
		String trc = FormatUtils.getCount(context.getTotalRowCount());
		String cds = FormatUtils.getSize(context.getCurrentDataSize());
		String tds = FormatUtils.getSize(context.getTotalDataSize());
		Object[] args = { crc, trc, cds, tds };
		logger.info("Current Rows: {}  Total Rows: {}  Current Size: {}  Total Size: {}", args);
	}

}

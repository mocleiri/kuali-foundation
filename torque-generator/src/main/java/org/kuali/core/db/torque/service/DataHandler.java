package org.kuali.core.db.torque.service;

import org.kuali.core.db.torque.pojo.DumpTableContext;

public interface DataHandler {

	void handleData(DumpTableContext context);

	void finalizeData(DumpTableContext context);

}

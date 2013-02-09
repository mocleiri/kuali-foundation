package org.kuali.core.db.torque.service;

import org.kuali.core.db.torque.pojo.DumpTableContext;

public interface DataHandler {

	void startData(DumpTableContext context);

	void doData(DumpTableContext context);

	void finishData(DumpTableContext context);

}

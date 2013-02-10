package org.kuali.core.db.torque.service;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

import org.kuali.core.db.torque.pojo.DumpTableContext;

public interface DataHandler {

	OutputStream openOutputStream(File workingDir, String tableName) throws IOException;

	void startData(DumpTableContext context) throws IOException;

	void doData(DumpTableContext context) throws IOException;

	void finishData(DumpTableContext context) throws IOException;

}

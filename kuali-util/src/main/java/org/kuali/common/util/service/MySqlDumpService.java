package org.kuali.common.util.service;

import java.io.File;

public interface MySqlDumpService extends ExecService {

	void dump(MySqlDumpContext context);

	void dump(String username, String password, String hostname, String database, File outputFile);

}

package org.kuali.common.util.service;

import java.io.File;
import java.util.List;

public interface MySqlDumpService extends ExecService {

	void dump(MySqlDumpContext context);

	void dump(String username, String password, String hostname, String database, File outputFile);

	void dump(List<String> args, File outputFile);

}

package org.kuali.common.util.service;

import java.io.File;
import java.util.List;

public interface MySqlDumpService extends ExecService {

	public static final String DEFAULT_EXECUTABLE = "mysqldump";
	public static final int DEFAULT_PORT = 3306;
	public static final String SKIP_LINE_PREFIX = "/*!50013 DEFINER=";
	public static final String SKIP_LINE_SUFFIX = "SQL SECURITY DEFINER */";

	void dump(MySqlDumpContext context);

	void dump(String username, String password, String hostname, String database, File outputFile);

	void dump(List<String> options, String database, File outputFile);

}

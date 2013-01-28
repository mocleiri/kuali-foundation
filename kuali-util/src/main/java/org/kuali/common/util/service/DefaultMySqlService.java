package org.kuali.common.util.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.kuali.common.util.Assert;

public class DefaultMySqlService implements MySqlService {

	ExecService service = new DefaultExecService();

	@Override
	public void dump(MySqlDumpContext context) {
		Assert.notNull(context.getDatabase(), "database is null");
		try {
			FileUtils.touch(context.getOutputFile());
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}

	protected ExecContext getExecContext(MySqlDumpContext context) throws IOException {
		DefaultExecContext ec = new DefaultExecContext();
		ec.setExecutable(context.getExecutable());
		ec.setArgs(getMySqlDumpArgs(context));
		return ec;
	}

	protected List<String> getMySqlDumpArgs(MySqlDumpContext context) throws IOException {
		List<String> args = new ArrayList<String>();
		if (!StringUtils.isBlank(context.getUsername())) {
			args.add("-u");
			args.add(context.getUsername());
		}
		if (!StringUtils.isBlank(context.getPassword())) {
			args.add("-p" + context.getPassword());
		}
		if (!StringUtils.isBlank(context.getHostname())) {
			args.add("-h");
			args.add(context.getHostname());
		}
		if (!StringUtils.isBlank(context.getDatabase())) {
			args.add(context.getDatabase());
		}
		args.add(">");
		args.add(context.getOutputFile().getCanonicalPath());
		return args;
	}

}

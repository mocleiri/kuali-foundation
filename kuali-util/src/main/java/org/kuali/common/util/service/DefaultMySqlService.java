package org.kuali.common.util.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.kuali.common.util.Assert;
import org.kuali.common.util.LocationUtils;

public class DefaultMySqlService implements MySqlService {

	ExecService service = new DefaultExecService();

	@Override
	public void dump(MySqlDumpContext context) {
		Assert.notNull(context.getDatabase(), "database is null");
		touch(context.getOutputFile());
		List<String> args = getMySqlDumpArgs(context);
		int result = service.execute(context.getExecutable(), args);
		if (result != 0) {
			throw new IllegalStateException("Non-zero exit value - " + result);
		}
	}

	protected List<String> getMySqlDumpArgs(MySqlDumpContext context) {
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
		args.add(LocationUtils.getCanonicalPath(context.getOutputFile()));
		return args;
	}

	protected void touch(File file) {
		try {
			FileUtils.touch(file);
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
	}

	public ExecService getService() {
		return service;
	}

	public void setService(ExecService service) {
		this.service = service;
	}

}

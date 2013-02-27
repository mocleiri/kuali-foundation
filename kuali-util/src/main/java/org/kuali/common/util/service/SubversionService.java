package org.kuali.common.util.service;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.LocationUtils;

public class SubversionService extends DefaultExecService implements ScmService {

	private static final String SVN = "svn";

	@Override
	public void add(List<File> files) {
		if (CollectionUtils.isEmpty(files)) {
			// Nothing to do
			return;
		}
		String command = "add";
		List<String> paths = LocationUtils.getCanonicalPaths(files);
		List<String> options = Arrays.asList("--force", "--parents");

		List<String> arguments = new ArrayList<String>();
		arguments.add(command);
		arguments.addAll(paths);
		arguments.addAll(options);

		int exitValue = execute(SVN, arguments);
		if (exitValue != 0) {
			throw new IllegalStateException("Non-zero exit value - " + exitValue);
		}
	}

	@Override
	public void delete(List<File> files) {
	}

	@Override
	public void commit(List<File> paths) {
	}

}

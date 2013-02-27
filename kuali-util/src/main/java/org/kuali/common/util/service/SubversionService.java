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
	public void add(List<File> paths) {
		if (CollectionUtils.isEmpty(paths)) {
			// Nothing to do
			return;
		}
		String command = "add";
		List<String> cpaths = LocationUtils.getCanonicalPaths(paths);
		List<String> options = Arrays.asList("--force", "--parents");

		List<String> arguments = new ArrayList<String>();
		arguments.add(command);
		arguments.addAll(cpaths);
		arguments.addAll(options);

		executeAndValidate(SVN, arguments);
	}

	@Override
	public void delete(List<File> paths) {
		if (CollectionUtils.isEmpty(paths)) {
			// Nothing to do
			return;
		}
		String command = "delete";
		List<String> cpaths = LocationUtils.getCanonicalPaths(paths);
		List<String> options = Arrays.asList("--force");

		List<String> arguments = new ArrayList<String>();
		arguments.add(command);
		arguments.addAll(cpaths);
		arguments.addAll(options);

		executeAndValidate(SVN, arguments);
	}

	@Override
	public void commit(List<File> paths, String message) {
		if (CollectionUtils.isEmpty(paths)) {
			// Nothing to do
			return;
		}
		String command = "commit";
		List<String> cpaths = LocationUtils.getCanonicalPaths(paths);
		List<String> options = Arrays.asList("--depth", "infinity", "--message", message);

		List<String> arguments = new ArrayList<String>();
		arguments.add(command);
		arguments.addAll(cpaths);
		arguments.addAll(options);

		executeAndValidate(SVN, arguments);
	}

}

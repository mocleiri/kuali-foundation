package org.kuali.common.util.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.kuali.common.util.LocationUtils;
import org.springframework.util.CollectionUtils;

public class DefaultMavenService extends DefaultExecService implements MavenService {

	protected List<String> getOptions(MavenContext context) {
		List<String> options = new ArrayList<String>();
		if (context.isBatchMode()) {
			options.add("--batch-mode");
		}
		if (context.isDebug()) {
			options.add("--debug");
		}
		if (context.isErrors()) {
			options.add("--errors");
		}
		if (context.isOffline()) {
			options.add("--offline");
		}
		if (context.isQuiet()) {
			options.add("--quiet");
		}
		if (context.getPom() != null) {
			String cpath = LocationUtils.getCanonicalPath(context.getPom());
			options.add("--file");
			options.add(cpath);
		}
		return options;
	}

	protected void handleOptions(MavenContext context) {
		List<String> options = getOptions(context);
		if (context.getOptions() == null) {
			context.setOptions(options);
		} else {
			context.getOptions().addAll(options);
		}
	}

	@Override
	public void execute(MavenContext context) {

		// Update options with MavenContext attributes
		handleOptions(context);

		// Convert options/goals/phases into an arg list
		List<String> args = getArgs(context);

		// Create an execution context
		DefaultExecContext dec = new DefaultExecContext();
		dec.setExecutable(context.getExecutable());
		dec.setWorkingDirectory(context.getWorkingDir());
		dec.setArgs(args);

		// Execute Maven making sure we get 0 as a return value
		executeAndValidate(dec);
	}

	protected List<String> getArgs(MavenContext context) {
		List<String> args = new ArrayList<String>();
		if (!CollectionUtils.isEmpty(context.getOptions())) {
			args.addAll(context.getOptions());
		}
		if (!CollectionUtils.isEmpty(context.getGoals())) {
			args.addAll(context.getGoals());
		}
		if (!CollectionUtils.isEmpty(context.getPhases())) {
			args.addAll(context.getPhases());
		}
		return args;
	}

	@Override
	public void execute(File workingDir, List<String> options, List<String> goals, List<String> phases) {
		MavenContext context = new MavenContext();
		context.setWorkingDir(workingDir);
		context.setOptions(options);
		context.setGoals(goals);
		context.setPhases(phases);
		execute(context);
	}

}

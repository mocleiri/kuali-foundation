package org.kuali.common.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class UnixCmds {
	private static final String SU = "su";
	private static final String MKDIR = "mkdir";
	private static final String RM = "rm";
	private static final String CHOWN = "chown";
	private static final String CHMOD = "chmod";

	public String mkdirp(String path) {
		Assert.hasLength(path);
		return mkdirp((List<String>) null, Collections.singletonList(path));
	}

	public String mkdirp(List<String> paths) {
		return mkdirp((List<String>) null, paths);
	}

	public String mkdirp(List<String> options, List<String> paths) {
		List<String> parents = Arrays.asList("-p");
		if (options == null) {
			return rm(parents, paths);
		} else {
			return rm(CollectionUtils.combineStringsUniquely(options, parents), paths);
		}

	}

	public String mkdir(List<String> options, List<String> paths) {
		Assert.notEmpty(paths);
		StringBuilder sb = new StringBuilder();
		sb.append(MKDIR);
		if (!CollectionUtils.isEmpty(options)) {
			sb.append(" ");
			sb.append(CollectionUtils.getSpaceSeparatedString(options));
		}
		sb.append(" ");
		sb.append(CollectionUtils.getSpaceSeparatedString(paths));
		return sb.toString();
	}

	public String su(String login, String command) {
		return su((List<String>) null, login, command);
	}

	public String su(List<String> options, String login, String command) {
		return su(options, login, Arrays.asList("--command", command));
	}

	public String su(List<String> options, String login, List<String> args) {
		StringBuilder sb = new StringBuilder();
		sb.append(SU);
		if (options != null) {
			sb.append(" ");
			sb.append(CollectionUtils.getSpaceSeparatedString(options));
		}
		if (login != null) {
			sb.append("--login");
			sb.append(" ");
			sb.append(login);
		}
		sb.append(" ");
		sb.append(CollectionUtils.getSpaceSeparatedString(args));
		return sb.toString();
	}

	public String rmrf(List<String> files) {
		return rmrf(null, files);
	}

	public String rmrf(List<String> args, List<String> files) {
		List<String> silent = Arrays.asList("-r", "-f");
		if (args == null) {
			return rm(silent, files);
		} else {
			return rm(CollectionUtils.combineStringsUniquely(args, silent), files);
		}
	}

	public String cmd(String executable, List<String> options) {
		StringBuilder sb = new StringBuilder();
		sb.append(executable);
		if (!CollectionUtils.isEmpty(options)) {
			sb.append(" ");
			sb.append(CollectionUtils.getSpaceSeparatedString(options));
		}
		return sb.toString();
	}

	public String rm(List<String> options, List<String> files) {
		Assert.notEmpty(files);
		return cmd(RM, CollectionUtils.addStrings(options, files));
	}

	public String chownr(String owner, String group, String path) {
		Assert.hasLength(path);
		return chownr(owner, group, Collections.singletonList(path));
	}

	public String chownr(String owner, String group, List<String> paths) {
		return chownr((List<String>) null, owner, group, paths);
	}

	public String chownr(List<String> options, String owner, String group, List<String> paths) {
		List<String> recursive = Arrays.asList("-R");
		if (options == null) {
			return chown(recursive, owner, group, paths);
		} else {
			return chown(CollectionUtils.combineStringsUniquely(options, recursive), owner, group, paths);
		}
	}

	public String chown(List<String> options, String owner, String group, List<String> paths) {
		Assert.notEmpty(paths);
		Assert.notBlank(owner, group);
		StringBuilder sb = new StringBuilder();
		sb.append(CHOWN);
		if (options != null) {
			sb.append(" ");
			sb.append(CollectionUtils.getSpaceSeparatedString(options));
		}
		sb.append(" ");
		sb.append(owner + ":" + group);
		sb.append(" ");
		sb.append(CollectionUtils.getSpaceSeparatedString(paths));
		return sb.toString();
	}

	public String chown(String owner, String group, String path) {
		Assert.hasLength(path);
		return chown((List<String>) null, owner, group, Collections.singletonList(path));
	}

}

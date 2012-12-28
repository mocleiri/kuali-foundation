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

	public String chmod(String mode, String path) {
		Assert.hasLength(path);
		return chmod(mode, Collections.singletonList(path));
	}

	public String chmod(String octalMode, List<String> paths) {
		return chmod(null, octalMode, paths);

	}

	public String chmod(List<String> options, String mode, List<String> paths) {
		Assert.hasLength(mode);
		Assert.notEmpty(paths);
		return cmd(CHMOD, CollectionUtils.combineStrings(options, mode, paths));
	}

	public String mkdirp(String path) {
		Assert.hasLength(path);
		return mkdirp(null, Collections.singletonList(path));
	}

	public String mkdirp(List<String> paths) {
		return mkdirp(null, paths);
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
		return cmd(MKDIR, CollectionUtils.combineStrings(options, paths));
	}

	public String su(String login, String command) {
		return su(null, login, command);
	}

	public String su(List<String> options, String login, String command) {
		return su(options, login, Arrays.asList("--command", command));
	}

	public String su(List<String> options, String login, List<String> args) {
		List<String> list2 = login == null ? null : Arrays.asList("-", login);
		return cmd(SU, CollectionUtils.combineStrings(options, list2, args));
	}

	public String rmrf(List<String> paths) {
		return rmrf(null, paths);
	}

	public String rmrf(String path) {
		Assert.notBlank(path);
		return rmrf(null, Collections.singletonList(path));
	}

	public String rmrf(List<String> options, List<String> paths) {
		List<String> recursiveSilent = Arrays.asList("-r", "-f");
		if (options == null) {
			return rm(recursiveSilent, paths);
		} else {
			return rm(CollectionUtils.combineStringsUniquely(options, recursiveSilent), paths);
		}
	}

	public String rm(List<String> paths) {
		return rm(null, paths);
	}

	public String rm(String path) {
		Assert.notBlank(path);
		return rm(null, Collections.singletonList(path));
	}

	public String rm(List<String> options, List<String> paths) {
		Assert.notEmpty(paths);
		return cmd(RM, CollectionUtils.combineStrings(options, paths));
	}

	public String chownr(String owner, String group, String path) {
		Assert.hasLength(path);
		return chownr(owner, group, Collections.singletonList(path));
	}

	public String chownr(String owner, String group, List<String> paths) {
		return chownr(null, owner, group, paths);
	}

	public String chownr(List<String> options, String owner, String group, List<String> paths) {
		List<String> recursive = Arrays.asList("-R");
		if (options == null) {
			return chown(recursive, owner, group, paths);
		} else {
			return chown(CollectionUtils.combineStringsUniquely(options, recursive), owner, group, paths);
		}
	}

	public String chown(List<String> options, String owner, String group, String path) {
		Assert.hasLength(path);
		return chown(options, owner, group, Collections.singletonList(path));
	}

	public String chown(List<String> options, String owner, String group, List<String> paths) {
		Assert.notEmpty(paths);
		Assert.notBlank(owner, group);
		return cmd(CHOWN, CollectionUtils.combineStrings(options, owner + ":" + group, paths));
	}

	public String chown(String owner, String group, String path) {
		Assert.hasLength(path);
		return chown(null, owner, group, Collections.singletonList(path));
	}

	public String cmd(String executable, List<String> args) {
		StringBuilder sb = new StringBuilder();
		sb.append(executable);
		if (!CollectionUtils.isEmpty(args)) {
			sb.append(" ");
			sb.append(CollectionUtils.getSpaceSeparatedString(args));
		}
		return sb.toString();
	}

}

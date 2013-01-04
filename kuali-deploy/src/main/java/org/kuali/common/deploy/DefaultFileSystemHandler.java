package org.kuali.common.deploy;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.SimpleFormatter;
import org.kuali.common.util.UnixCmds;
import org.kuali.common.util.property.Constants;
import org.kuali.common.util.secure.RemoteFile;
import org.kuali.common.util.secure.Result;
import org.kuali.common.util.secure.SecureChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.util.PropertyPlaceholderHelper;

public class DefaultFileSystemHandler implements FileSystemHandler {

	private static final Logger logger = LoggerFactory.getLogger(DefaultFileSystemHandler.class);
	private static final String TRAVERSE_SYMBOLIC_LINKS = "-L";

	PropertyPlaceholderHelper helper = Constants.DEFAULT_PROPERTY_PLACEHOLDER_HELPER;
	SimpleFormatter formatter = new SimpleFormatter();
	UnixCmds cmds = new UnixCmds();
	Properties properties;
	SecureChannel channel;
	List<String> filesToDelete;
	List<String> directoriesToDelete;
	List<String> directoriesToCreate;
	List<String> directoriesToChown;
	List<String> jsps;
	String jspDir;
	String setenv;
	String setenvPermissions;
	String owner;
	String group;
	List<Deployable> deployables;

	@Override
	public void clean() {
		executeCommand(cmds.rmrf(filesToDelete), filesToDelete);
		executeCommand(cmds.rmrf(directoriesToDelete), directoriesToDelete);
	}

	@Override
	public void prepare() {
		executeCommand(cmds.mkdirp(directoriesToCreate), directoriesToCreate);
		copyJsps();
		copyDeployables();
		executeCommand(cmds.chmod(setenvPermissions, Arrays.asList(setenv)), Arrays.asList(setenv));
		executeCommand(cmds.chownr(Arrays.asList(TRAVERSE_SYMBOLIC_LINKS), owner, group, directoriesToChown), directoriesToChown);
	}

	protected void copyDeployables() {
		if (CollectionUtils.isEmpty(deployables)) {
			return;
		}
		for (Deployable deployable : deployables) {
			RemoteFile destination = new RemoteFile(deployable.getRemote());
			String location = deployable.getLocal();
			if (deployable.isFilter()) {
				long start = System.currentTimeMillis();
				String originalContent = LocationUtils.toString(location);
				String resolvedContent = helper.replacePlaceholders(originalContent, properties);
				channel.copyStringToFile(resolvedContent, destination);
				long elapsed = System.currentTimeMillis() - start;
				Object[] args = { properties.size(), location, destination.getAbsolutePath(), formatter.getTime(elapsed) };
				logger.info("Used {} properties to filter [{}] -> [{}] - {}", args);
			} else {
				long start = System.currentTimeMillis();
				channel.copyLocationToFile(location, destination);
				logCopy(location, destination.getAbsolutePath(), System.currentTimeMillis() - start);
			}
		}
	}

	protected void copyJsps() {
		if (CollectionUtils.isEmpty(jsps)) {
			return;
		}
		RemoteFile destination = new RemoteFile(jspDir);
		for (String jsp : jsps) {
			long start = System.currentTimeMillis();
			channel.copyLocationToDirectory(jsp, destination);
			logCopy(jsp, jspDir + "/" + LocationUtils.getFilename(jsp), System.currentTimeMillis() - start);
		}
	}

	protected void logCopy(String src, String dst, long elapsed) {
		Object[] args = { src, dst, formatter.getTime(elapsed) };
		logger.info("[{}] -> [{}] - {}", args);
	}

	protected void executeCommand(String command, List<String> paths) {
		if (CollectionUtils.isEmpty(paths)) {
			return;
		}
		Result result = channel.executeCommand(command);
		ServiceUtils.logResult(result, logger);
		ServiceUtils.validateResult(result);
	}

	public UnixCmds getCmds() {
		return cmds;
	}

	public void setCmds(UnixCmds cmds) {
		this.cmds = cmds;
	}

	public SecureChannel getChannel() {
		return channel;
	}

	public void setChannel(SecureChannel channel) {
		this.channel = channel;
	}

	public List<String> getFilesToDelete() {
		return filesToDelete;
	}

	public void setFilesToDelete(List<String> filesToDelete) {
		this.filesToDelete = filesToDelete;
	}

	public List<String> getDirectoriesToDelete() {
		return directoriesToDelete;
	}

	public void setDirectoriesToDelete(List<String> directoriesToDelete) {
		this.directoriesToDelete = directoriesToDelete;
	}

	public List<String> getDirectoriesToCreate() {
		return directoriesToCreate;
	}

	public void setDirectoriesToCreate(List<String> directoriesToCreate) {
		this.directoriesToCreate = directoriesToCreate;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public List<String> getDirectoriesToChown() {
		return directoriesToChown;
	}

	public void setDirectoriesToChown(List<String> directoriesToChown) {
		this.directoriesToChown = directoriesToChown;
	}

	public List<String> getJsps() {
		return jsps;
	}

	public void setJsps(List<String> jsps) {
		this.jsps = jsps;
	}

	public String getJspDir() {
		return jspDir;
	}

	public void setJspDir(String jspDir) {
		this.jspDir = jspDir;
	}

	public List<Deployable> getDeployables() {
		return deployables;
	}

	public void setDeployables(List<Deployable> deployables) {
		this.deployables = deployables;
	}

	public String getSetenv() {
		return setenv;
	}

	public void setSetenv(String setenv) {
		this.setenv = setenv;
	}

	public String getSetenvPermissions() {
		return setenvPermissions;
	}

	public void setSetenvPermissions(String setenvPermissions) {
		this.setenvPermissions = setenvPermissions;
	}

	public PropertyPlaceholderHelper getHelper() {
		return helper;
	}

	public void setHelper(PropertyPlaceholderHelper helper) {
		this.helper = helper;
	}

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	public SimpleFormatter getFormatter() {
		return formatter;
	}

	public void setFormatter(SimpleFormatter formatter) {
		this.formatter = formatter;
	}

}

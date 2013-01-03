package org.kuali.common.deploy.execute;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.junit.Test;
import org.kuali.common.util.LoggerLevel;
import org.kuali.common.util.LoggerUtils;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.UnixCmds;
import org.kuali.common.util.property.Constants;
import org.kuali.common.util.secure.ChannelUtils;
import org.kuali.common.util.secure.DefaultSecureChannel;
import org.kuali.common.util.secure.Result;
import org.kuali.common.util.secure.SecureChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.PropertyPlaceholderHelper;

public class DefaultDeployService implements DeployService {

	private static final Logger logger = LoggerFactory.getLogger(DefaultDeployService.class);
	static {
		System.setProperty("dns.hostname", "env7.ole.kuali.org");
	}
	Properties properties = getProperties();
	SecureChannel channel = getSecureChannel(properties);
	UnixCmds cmds = new UnixCmds();

	protected SecureChannel getSecureChannel(Properties properties) {
		String username = properties.getProperty("deploy.username");
		String hostname = properties.getProperty("deploy.hostname");
		DefaultSecureChannel channel = new DefaultSecureChannel();
		channel.setUsername(username);
		channel.setHostname(hostname);
		channel.setStrictHostKeyChecking(false);
		return channel;
	}

	protected Properties getProperties() {
		PropertyPlaceholderHelper helper = Constants.DEFAULT_PROPERTY_PLACEHOLDER_HELPER;
		Properties properties = PropertyUtils.load("classpath:org/kuali/common/deploy.properties");
		Properties resolved = PropertyUtils.getResolvedProperties(properties);
		properties.putAll(resolved);
		List<String> keys = PropertyUtils.getSortedKeys(properties);
		Properties returnProperties = new Properties();
		for (String key : keys) {
			String originalValue = properties.getProperty(key);
			String resolvedValue = helper.replacePlaceholders(originalValue, properties);
			returnProperties.setProperty(key, resolvedValue);
		}
		return returnProperties;
	}

	@Test
	public void execute() {
		try {
			channel.open();
			shutdown();
			cleanup();
			prepare();
			startup();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ChannelUtils.closeQuietly(channel);
		}
	}

	@Override
	public void cleanup() {
	}

	@Override
	public void prepare() {
	}

	@Override
	public void startup() {
	}

	@Override
	public void shutdown() {
		String login = properties.getProperty("tomcat.user");
		String script = properties.getProperty("tomcat.shutdown");
		String shutdown = cmds.su(login, script);
		logger.info("[{}]", shutdown);
		Result result = channel.executeCommand(shutdown);
		logResult(result);
	}

	protected void logResult(Result result) {
		LoggerUtils.logLines(result.getStdout(), logger, LoggerLevel.INFO);
		LoggerUtils.logLines(result.getStderr(), logger, LoggerLevel.WARN);
		if (result.getExitValue() != 0) {
			logger.warn("Exit value = {}", result.getExitValue());
		}
	}

	protected void validateResult(Result result) {
		validateResult(result, Arrays.asList(0));
	}

	protected void validateResult(Result result, List<Integer> exitValues) {
		for (Integer exitValue : exitValues) {
			if (exitValue.equals(result.getExitValue())) {
				return;
			}
		}
		throw new IllegalStateException("Exit value " + result.getExitValue() + " is not allowed");
	}
}

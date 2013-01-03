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

public class DefaultDeployService {

	private static final Logger logger = LoggerFactory.getLogger(DefaultDeployService.class);

	protected SecureChannel getSecureChannel() {
		DefaultSecureChannel channel = new DefaultSecureChannel();
		channel.setUsername("root");
		channel.setHostname("env7.ole.kuali.org");
		channel.setStrictHostKeyChecking(false);
		return channel;
	}

	PropertyPlaceholderHelper helper = Constants.DEFAULT_PROPERTY_PLACEHOLDER_HELPER;
	SecureChannel channel = getSecureChannel();
	UnixCmds cmds = new UnixCmds();

	protected String getValue(Properties properties, String key) {
		String originalValue = properties.getProperty(key);
		return helper.replacePlaceholders(originalValue, properties);
	}

	@Test
	public void execute() {
		try {
			Properties properties = PropertyUtils.load("classpath:org/kuali/common/deploy.properties");
			channel.open();
			doShutdown(properties);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ChannelUtils.closeQuietly(channel);
		}
	}

	protected void doShutdown(Properties properties) {
		String login = getValue(properties, "tomcat.user");
		String script = getValue(properties, "tomcat.shutdown");
		String shutdown = cmds.su(login, script);
		logger.info("[{}]", shutdown);
		Result result = channel.executeCommand(shutdown);
		logResult(result);
	}

	protected void doCleanup(Properties properties) {

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

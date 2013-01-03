package org.kuali.common.deploy.execute;

import org.junit.Test;
import org.kuali.common.util.secure.ChannelUtils;
import org.kuali.common.util.secure.DefaultSecureChannel;
import org.kuali.common.util.secure.SecureChannel;

public class DefaultDeployService {

	protected SecureChannel getSecureChannel() {
		DefaultSecureChannel channel = new DefaultSecureChannel();
		channel.setUsername("root");
		channel.setHostname("env7.ole.kuali.org");
		channel.setStrictHostKeyChecking(false);
		return channel;
	}

	@Test
	public void execute() {
		SecureChannel channel = null;
		try {
			channel = getSecureChannel();
			channel.open();
		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			ChannelUtils.closeQuietly(channel);
		}
	}

}

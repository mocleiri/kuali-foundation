package org.kuali.common.deploy.service;

import org.kuali.common.util.secure.ChannelUtils;
import org.kuali.common.util.secure.SecureChannel;

public class DefaultDeployService implements DeployService {

	ApplicationServer applicationServer;
	SecureChannel channel;

	@Override
	public void deploy() {
		try {
			channel.open();
			applicationServer.shutdown();
			applicationServer.startup();
		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			ChannelUtils.closeQuietly(channel);
		}
	}

}

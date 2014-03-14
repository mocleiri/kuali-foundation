package org.kuali.common.devops.ci;

import static org.kuali.common.util.base.Precondition.checkNotNull;

import java.io.IOException;

import org.kuali.common.util.channel.api.ChannelService;
import org.kuali.common.util.channel.model.ChannelContext;
import org.kuali.common.util.condition.Condition;

public final class VerifiedSSHCondition implements Condition {

	public VerifiedSSHCondition(ChannelService service, ChannelContext context) {
		this.service = checkNotNull(service, "service");
		this.context = checkNotNull(context, "context");
	}

	private final ChannelService service;
	private final ChannelContext context;

	@Override
	public boolean isTrue() {
		try {
			service.openChannel(context);
			return true;
		} catch (IOException e) {
			return false;
		}
	}

}

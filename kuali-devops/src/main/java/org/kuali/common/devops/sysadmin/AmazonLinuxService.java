package org.kuali.common.devops.sysadmin;

import java.util.List;

import org.kuali.common.util.Assert;

public final class AmazonLinuxService implements SysAdminService {

	public AmazonLinuxService(AmazonLinuxContext context) {
		Assert.noNulls(context);
		this.context = context;
	}

	private final AmazonLinuxContext context;

	@Override
	public void enableRootSSH() {
	}

	@Override
	public void resizeRootVolume() {
	}

	@Override
	public void update() {
	}

	@Override
	public void installPackages(List<String> packages) {
	}

	public AmazonLinuxContext getContext() {
		return context;
	}

}

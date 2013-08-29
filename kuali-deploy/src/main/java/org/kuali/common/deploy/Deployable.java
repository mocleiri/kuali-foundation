package org.kuali.common.deploy;

import org.kuali.common.util.Assert;

public final class Deployable {

	public Deployable(String local, String remote) {
		this(false, local, remote, null);
	}

	public Deployable(boolean filter, String local, String remote) {
		this(filter, local, remote, null);
	}

	public Deployable(boolean filter, String local, String remote, String permissions) {
		Assert.noBlanks(local, remote);
		this.filter = filter;
		this.local = local;
		this.remote = remote;
		this.permissions = permissions;
	}

	private final boolean filter;
	private final String local;
	private final String remote;
	private final String permissions;

	public boolean isFilter() {
		return filter;
	}

	public String getLocal() {
		return local;
	}

	public String getRemote() {
		return remote;
	}

	public String getPermissions() {
		return permissions;
	}

}

package org.kuali.common.deploy.appserver.tomcat;

import org.kuali.common.deploy.Deployable;
import org.kuali.common.util.Assert;

public final class TomcatScripts {

	private final Deployable setenv;

	public static class Builder {

		private Deployable setenv;

		public Builder(TomcatDirs dirs) {
			this.setenv = getSetEnv(dirs);
		}

		private Deployable getSetEnv(TomcatDirs dirs) {
			String local = "classpath:org/kuali/common/deploy/bin/setenv.sh";
			String remote = dirs.getBin() + "/setenv.sh";
			String permissions = "755";
			return new Deployable.Builder(local, remote).permissions(permissions).build();
		}

		public TomcatScripts build() {
			Assert.notNull(setenv);
			return new TomcatScripts(this);
		}

	}

	private TomcatScripts(Builder builder) {
		this.setenv = builder.setenv;
	}

	public Deployable getSetenv() {
		return setenv;
	}

}

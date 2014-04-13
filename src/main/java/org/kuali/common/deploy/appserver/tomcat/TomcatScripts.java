package org.kuali.common.deploy.appserver.tomcat;

import org.kuali.common.deploy.Deployable;
import org.kuali.common.util.Assert;

public final class TomcatScripts {

	private final String shutdown;
	private final String cleanup;
	private final String startup;
	private final Deployable setenv;

	public static class Builder {

		private String shutdown;
		private String cleanup;
		private String startup;
		private Deployable setenv;

		public Builder(TomcatDirs dirs) {
			this.shutdown = dirs.getBin() + "/forced-shutdown.sh";
			this.cleanup = dirs.getBin() + "/cleanup.sh";
			this.startup = dirs.getBin() + "/startup.sh";
			this.setenv = getDefaultSetEnv(dirs);
		}

		private Deployable getDefaultSetEnv(TomcatDirs dirs) {
			String local = "classpath:org/kuali/common/deploy/bin/setenv.sh";
			String remote = dirs.getBin() + "/setenv.sh";
			String permissions = "755";
			return new Deployable.Builder(local, remote).permissions(permissions).build();
		}

		public Builder setenv(Deployable setenv) {
			this.setenv = setenv;
			return this;
		}

		public Builder shutdown(String shutdown) {
			this.shutdown = shutdown;
			return this;
		}

		public Builder cleanup(String cleanup) {
			this.cleanup = cleanup;
			return this;
		}

		public Builder startup(String startup) {
			this.startup = startup;
			return this;
		}

		public TomcatScripts build() {
			Assert.noBlanks(shutdown, cleanup, startup);
			Assert.notNull(setenv);
			return new TomcatScripts(this);
		}

	}

	private TomcatScripts(Builder builder) {
		this.setenv = builder.setenv;
		this.shutdown = builder.shutdown;
		this.cleanup = builder.cleanup;
		this.startup = builder.startup;
	}

	public Deployable getSetenv() {
		return setenv;
	}

	public String getShutdown() {
		return shutdown;
	}

	public String getCleanup() {
		return cleanup;
	}

	public String getStartup() {
		return startup;
	}

}

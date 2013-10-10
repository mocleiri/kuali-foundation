package org.kuali.common.deploy.appserver.tomcat;

import java.util.List;

import org.kuali.common.deploy.Deployable;
import org.kuali.common.util.Assert;

import com.google.common.collect.ImmutableList;

public final class TomcatFiles {

	private final String rootWar;
	private final String serverXml;
	private final String webXml;
	private final List<Deployable> logsDirJsps;

	public static class Builder {

		private String rootWar;
		private String serverXml;
		private String webXml;
		private List<Deployable> logsDirJsps = ImmutableList.of();

		public Builder(TomcatDirs dirs) {
			this.rootWar = dirs.getWebapps() + "/ROOT.war";
			this.serverXml = dirs.getConf() + "/server.xml";
			this.webXml = dirs.getConf() + "/web.xml";
			this.logsDirJsps = getLogsDirJsps(dirs);
		}

		public Builder() {
		}

		private List<Deployable> getLogsDirJsps(TomcatDirs dirs) {
			Deployable env = new Deployable.Builder("classpath:org/kuali/common/deploy/jsp/env.jsp", dirs.getLib() + "/env.jsp").build();
			Deployable tail = new Deployable.Builder("classpath:org/kuali/common/deploy/jsp/tail.jsp", dirs.getLib() + "/tail.jsp").build();
			return ImmutableList.of(env, tail);
		}

		public Builder rootWar(String rootWar) {
			this.rootWar = rootWar;
			return this;
		}

		public Builder serverXml(String serverXml) {
			this.serverXml = serverXml;
			return this;
		}

		public Builder webXml(String webXml) {
			this.webXml = webXml;
			return this;
		}

		public Builder logsDirJsps(List<Deployable> logsDirJsps) {
			this.logsDirJsps = logsDirJsps;
			return this;
		}

		public TomcatFiles build() {
			Assert.noBlanks(rootWar, serverXml, webXml);
			Assert.notNull(logsDirJsps);
			this.logsDirJsps = ImmutableList.copyOf(logsDirJsps);
			return new TomcatFiles(this);
		}

	}

	private TomcatFiles(Builder builder) {
		this.rootWar = builder.rootWar;
		this.serverXml = builder.serverXml;
		this.webXml = builder.webXml;
		this.logsDirJsps = builder.logsDirJsps;
	}

	public String getRootWar() {
		return rootWar;
	}

	public String getServerXml() {
		return serverXml;
	}

	public String getWebXml() {
		return webXml;
	}

	public List<Deployable> getLogsDirJsps() {
		return logsDirJsps;
	}

}

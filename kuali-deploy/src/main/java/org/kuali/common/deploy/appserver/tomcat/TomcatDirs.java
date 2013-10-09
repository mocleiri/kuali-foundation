package org.kuali.common.deploy.appserver.tomcat;

import org.kuali.common.util.Assert;

public final class TomcatDirs {

	private final String basedir;
	private final String conf;
	private final String catalina;
	private final String logs;
	private final String bin;
	private final String lib;
	private final String webapps;
	private final String work;
	private final String temp;

	public static class Builder {

		private final String basedir;

		private String logs;
		private String bin;
		private String lib;
		private String webapps;
		private String work;
		private String temp;
		private String conf;
		private String catalina;

		public Builder(String basedir) {
			this.basedir = basedir;
			this.logs = basedir + "/logs";
			this.bin = basedir + "/bin";
			this.lib = basedir + "/lib";
			this.webapps = basedir + "/webapps";
			this.work = basedir + "/work";
			this.temp = basedir + "/temp";
			this.conf = basedir + "/conf";
			this.catalina = conf + "/Catalina"; // This dir under conf has a special meaning to Tomcat
		}

		public Builder logs(String logs) {
			this.logs = logs;
			return this;
		}

		public Builder bin(String bin) {
			this.bin = bin;
			return this;
		}

		public Builder lib(String lib) {
			this.lib = lib;
			return this;
		}

		public Builder webapps(String webapps) {
			this.webapps = webapps;
			return this;
		}

		public Builder work(String work) {
			this.work = work;
			return this;
		}

		public Builder temp(String temp) {
			this.temp = temp;
			return this;
		}

		public Builder conf(String conf) {
			this.conf = conf;
			return this;
		}

		public Builder catalina(String catalina) {
			this.catalina = catalina;
			return this;
		}

		public TomcatDirs build() {
			Assert.noBlanks(basedir, logs, bin, lib, webapps, work, temp, conf, catalina);
			return new TomcatDirs(this);
		}

	}

	private TomcatDirs(Builder builder) {
		this.basedir = builder.basedir;
		this.logs = builder.logs;
		this.bin = builder.bin;
		this.lib = builder.lib;
		this.webapps = builder.webapps;
		this.work = builder.work;
		this.temp = builder.temp;
		this.conf = builder.conf;
		this.catalina = builder.catalina;
	}

	public String getConf() {
		return conf;
	}

	public String getLogs() {
		return logs;
	}

	public String getBin() {
		return bin;
	}

	public String getLib() {
		return lib;
	}

	public String getWebapps() {
		return webapps;
	}

	public String getWork() {
		return work;
	}

	public String getTemp() {
		return temp;
	}

	public String getBasedir() {
		return basedir;
	}

	public String getCatalina() {
		return catalina;
	}

}

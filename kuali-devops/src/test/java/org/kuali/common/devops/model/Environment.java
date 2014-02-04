package org.kuali.common.devops.model;

import java.util.Comparator;

import org.kuali.common.util.build.ValidatingBuilder;
import org.kuali.common.util.validate.IdiotProofImmutable;

import com.google.common.base.Optional;

@IdiotProofImmutable
public final class Environment implements Comparable<Environment> {

	private static final Comparator<String> COMPARATOR = new EnvStringComparator();

	private final String name;
	private final String fqdn;
	private final EC2Instance server;
	private final Tomcat tomcat;
	private final String java;
	private final Optional<Application> application;

	@Override
	public int compareTo(Environment other) {
		return COMPARATOR.compare(name, other.getName());
	}

	private Environment(Builder builder) {
		this.name = builder.name;
		this.fqdn = builder.fqdn;
		this.server = builder.server;
		this.tomcat = builder.tomcat;
		this.java = builder.java;
		this.application = builder.application;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder extends ValidatingBuilder<Environment> implements Comparable<Environment.Builder> {

		private String name;
		private String fqdn;
		private EC2Instance server;
		private Tomcat tomcat;
		private String java;
		private Optional<Application> application = Optional.absent();

		@Override
		public int compareTo(Environment.Builder other) {
			return COMPARATOR.compare(name, other.getName());
		}

		public Builder name(String name) {
			this.name = name;
			return this;
		}

		public Builder fqdn(String fqdn) {
			this.fqdn = fqdn;
			return this;
		}

		public Builder server(EC2Instance server) {
			this.server = server;
			return this;
		}

		public Builder tomcat(Tomcat tomcat) {
			this.tomcat = tomcat;
			return this;
		}

		public Builder java(String java) {
			this.java = java;
			return this;
		}

		public Builder application(Optional<Application> application) {
			this.application = application;
			return this;
		}

		@Override
		public Environment getInstance() {
			return new Environment(this);
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getFqdn() {
			return fqdn;
		}

		public void setFqdn(String fqdn) {
			this.fqdn = fqdn;
		}

		public EC2Instance getServer() {
			return server;
		}

		public void setServer(EC2Instance server) {
			this.server = server;
		}

		public Tomcat getTomcat() {
			return tomcat;
		}

		public void setTomcat(Tomcat tomcat) {
			this.tomcat = tomcat;
		}

		public String getJava() {
			return java;
		}

		public void setJava(String java) {
			this.java = java;
		}

		public Optional<Application> getApplication() {
			return application;
		}

		public void setApplication(Optional<Application> application) {
			this.application = application;
		}

	}

	public String getName() {
		return name;
	}

	public String getFqdn() {
		return fqdn;
	}

	public EC2Instance getServer() {
		return server;
	}

	public Tomcat getTomcat() {
		return tomcat;
	}

	public String getJava() {
		return java;
	}

	public Optional<Application> getApplication() {
		return application;
	}

}

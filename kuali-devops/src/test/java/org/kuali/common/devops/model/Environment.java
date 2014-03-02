package org.kuali.common.devops.model;

import static com.google.common.base.Optional.absent;

import java.util.Comparator;

import org.kuali.common.core.build.ValidatingBuilder;
import org.kuali.common.core.validate.annotation.IdiotProofImmutable;
import org.kuali.common.devops.metadata.model.EC2Instance;
import org.kuali.common.devops.metadata.model.Memory;

import com.google.common.base.Optional;

@IdiotProofImmutable
public final class Environment implements Comparable<Environment> {

	private static final Comparator<String> COMPARATOR = new EnvStringComparator();

	private final String name;
	private final String fqdn;
	private final EC2Instance server;
	private final Optional<String> java;
	private final Optional<Tomcat> tomcat;
	private final Optional<Application> application;
	private final Optional<Memory> memory;
	private final Status status;

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
		this.status = builder.status;
		this.memory = builder.memory;
	}

	public static Environment create(String name, String fqdn, EC2Instance server) {
		return builder().name(name).fqdn(fqdn).server(server).build();
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder extends ValidatingBuilder<Environment> implements Comparable<Environment.Builder> {

		private String name;
		private String fqdn;
		private EC2Instance server;
		private Optional<Tomcat> tomcat = absent();
		private Optional<String> java = absent();
		private Optional<Application> application = absent();
		private Status status = Status.UNKNOWN;
		private Optional<Memory> memory = absent();

		@Override
		public int compareTo(Environment.Builder other) {
			return COMPARATOR.compare(name, other.getName());
		}

		public Builder memory(Optional<Memory> memory) {
			this.memory = memory;
			return this;
		}

		public Builder memory(Memory memory) {
			return memory(Optional.of(memory));
		}

		public Builder status(String status) {
			this.name = status;
			return this;
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

		public Builder tomcat(Optional<Tomcat> tomcat) {
			this.tomcat = tomcat;
			return this;
		}

		public Builder tomcat(Tomcat tomcat) {
			return tomcat(Optional.of(tomcat));
		}

		public Builder java(String java) {
			return java(Optional.of(java));
		}

		public Builder java(Optional<String> java) {
			this.java = java;
			return this;
		}

		public Builder application(Optional<Application> application) {
			this.application = application;
			return this;
		}

		@Override
		public Environment build() {
			return validate(new Environment(this));
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

		public Optional<String> getJava() {
			return java;
		}

		public void setJava(Optional<String> java) {
			this.java = java;
		}

		public Optional<Application> getApplication() {
			return application;
		}

		public void setApplication(Optional<Application> application) {
			this.application = application;
		}

		public Optional<Tomcat> getTomcat() {
			return tomcat;
		}

		public void setTomcat(Optional<Tomcat> tomcat) {
			this.tomcat = tomcat;
		}

		public Status getStatus() {
			return status;
		}

		public void setStatus(Status status) {
			this.status = status;
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

	public Optional<Tomcat> getTomcat() {
		return tomcat;
	}

	public Optional<String> getJava() {
		return java;
	}

	public Optional<Application> getApplication() {
		return application;
	}

	public Status getStatus() {
		return status;
	}

	public Optional<Memory> getMemory() {
		return memory;
	}

}

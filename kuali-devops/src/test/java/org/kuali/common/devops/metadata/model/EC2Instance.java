package org.kuali.common.devops.metadata.model;

import static com.google.common.base.Optional.absent;

import java.util.List;

import javax.validation.constraints.Min;

import org.kuali.common.core.build.ValidatingBuilder;
import org.kuali.common.core.validate.annotation.IdiotProofImmutable;
import org.kuali.common.devops.metadata.model.format.TagListFormat;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;

@IdiotProofImmutable
public final class EC2Instance implements Comparable<EC2Instance> {

	private final String id;
	private final Optional<String> name;
	private final Optional<String> publicDnsName;
	private final String type;
	private final String ami;
	private final String state;
	@TagListFormat
	private final ImmutableList<EC2Tag> tags;

	@Min(1)
	private final long launchTime;

	@Override
	public int compareTo(EC2Instance other) {
		return Double.compare(launchTime, other.getLaunchTime());
	}

	private EC2Instance(Builder builder) {
		this.id = builder.id;
		this.name = builder.name;
		this.publicDnsName = builder.publicDnsName;
		this.type = builder.type;
		this.launchTime = builder.launchTime;
		this.ami = builder.ami;
		this.state = builder.state;
		this.tags = ImmutableList.copyOf(builder.tags);
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder extends ValidatingBuilder<EC2Instance> {

		private String id;
		private Optional<String> name = absent();
		private Optional<String> publicDnsName = absent();
		private String type;
		private long launchTime;
		private String ami;
		private String state;
		private List<EC2Tag> tags = ImmutableList.of();

		public Builder tags(List<EC2Tag> tags) {
			this.tags = tags;
			return this;
		}

		public Builder state(String state) {
			this.state = state;
			return this;
		}

		public Builder ami(String ami) {
			this.ami = ami;
			return this;
		}

		public Builder id(String id) {
			this.id = id;
			return this;
		}

		public Builder name(Optional<String> name) {
			this.name = name;
			return this;
		}

		public Builder name(String name) {
			return name(Optional.of(name));
		}

		public Builder publicDnsName(Optional<String> publicDnsName) {
			this.publicDnsName = publicDnsName;
			return this;
		}

		public Builder publicDnsName(String publicDnsName) {
			return publicDnsName(Optional.of(publicDnsName));
		}

		public Builder type(String type) {
			this.type = type;
			return this;
		}

		public Builder launchTime(long launchTime) {
			this.launchTime = launchTime;
			return this;
		}

		@Override
		public EC2Instance build() {
			return validate(new EC2Instance(this));
		}

		public Optional<String> getName() {
			return name;
		}

		public void setName(Optional<String> name) {
			this.name = name;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public long getLaunchTime() {
			return launchTime;
		}

		public void setLaunchTime(long launchTime) {
			this.launchTime = launchTime;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public Optional<String> getPublicDnsName() {
			return publicDnsName;
		}

		public void setPublicDnsName(Optional<String> publicDnsName) {
			this.publicDnsName = publicDnsName;
		}

		public String getAmi() {
			return ami;
		}

		public void setAmi(String ami) {
			this.ami = ami;
		}

		public String getState() {
			return state;
		}

		public void setState(String state) {
			this.state = state;
		}

		public List<EC2Tag> getTags() {
			return tags;
		}

		public void setTags(List<EC2Tag> tags) {
			this.tags = tags;
		}
	}

	public String getId() {
		return id;
	}

	public Optional<String> getName() {
		return name;
	}

	public Optional<String> getPublicDnsName() {
		return publicDnsName;
	}

	public String getType() {
		return type;
	}

	public long getLaunchTime() {
		return launchTime;
	}

	public String getAmi() {
		return ami;
	}

	public String getState() {
		return state;
	}

	public List<EC2Tag> getTags() {
		return tags;
	}

}

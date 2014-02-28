package org.kuali.common.devops.json.pojo;

import static com.google.common.base.Optional.absent;

import java.util.Date;
import java.util.Set;
import java.util.TimeZone;

import javax.validation.ConstraintViolation;

import org.kuali.common.util.build.ViolationsBuilder;
import org.kuali.common.util.validate.IdiotProofImmutable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.common.base.Optional;

@IdiotProofImmutable
@JsonDeserialize(builder = Apple.Builder.class)
public final class Apple {

	private final String color;
	private final Optional<Double> weight;
	private final boolean rotten;
	private final Date purchased;
	private final TimeZone timeZone;

	private Apple(Builder builder) {
		this.color = builder.color;
		this.weight = builder.weight;
		this.rotten = builder.rotten;
		this.purchased = builder.purchased;
		this.timeZone = builder.timeZone;
	}

	public static Apple newApple(String color) {
		return builder(color).build();
	}

	public static Builder builder(String color) {
		return new Builder(color);
	}

	public static class Builder extends ViolationsBuilder<Apple> {

		private final String color;
		private Optional<Double> weight = absent();
		private boolean rotten = false;
		private Date purchased = new Date();
		private TimeZone timeZone = TimeZone.getDefault();

		@JsonCreator
		public Builder(@JsonProperty("color") String color) {
			this.color = color;
		}

		@Override
		public Apple build() {
			return validate(make());
		}

		@Override
		public Set<ConstraintViolation<Apple>> violations() {
			return violations(make());
		}

		private Apple make() {
			return new Apple(this);
		}

		public Builder withPurchased(Date purchased) {
			this.purchased = purchased;
			return this;
		}

		public Builder withRotten(boolean rotten) {
			this.rotten = rotten;
			return this;
		}

		@JsonSetter
		public Builder withWeight(Optional<Double> weight) {
			this.weight = weight;
			return this;
		}

		public Builder withWeight(double weight) {
			return withWeight(Optional.of(weight));
		}

		public String getColor() {
			return color;
		}

		public Optional<Double> getWeight() {
			return weight;
		}

		public void setWeight(Optional<Double> weight) {
			this.weight = weight;
		}

		public boolean isRotten() {
			return rotten;
		}

		public void setRotten(boolean rotten) {
			this.rotten = rotten;
		}

		public Date getPurchased() {
			return purchased;
		}

		public void setPurchased(Date purchased) {
			this.purchased = purchased;
		}

		public TimeZone getTimeZone() {
			return timeZone;
		}

		public void setTimeZone(TimeZone timeZone) {
			this.timeZone = timeZone;
		}

	}

	public String getColor() {
		return color;
	}

	public Optional<Double> getWeight() {
		return weight;
	}

	public boolean isRotten() {
		return rotten;
	}

	public Date getPurchased() {
		return purchased;
	}

	public TimeZone getTimeZone() {
		return timeZone;
	}

}

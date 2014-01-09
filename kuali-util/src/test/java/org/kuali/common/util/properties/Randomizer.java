package org.kuali.common.util.properties;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;

import java.util.Random;

import org.kuali.common.util.log.LoggerUtils;
import org.slf4j.Logger;

public final class Randomizer {

	private static final Logger logger = LoggerUtils.make();

	private final Random random;

	public int getInteger(String rangeSpec) {
		String[] range = rangeSpec.split("-");
		checkState(range.length == 2, "Invalid range specifier: %s", rangeSpec);
		int from = Integer.parseInt(range[0].trim());
		int to = Integer.parseInt(range[1].trim());
		return getInteger(from, to);
	}

	private int getInteger(int from, int to) {
		if (from > to) {
			logger.warn("%s is greater than %s!!!  Switching 'from' and 'to' values so random number generation will work", from, to);
			int tmp = from;
			from = to;
			to = tmp;
		}
		// not very random
		if (from == to) {
			logger.warn("Specified random range is %s-%s!!!  No random number will be generated.", from, to);
			return from;
		} else {
			return from + random.nextInt((to - from) + 1);
		}
	}

	public static Builder builder() {
		return new Builder();
	}

	private Randomizer(Builder builder) {
		this.random = builder.random;
	}

	public static class Builder {

		private Random random;

		public Builder() {
			this(System.currentTimeMillis());
		}

		public Builder(long seed) {
			this(new Random(seed));
		}

		public Builder(Random random) {
			this.random = random;
		}

		public Builder random(Random random) {
			this.random = random;
			return this;
		}

		public Randomizer build() {
			Randomizer instance = new Randomizer(this);
			validate(instance);
			return instance;
		}

		private static void validate(Randomizer instance) {
			checkNotNull(instance.random, "random cannot be null");
		}

		public Random getRandom() {
			return random;
		}

		public void setRandom(Random random) {
			this.random = random;
		}
	}

}

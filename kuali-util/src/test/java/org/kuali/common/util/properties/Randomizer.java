package org.kuali.common.util.properties;

import static com.google.common.base.Preconditions.checkState;

import java.util.Random;

import com.google.common.base.Preconditions;

public final class Randomizer {

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
			int tmp = from;
			from = to;
			to = tmp;
		}
		// not very random
		if (from == to) {
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
			Preconditions.checkNotNull(instance.random, "random cannot be null");
		}
	}

}

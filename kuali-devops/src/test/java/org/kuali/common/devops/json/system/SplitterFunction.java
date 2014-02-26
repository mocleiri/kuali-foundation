package org.kuali.common.devops.json.system;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.collect.Sets.newHashSet;
import static org.kuali.common.util.base.Precondition.checkNotBlank;
import static org.kuali.common.util.base.Precondition.checkNotNull;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;

import org.kuali.common.util.build.ValidatingBuilder;
import org.kuali.common.util.validate.IdiotProofImmutable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.common.base.Function;
import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableSet;

@IdiotProofImmutable
@JsonDeserialize(builder = SplitterFunction.Builder.class)
public class SplitterFunction implements Function<Set<String>, Set<String>> {

	private final char separator;
	private final boolean allowBlanks;
	@JsonIgnore
	private final Splitter splitter;

	/**
	 * Convert a series of delimited strings into a unique set of individual elements.
	 * 
	 * <pre>
	 * java.class.path    -> java
	 * java.class.version    java.class
	 *                       java.class.path
	 *                       java.class.version
	 * </pre>
	 * 
	 * @throws IllegalArgumentException
	 *             If splitting the individual strings into elements produces blank tokens and allowBlanks is false
	 */
	@Override
	public Set<String> apply(Set<String> strings) {
		checkNotNull(strings, "strings");
		Set<String> set = newHashSet();
		for (String string : strings) {
			set.addAll(apply(string));
		}
		return ImmutableSet.copyOf(set);
	}

	/**
	 * Convert a delimited string into a unique set of individual elements.
	 * 
	 * <pre>
	 * java.class.path -> java
	 *                    java.class
	 *                    java.class.path
	 * </pre>
	 * 
	 * @throws IllegalArgumentException
	 *             If splitting the string into elements produces blank tokens and allow blanks is false
	 */
	protected Set<String> apply(String string) {

		// Split the key into tokens
		List<String> tokens = splitter.splitToList(string);

		// Setup some storage for the strings we are creating
		Set<String> strings = newHashSet();

		// Allocate a string builder
		StringBuilder sb = new StringBuilder();

		// Iterate over the tokens to create unique string elements
		for (int i = 0; i < tokens.size(); i++) {

			// append the separator char unless this is the first loop iteration
			sb = (i != 0) ? sb.append(separator) : sb;

			// Extract the token, checking to make sure it isn't blank
			String token = allowBlanks ? tokens.get(i) : checkNotBlank(tokens.get(i), "token");

			// Append the current token to create a new path element
			String element = sb.append(token).toString();

			// Add the new path element to our set
			// TODO This checkArgument call should be superfluous now that checkNotBlank is called on each token
			checkArgument(strings.add(element), "%s is a duplicate element", element);
		}

		// Return what we've got
		return ImmutableSet.copyOf(strings);
	}

	private SplitterFunction(Builder builder) {
		this.separator = builder.separator;
		this.allowBlanks = builder.allowBlanks;
		this.splitter = Splitter.on(separator);
	}

	public static SplitterFunction newSplitterFunction(char separator, boolean allowBlanks) {
		return builder().withAllowBlanks(allowBlanks).withSeparator(separator).build();
	}

	public static SplitterFunction newSplitterFunction() {
		return builder().build();
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder extends ValidatingBuilder<SplitterFunction> {

		private char separator = '.';
		private boolean allowBlanks = false;

		public Builder withSeparator(char separator) {
			this.separator = separator;
			return this;
		}

		public Builder withAllowBlanks(boolean allowBlanks) {
			this.allowBlanks = allowBlanks;
			return this;
		}

		@Override
		public SplitterFunction build() {
			return validate(make());
		}

		@Override
		public Set<ConstraintViolation<SplitterFunction>> violations() {
			return violations(make());
		}

		private SplitterFunction make() {
			return new SplitterFunction(this);
		}

	}

	public char getSeparator() {
		return separator;
	}

	public boolean isAllowBlanks() {
		return allowBlanks;
	}

}

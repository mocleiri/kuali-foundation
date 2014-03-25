package org.kuali.common.util.base.string;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.collect.Sets.newHashSet;
import static org.kuali.common.util.base.Precondition.checkNotBlank;
import static org.kuali.common.util.base.Precondition.checkNotNull;

import java.util.List;
import java.util.Set;

import com.google.common.base.Function;
import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableSet;

/**
 * <p>
 * Convert a set of delimited strings into an immutable set of individual elements.
 * </p>
 * 
 * A set containing these two strings:
 * 
 * <pre>
 * java.class.path
 * java.class.version
 * </pre>
 * 
 * Converts to a set containing these four strings:
 * 
 * <pre>
 * java
 * java.class
 * java.class.path
 * java.class.version
 * </pre>
 */
public final class SplitterFunction implements Function<Set<String>, Set<String>> {

	public SplitterFunction() {
		this(".");
	}

	public SplitterFunction(String separator) {
		this.separator = separator;
		this.splitter = Splitter.on(separator);
	}

	private final Splitter splitter;
	private final String separator;

	/**
	 * <p>
	 * Convert a set of delimited strings into an immutable set of individual elements.
	 * </p>
	 * 
	 * A set containing these two strings:
	 * 
	 * <pre>
	 * java.class.path
	 * java.class.version
	 * </pre>
	 * 
	 * Converts to a set containing these four strings:
	 * 
	 * <pre>
	 * java
	 * java.class
	 * java.class.path
	 * java.class.version
	 * </pre>
	 * 
	 * @throws IllegalArgumentException
	 *             If splitting the individual strings into elements produces blank tokens
	 * @throws NullPointerException
	 *             If strings is null or contains null elements
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
	 * Convert a delimited string into a set of individual elements.
	 * 
	 * <pre>
	 * java.class.path -> java
	 *                    java.class
	 *                    java.class.path
	 * </pre>
	 * 
	 * @throws IllegalArgumentException
	 *             If splitting the string into elements produces blank tokens
	 */
	protected Set<String> apply(String string) {
		checkNotNull(string, "string");

		// Split the key into tokens
		List<String> tokens = splitter.splitToList(string);

		// Setup some storage for the strings we are creating
		Set<String> strings = newHashSet();

		// Allocate a string builder
		StringBuilder sb = new StringBuilder();

		// Iterate over the tokens to create unique string elements
		for (int i = 0; i < tokens.size(); i++) {

			// append the separator unless this is the first loop iteration
			sb = (i != 0) ? sb.append(separator) : sb;

			// Extract the token, checking to make sure it isn't blank
			String token = checkNotBlank(tokens.get(i), "token");

			// Append the current token to create a new element
			String element = sb.append(token).toString();

			// Make sure it was actually added
			// TODO Don't think checkArgument() here is necessary since checkNotBlank() prevents token from being the empty string
			checkArgument(strings.add(element), "%s is a duplicate token -> [%s]", element, string);
		}

		// Return what we've got
		return strings;
	}

	public String getSeparator() {
		return separator;
	}

	public Splitter getSplitter() {
		return splitter;
	}

}

package org.kuali.common.devops.json.system;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.collect.Sets.newHashSet;
import static org.kuali.common.util.base.Precondition.checkNotBlank;
import static org.kuali.common.util.base.Precondition.checkNotNull;

import java.util.List;
import java.util.Set;

import com.google.common.base.Function;
import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableSet;

public class SplitterFunction implements Function<Set<String>, Set<String>> {

	public SplitterFunction() {
		this('.', false);
	}

	public SplitterFunction(char separator, boolean allowBlanks) {
		this.separator = separator;
		this.allowBlanks = allowBlanks;
		this.splitter = Splitter.on(separator);
	}

	private final char separator;
	private final boolean allowBlanks;
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
	 *             If splitting the individual keys into path elements produces blank tokens and allowBlanks is false
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
	 *             If splitting the key into path elements produces blank tokens and allow blanks is false
	 */
	protected Set<String> apply(String key) {

		// Split the key into tokens
		List<String> tokens = splitter.splitToList(key);

		// Setup some storage for the paths we are creating
		Set<String> paths = newHashSet();

		// Allocate a string builder
		StringBuilder sb = new StringBuilder();

		// Iterate over the tokens to create path elements
		for (int i = 0; i < tokens.size(); i++) {

			// append the separator char unless this is the first loop iteration
			sb = (i != 0) ? sb.append(separator) : sb;

			// Extract the token, checking to make sure it isn't blank
			String token = allowBlanks ? tokens.get(i) : checkNotBlank(tokens.get(i), "token");

			// Append the current token to create a new path element
			String path = sb.append(token).toString();

			// Add the new path element to our set
			// TODO This checkArgument call should be superfluous now that checkNotBlank is called on each token
			checkArgument(paths.add(path), "%s is a duplicate path element", path);
		}

		// Return what we've got
		return ImmutableSet.copyOf(paths);
	}

}

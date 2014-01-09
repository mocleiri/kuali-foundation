package org.kuali.common.util.metainf.model;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Comparator;

/**
 * <p>
 * Sort lexicographically by directory structure, then filename.
 * </p>
 * 
 * For example:
 * 
 * <pre>
 *   2 - /a/foo2.txt     1 - /a/foo1.txt
 *   3 - /a/b/foo.txt    2 - /a/foo2.txt
 *   1 - /a/foo1.txt     3 - /a/b/foo.txt
 * </pre>
 * 
 */
public class PathComparator implements Comparator<String> {

	@Override
	public int compare(String path1, String path2) {
		checkNotNull(path1, "'path1' cannot be null");
		checkNotNull(path2, "'path2' cannot be null");

		// Split the paths up into tokens
		// Each token represents a directory in the directory structure
		// The final token represents the filename
		String[] tokens1 = getPathTokens(path1);
		String[] tokens2 = getPathTokens(path2);

		// Compare the path tokens
		return compare(tokens1, tokens2);
	}

	/**
	 * Iterate over the tokens from both locations
	 */
	protected int compare(String[] tokens1, String[] tokens2) {

		// Stop iterating when we hit the end of either array
		for (int i = 0; i < tokens1.length && i < tokens2.length; i++) {

			// Compare the 2 tokens at this index
			int compare = compare(i, tokens1, tokens2);

			// If the comparison comes back as anything but zero, we are done
			if (compare != 0) {
				return compare;
			}
		}

		// If we get here, both arrays have the exact same number of tokens and all of the tokens in both arrays are identical
		return 0;
	}

	protected int compare(int index, String[] tokens1, String[] tokens2) {
		// We hit the end of 'one' but 'two' still has more tokens
		// 'one' is less than 'two'
		if (isLastToken(index, tokens1) && !isLastToken(index, tokens2)) {
			return -1;
		}

		// We hit the end of 'two' but 'one' still has more tokens
		// 'one' is greater than 'two'
		if (!isLastToken(index, tokens1) && isLastToken(index, tokens2)) {
			return 1;
		}

		// The 2 tokens at this index are either:
		// 1 - The last token for both paths (and therefore the filename)
		// OR
		// 2 - NOT the last token for both paths (and therefore a directory)
		return tokens1[index].compareTo(tokens2[index]);
	}

	/**
	 * Replace backslashes (if there are any) with forward slashes and split the string by forward slash
	 */
	protected String[] getPathTokens(String s) {
		return s.replace('\\', '/').split("/");
	}

	protected boolean isLastToken(int index, String[] tokens) {
		return index == tokens.length - 1;
	}

}

package org.kuali.common.util.metainf.model;

import static com.google.common.base.Preconditions.checkArgument;
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

		// Split them up into path tokens
		String[] tokens1 = path1.split("/");
		String[] tokens2 = path2.split("/");

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

		// If we get here, the locations are identical
		return 0;
	}

	protected int compare(int index, String[] tokens1, String[] tokens2) {
		checkArgument(index < tokens1.length && index < tokens2.length && index >= 0, "index=%s but must be >= 0 and < %s", index, Math.min(tokens1.length, tokens2.length));

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

		// The 2 tokens at this index are either the last token for both OR not the last token for either.
		return tokens1[index].compareTo(tokens2[index]);
	}

	protected boolean isLastToken(int index, String[] tokens) {
		return index == tokens.length - 1;
	}

}

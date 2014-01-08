package org.kuali.common.util.metainf.model;

import java.util.Comparator;

/**
 * <p>
 * Sort by directory structure, then filename.
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
public class MetaInfResourcePathComparator implements Comparator<MetaInfResource> {

	@Override
	public int compare(MetaInfResource one, MetaInfResource two) {

		// Split them up into path tokens
		String[] tokens1 = one.getLocation().split("/");
		String[] tokens2 = two.getLocation().split("/");

		// Iterate over the tokens from both locations
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

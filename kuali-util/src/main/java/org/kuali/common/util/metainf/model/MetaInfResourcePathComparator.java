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

		// Compare each token
		for (int i = 0; i < tokens1.length && i < tokens2.length; i++) {

			// We hit the end of 'one' but 'two' still has more tokens
			// 'one' is less than 'two'
			if (isLastToken(i, tokens1) && !isLastToken(i, tokens2)) {
				return -1;
			}

			// We hit the end of 'two' but 'one' still has more tokens
			// 'one' is greater than 'two'
			if (!isLastToken(i, tokens1) && isLastToken(i, tokens2)) {
				return 1;
			}

			// We are not at the end of either path, compare the two tokens
			int compare = tokens1[i].compareTo(tokens2[i]);

			// If the tokens are not equal, we are done
			if (compare != 0) {
				return compare;
			}
		}

		// If we get here, the locations are identical
		return 0;
	}

	protected boolean isLastToken(int index, String[] tokens) {
		return index == tokens.length - 1;
	}

}

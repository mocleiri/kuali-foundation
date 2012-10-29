package org.kuali.common.aws.s3.pojo;

import java.util.Comparator;

public class BucketSummaryLineComparator implements Comparator<BucketSummaryLine> {

	@Override
	public int compare(BucketSummaryLine one, BucketSummaryLine two) {
		if (one.getBucket().equals(two.getBucket())) {
			return one.getDate().compareTo(two.getDate());
		} else {
			return one.getBucket().compareTo(two.getBucket());
		}
	}
}

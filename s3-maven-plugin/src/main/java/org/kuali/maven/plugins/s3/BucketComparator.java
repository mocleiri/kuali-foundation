package org.kuali.maven.plugins.s3;

import java.util.Comparator;

import com.amazonaws.services.s3.model.Bucket;

public class BucketComparator implements Comparator<Bucket> {

	@Override
	public int compare(Bucket bucket1, Bucket bucket2) {
		return bucket1.getName().compareTo(bucket2.getName());
	}

}

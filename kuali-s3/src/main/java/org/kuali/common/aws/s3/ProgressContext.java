package org.kuali.common.aws.s3;

import org.kuali.common.util.LongCounter;
import org.kuali.common.util.ProgressInformer;

public class ProgressContext {

	LongCounter counter;
	ProgressInformer informer;
	long interval;

}

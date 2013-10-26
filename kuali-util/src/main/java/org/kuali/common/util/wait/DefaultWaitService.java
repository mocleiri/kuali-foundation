package org.kuali.common.util.wait;

public class DefaultWaitService implements WaitService {

	@Override
	public WaitResult wait(WaitContext context) {
		long start = System.currentTimeMillis();
		return new WaitResult.Builder(start, System.currentTimeMillis() - start).build();
	}

}

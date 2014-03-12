package org.kuali.maven.plugins.spring;

import org.kuali.common.util.Assert;

public class MojoRunner implements Runnable {

	public MojoRunner(AbstractSpringMojo mojo) {
		Assert.noNulls(mojo);
		this.mojo = mojo;
	}

	private final AbstractSpringMojo mojo;

	@Override
	public void run() {
		try {
			mojo.execute();
		} catch (Exception e) {
			throw new IllegalStateException("Unexpected mojo error", e);
		}
	}

	public AbstractSpringMojo getMojo() {
		return mojo;
	}

}

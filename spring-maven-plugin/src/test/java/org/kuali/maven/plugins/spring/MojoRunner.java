package org.kuali.maven.plugins.spring;

import org.apache.maven.plugin.AbstractMojo;
import org.kuali.common.util.Assert;

public class MojoRunner implements Runnable {

	public MojoRunner(AbstractMojo mojo) {
		Assert.noNulls(mojo);
		this.mojo = mojo;
	}

	private final AbstractMojo mojo;

	@Override
	public void run() {
		try {
			mojo.execute();
		} catch (Exception e) {
			throw new IllegalStateException("Unexpected mojo error", e);
		}
	}

	public AbstractMojo getMojo() {
		return mojo;
	}

}

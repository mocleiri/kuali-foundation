package org.kuali.common.util.builder;

import org.kuali.common.util.Assert;
import org.kuali.common.util.enc.EncryptionService;
import org.kuali.common.util.spring.env.EnvironmentService;

public final class BuilderContext {

	public BuilderContext(EnvironmentService env, EncryptionService enc) {
		Assert.noNulls(env, enc);
		this.env = env;
		this.enc = enc;
	}

	private final EnvironmentService env;
	private final EncryptionService enc;

	public EnvironmentService getEnv() {
		return env;
	}

	public EncryptionService getEnc() {
		return enc;
	}

}

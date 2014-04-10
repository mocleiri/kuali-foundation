package org.kuali.common.util.encrypt.provider;

import static org.kuali.common.util.base.Precondition.checkNotNull;

import org.kuali.common.util.encrypt.EncryptionContext;

public final class ChainContext {

	public ChainContext(EncryptionContext context, EncryptionContextProvider provider) {
		this.context = checkNotNull(context, "context");
		this.provider = checkNotNull(provider, "provider");
	}

	private final EncryptionContext context;
	private final EncryptionContextProvider provider;

	public EncryptionContext getContext() {
		return context;
	}

	public EncryptionContextProvider getProvider() {
		return provider;
	}

}

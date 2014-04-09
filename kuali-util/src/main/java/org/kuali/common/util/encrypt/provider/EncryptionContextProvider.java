package org.kuali.common.util.encrypt.provider;

import org.kuali.common.util.encrypt.EncryptionContext;

import com.google.common.base.Optional;

public interface EncryptionContextProvider {

	Optional<EncryptionContext> getEncryptionContext();

}

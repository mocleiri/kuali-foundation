package org.kuali.common.util.encrypt.provider;

import org.kuali.common.util.encrypt.EncContext;

import com.google.common.base.Optional;

public interface EncryptionContextProvider {

	Optional<EncContext> getEncryptionContext();

}

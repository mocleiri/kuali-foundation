package org.kuali.common.util.ssh.api;

import org.kuali.common.util.ssh.model.GenerateKeyPairContext;
import org.kuali.common.util.ssh.model.KeyPair;

/**
 * @deprecated use SshService from kuali-core
 */
@Deprecated
public interface SshService {

	KeyPair generateKeyPair(GenerateKeyPairContext context);

}

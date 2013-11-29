package org.kuali.common.util.ssh.api;

import org.kuali.common.util.enc.KeyPair;
import org.kuali.common.util.ssh.model.GenerateKeyPairContext;

public interface SshService {

	KeyPair generateKeyPair(GenerateKeyPairContext context);

}

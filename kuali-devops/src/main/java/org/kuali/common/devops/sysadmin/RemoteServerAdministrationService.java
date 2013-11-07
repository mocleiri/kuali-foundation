package org.kuali.common.devops.sysadmin;

public interface RemoteServerAdministrationService {

	void enableRootSSH(String publicKey);

	void resizeRootVolume();

}

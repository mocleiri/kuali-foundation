package org.kuali.common.devops.sysadmin;

import java.util.List;

public interface RemoteServerService {

	void enableRootSSH(String publicKey);

	void resizeRootVolume();

	void update();

	void installPackages(List<String> packages);

}

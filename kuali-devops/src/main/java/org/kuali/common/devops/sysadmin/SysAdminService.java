package org.kuali.common.devops.sysadmin;

import java.util.List;

public interface SysAdminService {

	void enableRootSSH();

	void resizeRootVolume();

	void update();

	void installPackages(List<String> packages);

}

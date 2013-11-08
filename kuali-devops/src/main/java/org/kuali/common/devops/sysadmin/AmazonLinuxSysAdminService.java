package org.kuali.common.devops.sysadmin;

public class AmazonLinuxSysAdminService implements SysAdminService {

	@Override
	public SysAdmin getSystemAdministrator(SysAdminContext context) {
		return new AmazonLinuxSysAdmin(context);
	}

}

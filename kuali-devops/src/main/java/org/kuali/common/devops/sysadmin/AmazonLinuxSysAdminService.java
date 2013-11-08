package org.kuali.common.devops.sysadmin;

public class AmazonLinuxSysAdminService implements SysAdminService {

	@Override
	public SysAdmin getSysAdmin(SysAdminContext context) {
		return new AwsSysAdmin(context);
	}

}

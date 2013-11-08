package org.kuali.common.devops.sysadmin;

public class AwsSysAdminService implements SysAdminService {

	@Override
	public SysAdmin getSysAdmin(BootstrapContext context) {
		return new AwsSysAdmin(context);
	}

}

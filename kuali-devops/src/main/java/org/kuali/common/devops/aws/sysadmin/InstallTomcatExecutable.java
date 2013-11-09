package org.kuali.common.devops.aws.sysadmin;

import org.kuali.common.devops.aws.sysadmin.model.InstallZipPackageContext;
import org.kuali.common.util.Assert;
import org.kuali.common.util.execute.Executable;

/**
 * 
 */
public final class InstallTomcatExecutable implements Executable {

	public InstallTomcatExecutable(InstallZipPackageContext context) {
		this(context, false);
	}

	public InstallTomcatExecutable(InstallZipPackageContext context, boolean skip) {
		Assert.noNulls(context);
		this.context = context;
		this.skip = skip;
	}

	private final InstallZipPackageContext context;
	private final boolean skip;

	@Override
	public void execute() {
		if (skip) {
			return;
		}
		new InstallZipPackageExecutable(context).execute();
	}

	public boolean isSkip() {
		return skip;
	}

}

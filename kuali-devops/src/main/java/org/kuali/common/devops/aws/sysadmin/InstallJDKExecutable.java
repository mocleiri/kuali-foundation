package org.kuali.common.devops.aws.sysadmin;

import org.kuali.common.devops.aws.sysadmin.model.InstallJDKContext;
import org.kuali.common.util.Assert;
import org.kuali.common.util.execute.Executable;

/**
 * 
 */
public final class InstallJDKExecutable implements Executable {

	public InstallJDKExecutable(InstallJDKContext context) {
		this(context, false);
	}

	public InstallJDKExecutable(InstallJDKContext context, boolean skip) {
		Assert.noNulls(context);
		this.context = context;
		this.skip = skip;
	}

	private final InstallJDKContext context;
	private final boolean skip;

	@Override
	public void execute() {
		if (skip) {
			return;
		}
		InstallUtils.installZipWithBinDir(context.getService(), context.getContext(), context.getLocalRepositoryDir(), context.getArtifact(), context.getRemoteJavaDir());
	}

	public boolean isSkip() {
		return skip;
	}

	public InstallJDKContext getContext() {
		return context;
	}

}

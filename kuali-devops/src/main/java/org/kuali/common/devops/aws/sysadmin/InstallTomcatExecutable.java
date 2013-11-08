package org.kuali.common.devops.aws.sysadmin;

import org.kuali.common.devops.aws.sysadmin.model.InstallTomcatContext;
import org.kuali.common.util.Assert;
import org.kuali.common.util.execute.Executable;

/**
 * 
 */
public final class InstallTomcatExecutable implements Executable {

	public InstallTomcatExecutable(InstallTomcatContext context) {
		this(context, false);
	}

	public InstallTomcatExecutable(InstallTomcatContext context, boolean skip) {
		Assert.noNulls(context);
		this.context = context;
		this.skip = skip;
	}

	private final InstallTomcatContext context;
	private final boolean skip;

	@Override
	public void execute() {
		if (skip) {
			return;
		}
		InstallUtils.installZipWithBinDir(context.getService(), context.getContext(), context.getLocalRepositoryDir(), context.getArtifact(), context.getRemoteTomcatDir());
	}

	public boolean isSkip() {
		return skip;
	}

	public InstallTomcatContext getContext() {
		return context;
	}

}

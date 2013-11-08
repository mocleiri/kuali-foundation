package org.kuali.common.devops.aws.sysadmin;

import java.io.File;
import java.io.IOException;

import org.kuali.common.devops.aws.sysadmin.model.InstallJDKContext;
import org.kuali.common.util.Assert;
import org.kuali.common.util.channel.api.SecureChannel;
import org.kuali.common.util.channel.model.RemoteFile;
import org.kuali.common.util.channel.util.ChannelUtils;
import org.kuali.common.util.execute.Executable;
import org.kuali.common.util.maven.RepositoryUtils;

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
		install();
	}

	protected void install() {
		File localFile = RepositoryUtils.getFile(context.getLocalRepositoryDir(), context.getArtifact());
		RemoteFile remoteFile = new RemoteFile.Builder(context.getRemoteJavaDir() + "/" + localFile.getName()).build();
		Assert.exists(localFile);
		SecureChannel channel = null;
		try {
			channel = context.getService().getChannel(context.getContext());
			ChannelUtils.scp(channel, localFile, remoteFile);
		} catch (IOException e) {
			throw new IllegalStateException("Unexpected IO error", e);
		} finally {
			ChannelUtils.closeQuietly(channel);
		}
	}

	public boolean isSkip() {
		return skip;
	}

	public InstallJDKContext getContext() {
		return context;
	}

}

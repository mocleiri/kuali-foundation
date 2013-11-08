package org.kuali.common.devops.aws.sysadmin;

import java.io.File;
import java.io.IOException;

import org.kuali.common.devops.aws.sysadmin.model.InstallTomcatContext;
import org.kuali.common.util.Assert;
import org.kuali.common.util.channel.api.SecureChannel;
import org.kuali.common.util.channel.model.RemoteFile;
import org.kuali.common.util.channel.util.ChannelUtils;
import org.kuali.common.util.execute.Executable;
import org.kuali.common.util.maven.RepositoryUtils;

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
		install();
	}

	protected void install() {
		File localFile = RepositoryUtils.getFile(context.getLocalRepositoryDir(), context.getArtifact());
		RemoteFile remoteFile = new RemoteFile.Builder(context.getRemoteTomcatDir() + "/" + localFile.getName()).build();
		Assert.exists(localFile);
		SecureChannel channel = null;
		try {
			String target = context.getRemoteTomcatDir() + "/" + context.getArtifact().getArtifactId() + "-" + context.getArtifact().getVersion();
			String linkName = context.getRemoteTomcatDir() + "/" + context.getArtifact().getArtifactId();
			String command1 = "rm -rf " + linkName + " " + target;
			String command2 = "unzip " + remoteFile.getAbsolutePath() + " -d " + context.getRemoteTomcatDir();
			String command3 = "ln -s " + target + " " + linkName;
			String command4 = "chmod -R 755 " + linkName + "/bin";

			channel = context.getService().getChannel(context.getContext());
			ChannelUtils.scp(channel, localFile, remoteFile);
			ChannelUtils.exec(channel, command1);
			ChannelUtils.exec(channel, command2);
			ChannelUtils.exec(channel, command3);
			ChannelUtils.exec(channel, command4);
		} catch (IOException e) {
			throw new IllegalStateException("Unexpected IO error", e);
		} finally {
			ChannelUtils.closeQuietly(channel);
		}
	}

	public boolean isSkip() {
		return skip;
	}

	public InstallTomcatContext getContext() {
		return context;
	}

}

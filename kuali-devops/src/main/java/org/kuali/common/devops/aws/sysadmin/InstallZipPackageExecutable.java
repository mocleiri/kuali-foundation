package org.kuali.common.devops.aws.sysadmin;

import java.io.File;
import java.io.IOException;

import org.kuali.common.devops.aws.sysadmin.model.InstallZipPackageContext;
import org.kuali.common.util.Assert;
import org.kuali.common.util.channel.api.SecureChannel;
import org.kuali.common.util.channel.model.RemoteFile;
import org.kuali.common.util.channel.util.ChannelExecutable;
import org.kuali.common.util.channel.util.ChannelUtils;
import org.kuali.common.util.execute.Executable;
import org.kuali.common.util.maven.RepositoryUtils;
import org.kuali.common.util.maven.model.Artifact;

import com.google.common.base.Optional;

/**
 * 
 */
public final class InstallZipPackageExecutable implements Executable {

	public InstallZipPackageExecutable(InstallZipPackageContext context) {
		this(context, false);
	}

	public InstallZipPackageExecutable(InstallZipPackageContext context, boolean skip) {
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
		install(context);
	}

	protected void install(InstallZipPackageContext context) {
		Artifact artifact = context.getZipPackage().getArtifact();
		File localFile = RepositoryUtils.getFile(context.getLocalRepositoryDir(), artifact);
		RemoteFile remoteFile = new RemoteFile.Builder(context.getRemotePackageDir() + "/" + localFile.getName()).build();
		Assert.exists(localFile);
		SecureChannel channel = null;
		try {
			String target = context.getRemotePackageDir() + "/" + artifact.getArtifactId() + "-" + artifact.getVersion();
			String linkName = context.getInstallDir();
			String zipFile = remoteFile.getAbsolutePath();
			String unzipDir = context.getRemotePackageDir();

			String command1 = "rm -rf " + target + " " + linkName; // Remove the existing symbolic link and unzipped package directory (if they exist)
			String command2 = "unzip " + zipFile + " -d " + unzipDir; // Unzip the package into a directory containing the version number
			String command3 = "ln -s " + target + " " + linkName; // Create a symbolic link via the user friendly package name (sans version number)
			String command4 = "chmod -R 755 " + linkName + "/bin"; // Make sure everything in the "bin" directory is executable
			String command5 = "rm " + zipFile; // Remove the zip file

			channel = context.getService().openChannel(context.getContext());
			exec(channel, context.getBefore());
			ChannelUtils.scp(channel, localFile, remoteFile);
			ChannelUtils.exec(channel, command1, command2, command3, command4, command5);
			exec(channel, context.getAfter());
		} catch (IOException e) {
			throw new IllegalStateException("Unexpected IO error", e);
		} finally {
			ChannelUtils.closeQuietly(channel);
		}
	}

	protected void exec(SecureChannel channel, Optional<ChannelExecutable> optional) {
		if (optional.isPresent()) {
			ChannelExecutable exec = optional.get();
			exec.execute(channel);
		}
	}

	public boolean isSkip() {
		return skip;
	}

}

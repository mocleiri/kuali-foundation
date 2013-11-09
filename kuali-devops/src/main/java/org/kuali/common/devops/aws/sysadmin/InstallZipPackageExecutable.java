package org.kuali.common.devops.aws.sysadmin;

import java.io.File;
import java.io.IOException;

import org.kuali.common.devops.aws.sysadmin.model.InstallZipPackageContext;
import org.kuali.common.util.Assert;
import org.kuali.common.util.channel.api.SecureChannel;
import org.kuali.common.util.channel.model.RemoteFile;
import org.kuali.common.util.channel.util.ChannelUtils;
import org.kuali.common.util.execute.Executable;
import org.kuali.common.util.maven.RepositoryUtils;
import org.kuali.common.util.maven.model.Artifact;

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
		String packageName = context.getZipPackage().getPackageName();
		File localFile = RepositoryUtils.getFile(context.getLocalRepositoryDir(), artifact);
		RemoteFile remoteFile = new RemoteFile.Builder(context.getRemotePackageDir() + "/" + localFile.getName()).build();
		Assert.exists(localFile);
		SecureChannel channel = null;
		try {
			String target = context.getRemotePackageDir() + "/" + artifact.getArtifactId() + "-" + artifact.getVersion();
			String linkName = context.getRemotePackageDir() + "/" + packageName;
			String zipFile = remoteFile.getAbsolutePath();
			String unzipDir = context.getRemotePackageDir();

			String command1 = "rm -rf " + linkName + " " + target; // Remove the existing symbolic link and unzipped package directory (if they exist)
			String command2 = "unzip " + zipFile + " -d " + unzipDir; // Unzip the package into a directory containing the version number
			String command3 = "ln -s " + target + " " + linkName; // Create a symbolic link via the user friendly package name (sans version number)
			String command4 = "chmod -R 755 " + linkName + "/bin"; // Make sure everything in the "bin" directory is executable
			String command5 = "rm " + zipFile; // Remove the zip file

			channel = context.getService().getChannel(context.getContext());
			ChannelUtils.scp(channel, localFile, remoteFile);
			ChannelUtils.exec(channel, command1, command2, command3, command4, command5);
		} catch (IOException e) {
			throw new IllegalStateException("Unexpected IO error", e);
		} finally {
			ChannelUtils.closeQuietly(channel);
		}
	}

	public boolean isSkip() {
		return skip;
	}

}

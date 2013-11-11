package org.kuali.common.devops.aws.sysadmin;

import java.io.File;

import org.kuali.common.devops.aws.sysadmin.model.InstallZipContext;
import org.kuali.common.util.Assert;
import org.kuali.common.util.channel.api.SecureChannel;
import org.kuali.common.util.channel.model.RemoteFile;
import org.kuali.common.util.channel.util.ChannelExecutable;
import org.kuali.common.util.channel.util.ChannelUtils;
import org.kuali.common.util.maven.RepositoryUtils;
import org.kuali.common.util.maven.model.Artifact;

import com.google.common.base.Optional;

/**
 * 
 */
public final class InstallZip implements ChannelExecutable {

	private final InstallZipContext context;
	private final boolean skip;
	private final boolean runAlways;
	private final Optional<ChannelExecutable> before;
	private final Optional<ChannelExecutable> after;

	public static class Builder {

		// Required
		private final InstallZipContext context;

		// Optional
		private boolean skip = false;
		private boolean runAlways = false;
		private Optional<ChannelExecutable> before = Optional.absent();
		private Optional<ChannelExecutable> after = Optional.absent();

		public Builder(InstallZipContext context) {
			this.context = context;
		}

		public Builder runAlways(boolean runAlways) {
			this.runAlways = runAlways;
			return this;
		}

		public Builder skip(boolean skip) {
			this.skip = skip;
			return this;
		}

		public Builder before(ChannelExecutable before) {
			this.before = Optional.of(before);
			return this;
		}

		public Builder after(ChannelExecutable after) {
			this.after = Optional.of(after);
			return this;
		}

		public InstallZip build() {
			Assert.noNulls(context, before, after);
			return new InstallZip(this);
		}
	}

	private InstallZip(Builder builder) {
		this.context = builder.context;
		this.skip = builder.skip;
		this.before = builder.before;
		this.after = builder.after;
		this.runAlways = builder.runAlways;
	}

	@Override
	public void execute(SecureChannel channel) {
		if (!skip) {
			install(context, channel);
		}
	}

	protected void install(InstallZipContext context, SecureChannel channel) {
		boolean installed = isZipInstalled(channel);
		if (!installed || runAlways) {
			install(channel);
		}
	}

	protected boolean isZipInstalled(SecureChannel channel) {
		String targetDir = getTargetDir();
		String installDir = context.getInstallDir();
		boolean check1 = channel.exists(targetDir) && channel.isDirectory(targetDir);
		boolean check2 = channel.exists(installDir) && channel.isDirectory(installDir);
		return check1 && check2;
	}

	protected void install(SecureChannel channel) {
		Artifact artifact = context.getZip().getArtifact();
		File localFile = RepositoryUtils.getFile(context.getLocalRepositoryDir(), artifact);
		RemoteFile remoteFile = new RemoteFile.Builder(context.getRemotePackageDir() + "/" + localFile.getName()).build();
		Assert.exists(localFile);
		String target = getTargetDir();
		String linkName = context.getInstallDir();
		String zipFile = remoteFile.getAbsolutePath();
		String unzipDir = context.getRemotePackageDir();

		String command1 = "rm -rf " + target + " " + linkName; // Remove the existing symbolic link and unzipped package directory (if they exist)
		String command2 = "unzip " + zipFile + " -d " + unzipDir; // Unzip the package into a directory containing the version number
		String command3 = "ln -s " + target + " " + linkName; // Create a symbolic link via the user friendly package name (sans version number)
		String command4 = "rm " + zipFile; // Remove the zip file

		exec(channel, before); // Do any pre-processing as needed
		channel.scp(localFile, remoteFile); // Copy the zip file
		ChannelUtils.exec(channel, command1, command2, command3, command4); // Install the package from the zip
		exec(channel, after); // Do any post-processing as needed
	}

	protected String getTargetDir() {
		Artifact artifact = context.getZip().getArtifact();
		return context.getRemotePackageDir() + "/" + artifact.getArtifactId() + "-" + artifact.getVersion();
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

	public InstallZipContext getContext() {
		return context;
	}

	public Optional<ChannelExecutable> getBefore() {
		return before;
	}

	public Optional<ChannelExecutable> getAfter() {
		return after;
	}

	public boolean isRunAlways() {
		return runAlways;
	}

}

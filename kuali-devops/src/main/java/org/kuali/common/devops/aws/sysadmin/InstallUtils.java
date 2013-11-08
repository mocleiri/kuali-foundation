package org.kuali.common.devops.aws.sysadmin;

import java.io.File;
import java.io.IOException;

import org.kuali.common.util.Assert;
import org.kuali.common.util.channel.api.SecureChannel;
import org.kuali.common.util.channel.api.SecureChannelService;
import org.kuali.common.util.channel.model.ChannelContext;
import org.kuali.common.util.channel.model.RemoteFile;
import org.kuali.common.util.channel.util.ChannelUtils;
import org.kuali.common.util.maven.RepositoryUtils;
import org.kuali.common.util.maven.model.Artifact;

public class InstallUtils {

	public static void installZipWithBinDir(SecureChannelService service, ChannelContext context, File localRepoDir, Artifact artifact, String remoteDir) {
		File localFile = RepositoryUtils.getFile(localRepoDir, artifact);
		RemoteFile remoteFile = new RemoteFile.Builder(remoteDir + "/" + localFile.getName()).build();
		Assert.exists(localFile);
		SecureChannel channel = null;
		try {
			String target = remoteDir + "/" + artifact.getArtifactId() + "-" + artifact.getVersion();
			String linkName = remoteDir + "/" + artifact.getArtifactId();
			String command1 = "rm -rf " + linkName + " " + target;
			String command2 = "unzip " + remoteFile.getAbsolutePath() + " -d " + remoteDir;
			String command3 = "ln -s " + target + " " + linkName;
			String command4 = "chmod -R 755 " + linkName + "/bin";

			channel = service.getChannel(context);
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
}

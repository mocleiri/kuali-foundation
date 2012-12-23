package org.kuali.common.deploy;

import java.io.File;

import org.kuali.common.deploy.context.DeployContext;
import org.kuali.common.util.UnixUtils;

public class DeployUtil {

	public static final void scpTomcatFile(DeployContext context, File localFile, String remoteFile) {
		String user = context.getSecurity().getUser();
		String hostname = context.getSecurity().getHostname();
		String owner = context.getTomcat().getUser();
		String group = context.getTomcat().getGroup();

		String destination = UnixUtils.getLocation(user, hostname, remoteFile);
		int scp = UnixUtils.scp(localFile, destination);
		UnixUtils.validate(scp, "Error completing secure copy");
		int chown = UnixUtils.sshchown(user, hostname, owner, group, remoteFile);
		UnixUtils.validate(chown, "Error changing file ownership");
	}

}

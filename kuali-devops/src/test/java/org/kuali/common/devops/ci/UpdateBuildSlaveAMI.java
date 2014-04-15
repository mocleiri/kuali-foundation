package org.kuali.common.devops.ci;

import static org.kuali.common.devops.ci.SpinUpJenkinsMaster.exec;
import static org.kuali.common.devops.ci.SpinUpJenkinsMaster.getResource;
import static org.kuali.common.devops.ci.SpinUpJenkinsMaster.openSecureChannel;
import static org.kuali.common.devops.ci.SpinUpJenkinsMaster.publishProject;
import static org.kuali.common.devops.ci.model.Constants.DISTRO;
import static org.kuali.common.devops.ci.model.Constants.DISTRO_VERSION;
import static org.kuali.common.devops.ci.model.Constants.ROOT;
import static org.kuali.common.util.encrypt.Encryption.getDefaultEncryptor;
import static org.kuali.common.util.log.Loggers.newLogger;

import java.io.IOException;

import org.junit.Test;
import org.kuali.common.core.system.VirtualSystem;
import org.kuali.common.util.channel.api.SecureChannel;
import org.kuali.common.util.encrypt.Encryptor;
import org.kuali.common.util.project.model.ProjectIdentifier;
import org.slf4j.Logger;

public class UpdateBuildSlaveAMI {

	private static final Logger logger = newLogger();

	private final Encryptor encryptor = getDefaultEncryptor();
	private final String kisUsernameEncrypted = "U2FsdGVkX18yas/kI9ymLV41TRC9tcoE8P2YaoQmtOc=";
	private final String kisPasswordEncrypted = "U2FsdGVkX18M5faj1sGRINZ0p5dNNW3FFEPxM1lx3Gw=";

	@Test
	public void test() throws Exception {
		VirtualSystem vs = VirtualSystem.create();
	}

	protected void updateMasterAMI(String jenkinsMaster, ProjectIdentifier pid, String privateKey, boolean quiet, String ami) throws IOException {
		info("updating %s with ami [%s]", jenkinsMaster, ami);
		String kisUsername = encryptor.decrypt(kisUsernameEncrypted);
		String kisPassword = encryptor.decrypt(kisPasswordEncrypted);
		SecureChannel channel = openSecureChannel(ROOT, jenkinsMaster, privateKey, quiet);
		String basedir = publishProject(channel, pid, ROOT, jenkinsMaster, quiet);
		String rubyScript = getResource(basedir, pid, DISTRO, DISTRO_VERSION, "jenkins/update_jenkins_ami_headless.rb");
		exec(channel, "ruby", rubyScript, kisUsername, kisPassword, ami, jenkinsMaster);
	}

	private static void info(String msg, Object... args) {
		if (args == null) {
			logger.info(msg);
		} else {
			logger.info(msg, args);
		}
	}

}

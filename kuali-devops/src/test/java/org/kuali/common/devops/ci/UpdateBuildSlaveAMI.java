package org.kuali.common.devops.ci;

import static com.google.common.base.Preconditions.checkState;
import static com.google.common.base.Stopwatch.createStarted;
import static java.lang.String.format;
import static java.util.Collections.reverse;
import static java.util.Collections.sort;
import static org.apache.commons.lang3.StringUtils.equalsIgnoreCase;
import static org.kuali.common.devops.ci.CreateBuildSlaveAMI.CI_SLAVE_STARTS_WITH_TOKEN;
import static org.kuali.common.devops.ci.CreateBuildSlaveAMI.CONTEXTS;
import static org.kuali.common.devops.ci.CreateBuildSlaveAMI.DEVOPS_KEYPAIR;
import static org.kuali.common.devops.ci.CreateBuildSlaveAMI.getEC2Service;
import static org.kuali.common.devops.ci.CreateBuildSlaveAMI.getFilteredImages;
import static org.kuali.common.devops.ci.CreateBuildSlaveAMI.getJenkinsMaster;
import static org.kuali.common.devops.ci.SpinUpJenkinsMaster.exec;
import static org.kuali.common.devops.ci.SpinUpJenkinsMaster.getJenkinsContext;
import static org.kuali.common.devops.ci.SpinUpJenkinsMaster.getResource;
import static org.kuali.common.devops.ci.SpinUpJenkinsMaster.openSecureChannel;
import static org.kuali.common.devops.ci.SpinUpJenkinsMaster.publishProject;
import static org.kuali.common.devops.ci.model.Constants.AMAZON_ACCOUNT;
import static org.kuali.common.devops.ci.model.Constants.DISTRO;
import static org.kuali.common.devops.ci.model.Constants.DISTRO_VERSION;
import static org.kuali.common.devops.ci.model.Constants.ROOT;
import static org.kuali.common.devops.project.KualiDevOpsProjectConstants.KUALI_DEVOPS_PROJECT_IDENTIFIER;
import static org.kuali.common.util.encrypt.Encryption.getDefaultEncryptor;
import static org.kuali.common.util.log.Loggers.newLogger;

import java.io.IOException;
import java.util.List;

import org.junit.Test;
import org.kuali.common.aws.ec2.api.EC2Service;
import org.kuali.common.core.system.VirtualSystem;
import org.kuali.common.devops.ci.CreateBuildSlaveAMI.ImageTagsComparator;
import org.kuali.common.devops.ci.model.JenkinsContext;
import org.kuali.common.util.FormatUtils;
import org.kuali.common.util.channel.api.SecureChannel;
import org.kuali.common.util.encrypt.Encryptor;
import org.kuali.common.util.project.model.ProjectIdentifier;
import org.slf4j.Logger;

import com.amazonaws.services.ec2.model.Image;
import com.amazonaws.services.ec2.model.Tag;
import com.google.common.base.Stopwatch;

public class UpdateBuildSlaveAMI {

	private static final Logger logger = newLogger();

	private final Stopwatch stopwatch = createStarted();
	private final Encryptor encryptor = getDefaultEncryptor();
	private final String kisUsernameEncrypted = "U2FsdGVkX18yas/kI9ymLV41TRC9tcoE8P2YaoQmtOc=";
	private final String kisPasswordEncrypted = "U2FsdGVkX18M5faj1sGRINZ0p5dNNW3FFEPxM1lx3Gw=";
	private final ProjectIdentifier PID = KUALI_DEVOPS_PROJECT_IDENTIFIER;

	@Test
	public void test() throws Exception {
		VirtualSystem vs = VirtualSystem.create();
		// Default to quiet mode unless they've supplied -Dec2.quiet=false
		boolean quiet = equalsIgnoreCase(vs.getProperties().getProperty("ec2.quiet"), "false") ? false : true;
		JenkinsContext jenkinsContext = getJenkinsContext(vs, CONTEXTS);
		EC2Service service = getEC2Service(AMAZON_ACCOUNT, jenkinsContext.getRegion());
		String ami = getMostRecentAMI(service, jenkinsContext);
		String privateKey = DEVOPS_KEYPAIR.getPrivateKey();
		String jenkinsMaster = getJenkinsMaster(jenkinsContext);
		updateMasterAMI(jenkinsMaster, privateKey, quiet, ami);
		info("updated %s with ami %s - %s", jenkinsMaster, ami, FormatUtils.getTime(stopwatch));
	}

	protected void updateMasterAMI(String jenkinsMaster, String privateKey, boolean quiet, String ami) throws IOException {
		info("updating %s with ami [%s]", jenkinsMaster, ami);
		String kisUsername = encryptor.decrypt(kisUsernameEncrypted);
		String kisPassword = encryptor.decrypt(kisPasswordEncrypted);
		SecureChannel channel = openSecureChannel(ROOT, jenkinsMaster, privateKey, quiet);
		String basedir = publishProject(channel, PID, ROOT, jenkinsMaster, quiet);
		String rubyScript = getResource(basedir, PID, DISTRO, DISTRO_VERSION, "jenkins/update_jenkins_ami_headless.rb");
		exec(channel, "ruby", rubyScript, kisUsername, kisPassword, ami, jenkinsMaster);
	}

	protected static String getMostRecentAMI(EC2Service service, JenkinsContext context) {
		return getMostRecentAMI(service, context.getStack().getTag(), context.getName().getTag());
	}

	protected static String getMostRecentAMI(EC2Service service, Tag stack, Tag name) {

		List<Image> images = service.getMyImages();

		// Extract just the ci.slave AMI's for this stack
		List<Image> filtered = getFilteredImages(images, stack, name.getKey(), CI_SLAVE_STARTS_WITH_TOKEN);

		// This sort them by date
		sort(filtered, new ImageTagsComparator());

		// Most recent images are at the bottom, this brings them to the top
		reverse(filtered);

		// Make sure we have at least one image
		checkState(filtered.size() > 0, "need at least one image");

		// Return the most recent one
		return filtered.get(0).getImageId();
	}

	private static void info(String msg, Object... args) {
		if (args == null) {
			logger.info(msg);
		} else {
			logger.info(format(msg, args));
		}
	}

}

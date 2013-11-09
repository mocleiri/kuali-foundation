package org.kuali.common.devops.aws.sysadmin;

import java.util.ArrayList;
import java.util.List;

import org.kuali.common.devops.aws.sysadmin.model.Deployable;
import org.kuali.common.devops.project.DevOpsProjectConstants;
import org.kuali.common.util.channel.model.RemoteFile;
import org.kuali.common.util.project.ProjectUtils;

import com.google.common.collect.ImmutableList;

public final class TomcatUtils {

	private static final String CLASSPATH_PREFIX = ProjectUtils.getClasspathPrefix(DevOpsProjectConstants.PROJECT_ID) + "/tomcat";

	private static final List<String> CONF = ImmutableList.of("server.xml", "web.xml");
	private static final List<String> BIN = ImmutableList.of("forced-shutdown.sh", "cleanup.sh");
	private static final List<String> JSPS = ImmutableList.of("env.jsp", "tail.jsp");

	public static List<Deployable> getDeployables(String installDir, String majorVersion) {
		List<Deployable> list = new ArrayList<Deployable>();
		list.addAll(getJsp(installDir));
		list.addAll(getBin(installDir));
		list.addAll(getConf(installDir, majorVersion));
		return ImmutableList.copyOf(list);
	}

	protected static List<Deployable> getJsp(String installDir) {
		List<Deployable> list = new ArrayList<Deployable>();
		for (String resource : JSPS) {
			String src = CLASSPATH_PREFIX + "/jsps/" + resource;
			String absolutePath = installDir + "/logs/" + resource;
			RemoteFile dst = new RemoteFile.Builder(absolutePath).build();
			list.add(new Deployable(src, dst));
		}
		return list;
	}

	protected static List<Deployable> getBin(String installDir) {
		List<Deployable> list = new ArrayList<Deployable>();
		for (String resource : BIN) {
			String src = CLASSPATH_PREFIX + "/bin/" + resource;
			String absolutePath = installDir + "/bin/" + resource;
			RemoteFile dst = new RemoteFile.Builder(absolutePath).build();
			list.add(new Deployable(src, dst));
		}
		return list;
	}

	protected static List<Deployable> getConf(String installDir, String majorVersion) {
		List<Deployable> list = new ArrayList<Deployable>();
		for (String resource : CONF) {
			String src = CLASSPATH_PREFIX + "/" + majorVersion + "/conf/" + resource;
			String absolutePath = installDir + "/conf/" + resource;
			RemoteFile dst = new RemoteFile.Builder(absolutePath).build();
			list.add(new Deployable(src, dst));
		}
		return list;
	}

}

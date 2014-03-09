package org.kuali.common.devops.aws.sysadmin;

import java.util.ArrayList;
import java.util.List;

import org.kuali.common.devops.model.FileResource;
import org.kuali.common.devops.project.KualiDevOpsProjectConstants;
import org.kuali.common.util.channel.model.RemoteFile;
import org.kuali.common.util.project.ProjectUtils;

import com.google.common.collect.ImmutableList;

public final class TomcatConfig {

	private static final String CLASSPATH_PREFIX = ProjectUtils.getClasspathPrefix(KualiDevOpsProjectConstants.KUALI_DEVOPS_PROJECT_IDENTIFIER) + "/tomcat";

	private static final List<String> CONF = ImmutableList.of("server.xml", "web.xml");
	private static final List<String> BIN = ImmutableList.of("cleanup.sh");
	private static final List<String> JSPS = ImmutableList.of("env.jsp", "tail.jsp");

	public static List<FileResource> getDeployables(String installDir, String majorVersion) {
		List<FileResource> list = new ArrayList<FileResource>();
		list.addAll(getJsp(installDir));
		list.addAll(getBin(installDir));
		list.addAll(getConf(installDir, majorVersion));
		return ImmutableList.copyOf(list);
	}

	protected static List<FileResource> getJsp(String installDir) {
		List<FileResource> list = new ArrayList<FileResource>();
		for (String resource : JSPS) {
			String src = CLASSPATH_PREFIX + "/jsps/" + resource;
			String absolutePath = installDir + "/logs/" + resource;
			RemoteFile dst = new RemoteFile.Builder(absolutePath).build();
			list.add(new FileResource.Builder("jsp", dst, src).build());
		}
		return list;
	}

	protected static List<FileResource> getBin(String installDir) {
		List<FileResource> list = new ArrayList<FileResource>();
		for (String resource : BIN) {
			String src = CLASSPATH_PREFIX + "/bin/" + resource;
			String absolutePath = installDir + "/bin/" + resource;
			RemoteFile dst = new RemoteFile.Builder(absolutePath).build();
			list.add(new FileResource.Builder("bin", dst, src).build());
		}
		return list;
	}

	protected static List<FileResource> getConf(String installDir, String majorVersion) {
		List<FileResource> list = new ArrayList<FileResource>();
		for (String resource : CONF) {
			String src = CLASSPATH_PREFIX + "/" + majorVersion + "/conf/" + resource;
			String absolutePath = installDir + "/conf/" + resource;
			RemoteFile dst = new RemoteFile.Builder(absolutePath).build();
			list.add(new FileResource.Builder("conf", dst, src).build());
		}
		return list;
	}

}

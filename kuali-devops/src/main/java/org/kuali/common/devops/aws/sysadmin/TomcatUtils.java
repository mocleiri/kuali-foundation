package org.kuali.common.devops.aws.sysadmin;

import java.util.ArrayList;
import java.util.List;

import org.kuali.common.devops.aws.sysadmin.model.CustomTomcatConfig;
import org.kuali.common.devops.aws.sysadmin.model.Deployable;
import org.kuali.common.devops.project.DevOpsProjectConstants;
import org.kuali.common.util.channel.model.RemoteFile;
import org.kuali.common.util.project.ProjectUtils;

import com.google.common.collect.ImmutableList;

public final class TomcatUtils {

	private static final String PREFIX = ProjectUtils.getClasspathPrefix(DevOpsProjectConstants.PROJECT_ID) + "/tomcat";
	private static final CustomTomcatConfig CONFIG = new CustomTomcatConfig();

	public static List<Deployable> getDeployables(String installDir, String majorVersion) {
		List<Deployable> list = new ArrayList<Deployable>();
		list.addAll(getJsp(installDir));
		list.addAll(getBin(installDir));
		list.addAll(getConf(installDir, majorVersion));
		return ImmutableList.copyOf(list);
	}

	protected static List<Deployable> getJsp(String installDir) {
		List<String> resources = CONFIG.getJsps();
		List<Deployable> list = new ArrayList<Deployable>();
		for (String resource : resources) {
			String src = PREFIX + "/jsps/" + resource;
			String absolutePath = installDir + "/logs/" + resource;
			RemoteFile dst = new RemoteFile.Builder(absolutePath).build();
			list.add(new Deployable(src, dst));
		}
		return list;
	}

	protected static List<Deployable> getBin(String installDir) {
		List<String> resources = CONFIG.getBin();
		List<Deployable> list = new ArrayList<Deployable>();
		for (String resource : resources) {
			String src = PREFIX + "/bin/" + resource;
			String absolutePath = installDir + "/bin/" + resource;
			RemoteFile dst = new RemoteFile.Builder(absolutePath).build();
			list.add(new Deployable(src, dst));
		}
		return list;
	}

	protected static List<Deployable> getConf(String installDir, String majorVersion) {
		List<String> resources = CONFIG.getConf();
		List<Deployable> list = new ArrayList<Deployable>();
		for (String resource : resources) {
			String src = PREFIX + "/" + majorVersion + "/conf/" + resource;
			String absolutePath = installDir + "/conf/" + resource;
			RemoteFile dst = new RemoteFile.Builder(absolutePath).build();
			list.add(new Deployable(src, dst));
		}
		return list;
	}

}

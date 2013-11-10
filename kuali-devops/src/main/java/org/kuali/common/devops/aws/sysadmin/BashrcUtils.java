package org.kuali.common.devops.aws.sysadmin;

import java.util.ArrayList;
import java.util.List;

import org.kuali.common.aws.ec2.model.Distro;
import org.kuali.common.devops.aws.sysadmin.model.BashrcContext;
import org.kuali.common.devops.aws.sysadmin.model.Heap;
import org.kuali.common.devops.project.DevOpsProjectConstants;
import org.kuali.common.util.Assert;
import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.FormatUtils;
import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.Str;
import org.kuali.common.util.project.ProjectUtils;

import com.google.common.collect.ImmutableList;

public final class BashrcUtils {

	private static final String CLASSPATH_PREFIX = ProjectUtils.getClasspathPrefix(DevOpsProjectConstants.PROJECT_ID);

	private static final String PS1 = "export PS1=\"\\[\\e[36;1m\\]\\u@\\h\\w\\[\\e[32;1m\\]$ \\[\\e[0m\\]\"";
	private static final String CLICOLOR = "export CLICOLOR=1";

	private static final String JAVA_HOME = "JAVA_HOME";
	private static final String CATALINA_HOME = "CATALINA_HOME";
	private static final String CATALINA_BASE = "CATALINA_BASE";
	private static final String CATALINA_PID = "CATALINA_PID";
	private static final String CATALINA_OPTS = "CATALINA_OPTS";
	private static final String PATH = "PATH";
	private static final List<String> EXPORTS = ImmutableList.of(JAVA_HOME, CATALINA_HOME, CATALINA_BASE, CATALINA_PID, CATALINA_OPTS, PATH);

	public static String getContent(Distro distro, String absolutePath, BashrcContext context) {
		Assert.noNulls(distro, context);
		Assert.noBlanks(absolutePath);
		String location = CLASSPATH_PREFIX + "/" + distro.getName() + absolutePath + ".original";
		Assert.exists(location);
		List<String> lines = new ArrayList<String>();
		lines.addAll(LocationUtils.readLines(location));
		lines.add("");
		lines.add(PS1);
		lines.add(CLICOLOR);
		lines.add("");
		lines.add(JAVA_HOME + "=" + context.getJavaHome());
		lines.add("");
		lines.add(CATALINA_HOME + "=" + context.getCatalinaHome());
		lines.add(CATALINA_BASE + "=$" + CATALINA_HOME);
		lines.add(CATALINA_PID + "=$" + CATALINA_BASE + "/logs/catalina.pid");
		lines.add("");
		for (String catalinaOpt : context.getCatalinaOpts()) {
			lines.add(CATALINA_OPTS + "=" + Str.quote("$" + CATALINA_OPTS + " " + catalinaOpt));
		}
		lines.add("");
		lines.addAll(context.getAdditionalLines());
		lines.add("");
		lines.add(PATH + "=$" + JAVA_HOME + "/bin:$" + CATALINA_HOME + "/bin:$" + PATH);
		lines.add("");
		lines.add("export " + CollectionUtils.getSpaceSeparatedString(EXPORTS));
		lines.add("");
		StringBuilder sb = new StringBuilder();
		for (String line : lines) {
			sb.append(line + "\n");
		}
		return sb.toString();
	}

	public static List<String> getDefaultCatalinaOpts(Heap heap) {
		List<String> opts = new ArrayList<String>();
		opts.add("-Xms" + FormatUtils.getSize(heap.getMinSizeInBytes()));
		opts.add("-Xmx" + FormatUtils.getSize(heap.getMaxSizeInBytes()));
		opts.add("-XX:MaxPermSize=" + FormatUtils.getSize(heap.getMaxPermSizeInBytes()));
		opts.add("-verbose:gc");
		opts.add("-XX:+PrintGCDetails");
		opts.add("-XX:+PrintGCDateStamps");
		opts.add("-XX:+PrintHeapAtGC");
		opts.add("-XX:+PrintTenuringDistribution");
		opts.add("-Xloggc:$" + CATALINA_BASE + "/logs/heap.log");
		if (heap.isDumpOnOutOfMemoryError()) {
			opts.add("-XX:HeapDumpPath=$" + CATALINA_BASE + "/logs");
			opts.add("-XX:+HeapDumpOnOutOfMemoryError");
			opts.add("-agentlib:hprof=file=$" + CATALINA_BASE + "/logs/snapshot.hprof,format=b");
		}
		return ImmutableList.copyOf(opts);
	}
}

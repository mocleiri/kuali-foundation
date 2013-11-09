package org.kuali.common.devops.aws.sysadmin;

import java.util.ArrayList;
import java.util.List;

import org.kuali.common.devops.aws.sysadmin.model.Bashrc;
import org.kuali.common.devops.project.DevOpsProjectConstants;
import org.kuali.common.util.Assert;
import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.project.ProjectUtils;

public final class BashrcUtils {

	private static final String CLASSPATH_PREFIX = ProjectUtils.getClasspathPrefix(DevOpsProjectConstants.PROJECT_ID);

	public static String getContent(Bashrc bashrc, List<String> additionalLines) {
		Assert.noNulls(bashrc, additionalLines);
		String location = CLASSPATH_PREFIX + bashrc.getLocation() + ".original";
		Assert.exists(location);
		List<String> lines = new ArrayList<String>();
		lines.addAll(LocationUtils.readLines(location));
		lines.addAll(additionalLines);
		StringBuilder sb = new StringBuilder();
		for (String line : lines) {
			sb.append(line + "\n");
		}
		return sb.toString();
	}
}

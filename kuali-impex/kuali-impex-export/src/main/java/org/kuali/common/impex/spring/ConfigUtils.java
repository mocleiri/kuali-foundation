package org.kuali.common.impex.spring;

import java.util.ArrayList;
import java.util.List;

import org.kuali.common.util.Project;
import org.kuali.common.util.ProjectUtils;
import org.kuali.common.util.spring.SpringUtils;
import org.springframework.core.env.Environment;

public class ConfigUtils {

	public static List<Project> getProjects(Environment env, String key) {
		List<String> gavs = SpringUtils.getNoneSensitiveListFromCSV(env, key);
		List<Project> projects = new ArrayList<Project>();
		for (String gav : gavs) {
			Project project = ProjectUtils.loadProject(gav);
			projects.add(project);
		}
		return projects;
	}

}

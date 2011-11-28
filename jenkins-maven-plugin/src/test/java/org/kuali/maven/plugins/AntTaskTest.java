package org.kuali.maven.plugins;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.Task;
import org.junit.Test;

public class AntTaskTest {

	@Test
	public void testForking() {
		Project project = new Project();
		Task task = new HelloWorldTask();
		task.setProject(project);
		task.execute();
	}
}

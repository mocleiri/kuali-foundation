package org.kuali.maven.plugins;

import java.io.File;

import org.apache.tools.ant.Project;
import org.junit.Test;

public class AntTaskTest {

	@Test
	public void testForking() {
		Project project = new Project();
		HelloWorldTask task = new HelloWorldTask();
		task.setProject(project);
		task.setFork(true);
		task.setOutput(new File("C:\\temp\\out.txt"));
		project.init();
		int result = task.executeJava();
		System.out.println(result);
	}
}

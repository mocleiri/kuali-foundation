package org.apache.torque.mojo;

import org.apache.tools.ant.taskdefs.Echo;
import org.codehaus.plexus.PlexusTestCase;

/**
 * Tests AntTaskMojo
 */
public class AntTaskMojoTest extends PlexusTestCase {

	/**
	 * 
	 */
	public void testExecute() throws Exception {
		try {
			System.out.println("execute");

			Echo echoTask = new Echo();
			echoTask.setTaskName("empty task");
			AntTaskMojo instance = new AntTaskMojo();
			instance.setAntTask(echoTask);
			instance.configureTask();
			echoTask.addText("hello world");
			instance.execute();
		} catch (Throwable t) {
			t.printStackTrace();
		}

	}
}

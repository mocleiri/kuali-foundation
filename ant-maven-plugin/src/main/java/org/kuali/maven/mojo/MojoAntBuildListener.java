package org.kuali.maven.mojo;

import org.apache.maven.plugin.logging.Log;
import org.apache.tools.ant.BuildEvent;
import org.apache.tools.ant.BuildListener;
import org.apache.tools.ant.Project;

/**
 * This listener issues Ant logging messages to the Maven logging system
 */
public class MojoAntBuildListener implements BuildListener {
	Log mavenLog;

	public MojoAntBuildListener() {
		this(null);
	}

	public MojoAntBuildListener(Log mavenLog) {
		super();
		this.mavenLog = mavenLog;
	}

	// Log the Ant message using Maven's logger
	@Override
	public void messageLogged(BuildEvent event) {
		String message = event.getMessage();
		int priority = event.getPriority();
		switch (priority) {
		case (Project.MSG_VERBOSE):
		case (Project.MSG_DEBUG):
			mavenLog.debug(message);
			return;
		case (Project.MSG_ERR):
			mavenLog.error(message);
			return;
		case (Project.MSG_WARN):
			mavenLog.warn(message);
			return;
		case (Project.MSG_INFO):
			mavenLog.info(message);
			return;
		default:
			mavenLog.info(message);
			return;
		}
	}

	// Ignore all other events
	@Override
	public void taskStarted(BuildEvent event) {
	}

	@Override
	public void taskFinished(BuildEvent event) {
	}

	@Override
	public void targetStarted(BuildEvent event) {
		messageLogged(event);
	}

	@Override
	public void targetFinished(BuildEvent event) {
		messageLogged(event);
	}

	@Override
	public void buildStarted(BuildEvent event) {
	}

	@Override
	public void buildFinished(BuildEvent event) {
	}

	public Log getMavenLog() {
		return mavenLog;
	}

	public void setMavenLog(Log mavenLog) {
		this.mavenLog = mavenLog;
	}

}

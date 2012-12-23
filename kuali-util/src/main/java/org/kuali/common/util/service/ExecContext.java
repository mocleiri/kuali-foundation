package org.kuali.common.util.service;

import java.io.File;
import java.util.List;

import org.codehaus.plexus.util.cli.StreamConsumer;

public interface ExecContext {

	String getExecutable();

	List<String> getArgs();

	StreamConsumer getStdout();

	StreamConsumer getStderr();

	int getTimeoutInSeconds();

	File getWorkingDirectory();

}

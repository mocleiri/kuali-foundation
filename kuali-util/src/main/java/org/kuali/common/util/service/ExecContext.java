package org.kuali.common.util.service;

import java.io.File;
import java.io.InputStream;
import java.util.List;

import org.codehaus.plexus.util.cli.StreamConsumer;

public interface ExecContext {

	String getExecutable();

	List<String> getArgs();

	InputStream getInput();

	int getTimeoutInSeconds();

	File getWorkingDirectory();

	StreamConsumer getStandardOutConsumer();

	StreamConsumer getStandardErrConsumer();

}

package org.kuali.common.util.service;

import java.io.File;
import java.util.List;

public class MavenContext {

	String executable = "mvn";
	File workingDir;
	File pom;
	boolean debug;
	boolean errors;
	boolean batchMode;
	boolean quiet;
	boolean offline;
	boolean mavenOpts;
	List<String> options;
	List<String> goals;
	List<String> phases;

}

package org.kuali.common.impex.staging;

import java.io.File;
import java.util.List;

import org.kuali.common.util.Project;
import org.kuali.common.util.execute.Executable;

public class CreateStagingDirExecutable implements Executable {

	Project project;
	File stagingDir;
	List<String> includes;
	List<String> excludes;
	File dumpDir;

	@Override
	public void execute() {
		// TODO Auto-generated method stub

	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public File getStagingDir() {
		return stagingDir;
	}

	public void setStagingDir(File stagingDir) {
		this.stagingDir = stagingDir;
	}

	public File getDumpDir() {
		return dumpDir;
	}

	public void setDumpDir(File dumpDir) {
		this.dumpDir = dumpDir;
	}

	public List<String> getIncludes() {
		return includes;
	}

	public void setIncludes(List<String> includes) {
		this.includes = includes;
	}

	public List<String> getExcludes() {
		return excludes;
	}

	public void setExcludes(List<String> excludes) {
		this.excludes = excludes;
	}

}

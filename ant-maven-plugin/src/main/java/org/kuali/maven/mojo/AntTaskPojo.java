package org.kuali.maven.mojo;

/**
 * Pojo representing the Ant "ant" task
 * 
 * http://ant.apache.org/manual/Tasks/ant.html
 */
public class AntTaskPojo {
	String antfile = "build.xml";
	String dir;
	String target;
	String output;
	boolean inheritAll = true;
	boolean inheritRefs;
	boolean useNativeBasedir;

	public String getAntfile() {
		return antfile;
	}

	public void setAntfile(String antfile) {
		this.antfile = antfile;
	}

	public String getDir() {
		return dir;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getOutput() {
		return output;
	}

	public void setOutput(String output) {
		this.output = output;
	}

	public boolean isInheritAll() {
		return inheritAll;
	}

	public void setInheritAll(boolean inheritAll) {
		this.inheritAll = inheritAll;
	}

	public boolean isInheritRefs() {
		return inheritRefs;
	}

	public void setInheritRefs(boolean inheritRefs) {
		this.inheritRefs = inheritRefs;
	}

	public boolean isUseNativeBasedir() {
		return useNativeBasedir;
	}

	public void setUseNativeBasedir(boolean useNativeBasedir) {
		this.useNativeBasedir = useNativeBasedir;
	}

}

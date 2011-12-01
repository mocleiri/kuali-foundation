/**
 * Copyright 2004-2011 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl2.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.maven.mojo;

/**
 * Pojo representing the Ant "ant" task
 * 
 * http://ant.apache.org/manual/Tasks/ant.html
 */
public class AntTaskPojo {
	String antfile;
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

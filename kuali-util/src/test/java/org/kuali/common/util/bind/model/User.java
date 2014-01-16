package org.kuali.common.util.bind.model;

import java.io.File;

import org.kuali.common.util.bind.api.BindMapping;
import org.kuali.common.util.bind.api.Bound;

@Bound
public class User {

	String name;
	File home;

	@BindMapping({ "directory" })
	File dir;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public File getHome() {
		return home;
	}

	public void setHome(File home) {
		this.home = home;
	}

	public File getDir() {
		return dir;
	}

	public void setDir(File dir) {
		this.dir = dir;
	}

}

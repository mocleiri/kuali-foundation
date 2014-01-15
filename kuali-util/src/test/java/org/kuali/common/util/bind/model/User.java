package org.kuali.common.util.bind.model;

import java.io.File;
import java.util.TimeZone;

import org.kuali.common.util.bind.api.Bind;
import org.kuali.common.util.bind.api.BindMappings;

@Bind
public class User {

	String country;
	File home;
	File dir;

	@BindMappings("timezone")
	TimeZone timeZone;

	String name;

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
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

	public TimeZone getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(TimeZone timeZone) {
		this.timeZone = timeZone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}

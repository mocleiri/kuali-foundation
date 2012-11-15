package org.codehaus.mojo.sql;

import org.springframework.core.io.Resource;

public class SqlResource {

	String location;
	Resource resource;

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

}

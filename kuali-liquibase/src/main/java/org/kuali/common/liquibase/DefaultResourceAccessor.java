package org.kuali.common.liquibase;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;
import java.util.Vector;

import liquibase.resource.ResourceAccessor;

import org.kuali.common.util.LocationUtils;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

public final class DefaultResourceAccessor implements ResourceAccessor {

	private final ResourceLoader loader = new DefaultResourceLoader();

	@Override
	public InputStream getResourceAsStream(String location) throws IOException {
		return LocationUtils.getInputStream(location);
	}

	@Override
	public Enumeration<URL> getResources(String packageName) throws IOException {
		Resource resource = LocationUtils.getResource(packageName);
		URL url = resource.getURL();
		Vector<URL> tmp = new Vector<URL>();
		tmp.add(url);
		return tmp.elements();
	}

	@Override
	public ClassLoader toClassLoader() {
		return loader.getClassLoader();
	}

}

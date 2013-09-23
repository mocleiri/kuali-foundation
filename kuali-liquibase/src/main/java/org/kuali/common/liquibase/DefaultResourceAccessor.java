package org.kuali.common.liquibase;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;

import liquibase.resource.ResourceAccessor;

import org.kuali.common.util.LocationUtils;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;

public final class DefaultResourceAccessor implements ResourceAccessor {

	private final ResourceLoader loader = new DefaultResourceLoader();

	@Override
	public InputStream getResourceAsStream(String location) throws IOException {
		return LocationUtils.getInputStream(location);
	}

	@Override
	public Enumeration<URL> getResources(String packageName) throws IOException {
		return null;
	}

	@Override
	public ClassLoader toClassLoader() {
		return loader.getClassLoader();
	}

}

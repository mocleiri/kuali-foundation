package org.kuali.common.devops.cache;

import static com.google.common.base.Optional.absent;
import static com.google.common.base.Preconditions.checkNotNull;
import static org.kuali.common.util.base.Precondition.checkNotBlank;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.kuali.common.util.file.CanonicalFile;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import com.google.common.base.Optional;
import com.google.common.cache.CacheLoader;

public final class LocationLoader extends CacheLoader<String, Optional<String>> {

	public LocationLoader(String encoding) {
		this(new DefaultResourceLoader(), encoding);
	}

	public LocationLoader(ResourceLoader resourceLoader, String encoding) {
		this.resourceLoader = checkNotNull(resourceLoader, "resourceLoader");
		this.encoding = checkNotBlank(encoding, "encoding");
	}

	private final ResourceLoader resourceLoader;
	private final String encoding;

	@Override
	public Optional<String> load(String location) throws IOException {
		checkNotBlank(location, "location");
		Resource resource = getResource(location);
		if (resource.exists()) {
			return Optional.of(readResourceToString(resource, encoding));
		} else {
			return absent();
		}
	}

	protected String readResourceToString(Resource resource, String encoding) throws IOException {
		InputStream in = null;
		try {
			in = resource.getInputStream();
			return IOUtils.toString(in, encoding);
		} finally {
			IOUtils.closeQuietly(in);
		}
	}

	protected Resource getResource(String location) {
		File file = new File(location);
		if (file.exists()) {
			File canonical = new CanonicalFile(file);
			return new FileSystemResource(canonical);
		} else {
			return resourceLoader.getResource(location);
		}
	}

	public ResourceLoader getResourceLoader() {
		return resourceLoader;
	}

	public String getEncoding() {
		return encoding;
	}

}

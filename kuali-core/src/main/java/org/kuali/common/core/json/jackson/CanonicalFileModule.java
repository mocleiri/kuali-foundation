package org.kuali.common.core.json.jackson;

import java.io.File;

import com.fasterxml.jackson.databind.module.SimpleModule;

public final class CanonicalFileModule extends SimpleModule {

	private static final long serialVersionUID = 1L;

	public CanonicalFileModule() {
		addDeserializer(File.class, new CanonicalFileDeserializer());
		addSerializer(File.class, new CanonicalFileSerializer(File.class));
	}

}

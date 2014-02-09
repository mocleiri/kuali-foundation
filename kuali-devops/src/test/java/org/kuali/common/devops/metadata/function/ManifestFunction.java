package org.kuali.common.devops.metadata.function;

import static java.lang.String.format;
import static org.kuali.common.util.Encodings.UTF8;
import static org.kuali.common.util.base.Precondition.checkNotNull;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.SortedSet;
import java.util.jar.Attributes;
import java.util.jar.Manifest;

import org.kuali.common.util.log.Loggers;
import org.kuali.common.util.property.ImmutableProperties;
import org.slf4j.Logger;

import com.google.common.base.Function;
import com.google.common.collect.Sets;

public class ManifestFunction implements Function<String, Properties> {

	private static final Logger logger = Loggers.make();

	@Override
	public Properties apply(String content) {
		checkNotNull(content, "content");
		try {
			ByteArrayInputStream in = new ByteArrayInputStream(content.getBytes(UTF8));
			Manifest manifest = new Manifest(in);
			return convert(manifest);
		} catch (IOException e) {
			logger.warn(format("unexpected io error parsing manifest -> %s", e.getMessage()));
			return ImmutableProperties.of();
		}
	}

	protected Properties convert(Manifest manifest) {
		Attributes attributes = manifest.getMainAttributes();
		SortedSet<String> keys = getKeys(attributes);
		Properties properties = new Properties();
		for (String key : keys) {
			String value = attributes.getValue(key);
			properties.setProperty(key, value);
		}
		return ImmutableProperties.copyOf(properties);
	}

	protected SortedSet<String> getKeys(Attributes attributes) {
		SortedSet<String> keys = Sets.newTreeSet();
		for (Object key : attributes.keySet()) {
			keys.add(key.toString());
		}
		return keys;
	}

}

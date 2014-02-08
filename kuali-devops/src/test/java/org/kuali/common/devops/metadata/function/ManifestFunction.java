package org.kuali.common.devops.metadata.function;

import static org.kuali.common.util.Encodings.UTF8;
import static org.kuali.common.util.base.Exceptions.illegalState;
import static org.kuali.common.util.base.Precondition.checkNotNull;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.SortedSet;
import java.util.jar.Attributes;
import java.util.jar.Manifest;

import org.kuali.common.util.property.ImmutableProperties;

import com.google.common.base.Function;
import com.google.common.collect.Sets;

public class ManifestFunction implements Function<String, Properties> {

	@Override
	public Properties apply(String content) {
		checkNotNull(content, "content");
		try {
			ByteArrayInputStream in = new ByteArrayInputStream(content.getBytes(UTF8));
			Manifest manifest = new Manifest(in);
			return convert(manifest);
		} catch (IOException e) {
			throw illegalState(e, "unexpected io error parsing manifest -> \n\n%s\n\n", content);
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

package org.kuali.common.devops.logic;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.SortedSet;
import java.util.jar.Attributes;
import java.util.jar.Manifest;

import org.kuali.common.util.Encodings;
import org.kuali.common.util.base.Exceptions;

import com.google.common.base.Optional;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

public class Manifests extends Examiner {

	private static final String MANIFEST_LOCATION = "/tomcat/webapps/ROOT/META-INF/MANIFEST.MF";

	public static Map<String, String> getManifest(String fqdn) {
		String url = PROTOCOL + fqdn + MANIFEST_LOCATION;
		Optional<String> optional = HttpCacher.getContent(url);
		if (!optional.isPresent()) {
			return Maps.newHashMap();
		} else {
			String content = optional.get();
			Manifest manifest = getManifestFromString(content);
			return getMap(manifest);
		}
	}

	protected static Manifest getManifestFromString(String content) {
		try {
			ByteArrayInputStream in = new ByteArrayInputStream(content.getBytes(Encodings.UTF8));
			Manifest manifest = new Manifest(in);
			return manifest;
		} catch (IOException e) {
			throw Exceptions.illegalState(e, "unexpected io error converting string content into a Manifest object. \n\n%s\n\n", content);
		}
	}

	protected static Map<String, String> getMap(Manifest manifest) {
		Attributes attributes = manifest.getMainAttributes();
		Map<String, String> map = Maps.newHashMap();
		SortedSet<String> keys = getKeys(attributes);
		for (String key : keys) {
			String value = attributes.getValue(key);
			map.put(key, value);
		}
		return map;
	}

	protected static SortedSet<String> getKeys(Attributes attributes) {
		SortedSet<String> keys = Sets.newTreeSet();
		for (Object key : attributes.keySet()) {
			keys.add(key.toString());
		}
		return keys;
	}

}

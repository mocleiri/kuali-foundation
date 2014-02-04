package org.kuali.common.devops.logic;

import static java.lang.String.format;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.SortedSet;
import java.util.jar.Attributes;
import java.util.jar.Manifest;

import org.apache.commons.io.IOUtils;
import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.log.Loggers;
import org.slf4j.Logger;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

public class Manifests extends Examiner {

	private static final Logger logger = Loggers.make();

	public static Map<String, String> getManifest(String fqdn) {
		String fragment = "/tomcat/webapps/ROOT/META-INF/MANIFEST.MF";
		String location = PROTOCOL + fqdn + fragment;
		Map<String, String> map = Maps.newHashMap();
		InputStream in = null;
		try {
			in = LocationUtils.getInputStream(location);
			Manifest manifest = new Manifest(in);
			Attributes attributes = manifest.getMainAttributes();
			SortedSet<String> keys = getKeys(attributes);
			for (String key : keys) {
				String value = attributes.getValue(key);
				map.put(key, value);
			}
		} catch (IOException e) {
			logger.debug(format("error getting manifest -> [%s]", location));
		} finally {
			IOUtils.closeQuietly(in);
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

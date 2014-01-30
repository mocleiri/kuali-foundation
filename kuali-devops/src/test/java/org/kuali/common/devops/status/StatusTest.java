package org.kuali.common.devops.status;

import static com.google.common.base.Preconditions.checkState;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.SortedSet;
import java.util.jar.Attributes;
import java.util.jar.Manifest;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.kuali.common.devops.util.AwsRecord;
import org.kuali.common.devops.util.Environment;
import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.Str;
import org.kuali.common.util.log.LoggerUtils;
import org.kuali.common.util.project.ProjectUtils;
import org.kuali.common.util.project.model.Project;
import org.slf4j.Logger;

import com.google.common.base.Optional;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

public class StatusTest {

	private static final Splitter SPLITTER = Splitter.on('.');

	private static final Logger logger = LoggerUtils.make();

	@Test
	public void test() {
		try {
			// Map<String, String> fqdns = Fqdns.getMap();
			// Map<String, List<Instance>> instances = Instances.getMap();
			// List<AwsRecord> records = Instances.getRecords(instances);
			// List<Environment> envs = merge(records, fqdns);
			// logger.info("Located {} managed environments", envs.size());
			String fqdn = "env1.ks.kuali.org";
			Map<String, String> manifest = getManifest(fqdn);
			Properties properties = getProjectProperties(fqdn, manifest);
			String revision = manifest.get("SVN-Revision");
			if (revision != null) {
				properties.setProperty("project.scm.revision", revision);
			}
			Project project = ProjectUtils.getProject(properties);
			String tomcat = getTomcatVersion(fqdn);
			String java = getJavaVersion(fqdn);
			logger.info(project.getGroupId() + ":" + project.getArtifactId() + ":" + project.getVersion());
			logger.info(String.format("tomcat -> %s", tomcat));
			logger.info(String.format("java   -> %s", java));
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	protected String getJavaVersion(String fqdn) {
		String protocol = "http://";
		String fragment = "/tomcat/logs/env.jsp";
		String location = protocol + fqdn + fragment;
		List<String> lines = LocationUtils.readLines(location);
		String token = "java.version";
		for (String line : lines) {
			if (line.contains(token)) {
				int pos = line.indexOf(token) + token.length();
				String substring = line.substring(pos);
				String version = StringUtils.substringBetween(substring, "<td>", "</td>");
				return StringUtils.trim(version);
			}
		}
		return "unknown";
	}

	protected String getTomcatVersion(String fqdn) {
		String protocol = "http://";
		String fragment = "/tomcat/RELEASE-NOTES";
		String location = protocol + fqdn + fragment;
		List<String> lines = LocationUtils.readLines(location);
		String token = "Apache Tomcat Version";
		for (String line : lines) {
			if (line.contains(token)) {
				int pos = line.indexOf(token) + token.length();
				String version = line.substring(pos);
				return StringUtils.trim(version);
			}
		}
		return "unknown";
	}

	protected Properties getProjectProperties(String fqdn, Map<String, String> manifest) {
		Optional<String> bundleSymbolicName = Optional.fromNullable(manifest.get("Bundle-SymbolicName"));
		if (!bundleSymbolicName.isPresent()) {
			return new Properties();
		} else {
			String location = getProjectPropertiesPath(fqdn, bundleSymbolicName.get());
			return PropertyUtils.loadOrCreateSilently(location);
		}
	}

	protected String getProjectPropertiesPath(String fqdn, String bundleSymbolicName) {
		String protocol = "http://";
		String fragment = "/tomcat/webapps/ROOT/WEB-INF/classes/META-INF";
		String groupId = getGroupId(bundleSymbolicName);
		String artifactId = getArtifactId(bundleSymbolicName);
		String filename = "project.properties";
		StringBuilder sb = new StringBuilder();
		sb.append(protocol);
		sb.append(fqdn);
		sb.append(fragment);
		sb.append("/");
		sb.append(Str.getPath(groupId));
		sb.append("/");
		sb.append(artifactId);
		sb.append("/");
		sb.append(filename);
		return sb.toString();
	}

	protected String getArtifactId(String bundleSymbolicName) {
		List<String> tokens = SPLITTER.splitToList(bundleSymbolicName);
		return tokens.get(tokens.size() - 1);
	}

	protected String getGroupId(String bundleSymbolicName) {
		List<String> tokens = SPLITTER.splitToList(bundleSymbolicName);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < tokens.size() - 1; i++) {
			if (i != 0) {
				sb.append(".");
			}
			String token = tokens.get(i);
			sb.append(token);
			// Make sure to shorten "org.kuali.student.web" -> "org.kuali.student"
			if (token.equals("student")) {
				break;
			}
		}
		return sb.toString();
	}

	protected Map<String, String> getManifest(String fqdn) {
		String protocol = "http://";
		String fragment = "/tomcat/webapps/ROOT/META-INF/MANIFEST.MF";
		String location = protocol + fqdn + fragment;
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
			logger.warn(String.format("cannot access -> [%s]", location));
		} finally {
			IOUtils.closeQuietly(in);
		}
		return map;
	}

	protected SortedSet<String> getKeys(Attributes attributes) {
		SortedSet<String> keys = Sets.newTreeSet();
		for (Object key : attributes.keySet()) {
			keys.add(key.toString());
		}
		return keys;
	}

	protected List<Environment> merge(List<AwsRecord> records, Map<String, String> fqdns) {
		List<Environment> envs = Lists.newArrayList();
		for (AwsRecord record : records) {
			Environment env = new Environment();
			env.setId(record.getEnv());
			env.setProject(record.getProject());
			env.setType(record.getType());
			String fqdn = fqdns.get(record.getDns());
			checkState(fqdn != null, "No DNSME record for %s [%s=%s]", record.getProject(), record.getEnv(), record.getDns());
			env.setDns(fqdn);
			envs.add(env);
		}
		return envs;
	}

}

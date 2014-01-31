package org.kuali.common.devops.status;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkState;
import static java.lang.String.format;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.SortedSet;
import java.util.jar.Attributes;
import java.util.jar.Manifest;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.junit.Ignore;
import org.junit.Test;
import org.kuali.common.devops.util.AwsRecord;
import org.kuali.common.devops.util.Environment;
import org.kuali.common.devops.util.Fqdns;
import org.kuali.common.devops.util.Instances;
import org.kuali.common.devops.util.Tomcat;
import org.kuali.common.util.Encodings;
import org.kuali.common.util.FormatUtils;
import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.Str;
import org.kuali.common.util.file.CanonicalFile;
import org.kuali.common.util.log.LoggerUtils;
import org.kuali.common.util.project.ProjectUtils;
import org.kuali.common.util.project.model.Project;
import org.slf4j.Logger;

import com.amazonaws.services.ec2.model.Instance;
import com.google.common.base.Joiner;
import com.google.common.base.Optional;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

public class StatusTest {

	private static final Splitter SPLITTER = Splitter.on('.');
	private static final Joiner JOINER = Joiner.on(',');
	private static final Splitter LINE_SPLITTER = Splitter.on('\n');
	private static final Splitter CSV_SPLITTER = Splitter.on(',');
	private static final Splitter PIPE_SPLITTER = Splitter.on('|');
	private static final Splitter EQUALS_SPLITTER = Splitter.on('=');
	private static final SimpleDateFormat PARSER = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZZZZZ");

	private static final Logger logger = LoggerUtils.make();

	@Test
	public void test1() {
		try {
			String path = "classpath:environments.txt";
			List<Environment> envs = getEnvironments(path);
			info("%s envs", envs.size());
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	public void info(String msg, Object... args) {
		logger.info(format(msg, args));
	}

	@Test
	@Ignore
	public void test() {
		try {
			long start = System.currentTimeMillis();
			Map<String, String> fqdns = Fqdns.getMap();
			Map<String, List<Instance>> instances = Instances.getMap();
			List<AwsRecord> records = Instances.getRecords(instances);
			List<Environment> envs = merge(records, fqdns);
			for (Environment env : envs) {
				fillIn(env);
			}
			long elapsed = System.currentTimeMillis() - start;
			logger.info(format("elapsed -> %s", FormatUtils.getTime(elapsed)));
			write(envs);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	protected List<Environment> getEnvironments(String location) throws IOException {
		List<Environment> envs = Lists.newArrayList();
		checkState(LocationUtils.exists(location), "[%s] does not exist", location);
		List<String> lines = LocationUtils.readLines(location);
		for (int i = 1; i < lines.size(); i++) {
			String line = lines.get(i);
			Environment env = getEnvironment(line);
			envs.add(env);
		}
		return envs;
	}

	protected Environment getEnvironment(String csv) {
		List<String> tokens = CSV_SPLITTER.splitToList(csv);
		Environment env = new Environment();
		env.setProject(tokens.get(0));
		env.setId(tokens.get(1));
		env.setDns(tokens.get(2));
		env.setType(tokens.get(3));
		env.setJava(tokens.get(4));
		// group,env,fqdn,type,java,tomcat,startup,uptime,groupId,artifactId,version,properties
		env.setTomcat(getTomcat(tokens));
		env.setApplication(getProject(tokens));
		return env;
	}

	protected Optional<Project> getProject(List<String> tokens) {
		Properties props = getProperties(tokens);
		if (props.isEmpty()) {
			return Optional.absent();
		} else {
			return Optional.of(ProjectUtils.getProject(props));
		}
	}

	protected Properties getProperties(List<String> tokens) {
		String psv = tokens.get(11);
		List<String> values = PIPE_SPLITTER.splitToList(psv);
		List<String> list = Lists.newArrayList();
		for (String value : values) {
			String inflated = inflate(value);
			list.add(inflated);
		}
		Properties props = new Properties();
		for (String element : list) {
			if (element.equals("na")) {
				return props;
			}
			List<String> propTokens = EQUALS_SPLITTER.splitToList(element);
			checkArgument(propTokens.size() == 2, "Must always be exactly 2 tokens");
			String key = propTokens.get(0);
			String value = propTokens.get(1);
			props.setProperty(key, value);
		}
		return props;
	}

	protected String deflate(String s) {
		return s.replace("\r", "${cr}").replace("\n", "${lf}").replace(",", "${comma}").replace("|", "${pipe}");
	}

	protected String inflate(String s) {
		return s.replace("${cr}", "\r").replace("${lf}", "\n").replace("${comma}", ",").replace("${pipe}", "|");
	}

	protected Tomcat getTomcat(List<String> tokens) {
		Tomcat tomcat = new Tomcat();
		tomcat.setVersion(tokens.get(5));
		tomcat.setStartup(tokens.get(6));
		tomcat.setUptime(tokens.get(7));
		return tomcat;
	}

	protected void write(List<Environment> envs) throws IOException {
		List<String> lines = Lists.newArrayList();
		lines.add("group,env,fqdn,type,java,tomcat,startup,uptime,groupId,artifactId,version,properties");
		for (Environment env : envs) {
			lines.add(asCSV(env));
		}
		String path = System.getProperty("envs.out", "./target/env/environments.txt");
		File file = new CanonicalFile(path);
		FileUtils.writeLines(file, lines);
		logger.info(format("created -> %s", file));
	}

	protected String asCSV(Environment env) {
		List<String> tokens = Lists.newArrayList();
		tokens.add(env.getProject());
		tokens.add(env.getId());
		tokens.add(env.getDns());
		tokens.add(env.getType());
		tokens.add(env.getJava());
		tokens.add(env.getTomcat().getVersion());
		tokens.add(env.getTomcat().getStartup());
		tokens.add(env.getTomcat().getUptime());
		tokens.addAll(getTokens(env.getApplication()));
		return JOINER.join(tokens.iterator());
	}

	protected List<String> getTokens(Optional<Project> project) {
		List<String> tokens = Lists.newArrayList();
		if (project.isPresent()) {
			Project p = project.get();
			tokens.add(p.getGroupId());
			tokens.add(p.getArtifactId());
			tokens.add(p.getVersion());
			tokens.add(toString(p.getProperties()));
		} else {
			tokens.add("na");
			tokens.add("na");
			tokens.add("na");
			tokens.add("na");
		}
		return tokens;
	}

	protected String toString(Properties properties) {
		StringBuilder sb = new StringBuilder();
		SortedSet<String> keys = Sets.newTreeSet(properties.stringPropertyNames());
		for (String key : keys) {
			String value = properties.getProperty(key);
			String deflated = deflate(value);
			sb.append(key + "=" + deflated + "|");
		}
		return sb.substring(0, sb.length() - 1); // Remove the trailing "|"
	}

	protected void fillIn(Environment env) {
		logger.info(format("examining -> %s", env.getDns()));
		String fqdn = env.getDns();
		String java = getJavaVersion(fqdn);
		Tomcat tomcat = getTomcat(fqdn);
		env.setTomcat(tomcat);
		env.setJava(java);
		Map<String, String> manifest = getManifest(fqdn);
		Properties properties = getProjectProperties(fqdn, manifest);
		String artifactId = properties.getProperty("project.artifactId");
		if (artifactId != null) {
			Project application = ProjectUtils.getProject(properties);
			env.setApplication(Optional.of(application));
		} else {
			env.setApplication(Optional.<Project> absent());
		}
	}

	protected Tomcat getTomcat(String fqdn) {
		String version = getTomcatVersion(fqdn);
		// 2014-01-06T21:23:15.299+0000: 0.957: [GC
		long startup = getTomcatStartupTime(fqdn, PARSER);
		Tomcat tomcat = new Tomcat();
		tomcat.setVersion(version);
		if (startup != -1) {
			tomcat.setStartup(new Date(startup).toString());
			String uptime = FormatUtils.getTime(System.currentTimeMillis() - startup);
			int pos = uptime.indexOf('.');
			if (pos != -1) {
				String original = uptime;
				uptime = uptime.substring(0, pos);
				uptime = uptime + original.substring(original.length() - 1);
			}
			tomcat.setUptime(uptime);
		} else {
			tomcat.setStartup("na");
			tomcat.setUptime("na");
		}
		return tomcat;
	}

	protected long getTomcatStartupTime(String fqdn, SimpleDateFormat parser) {
		Optional<String> string = getTomcatStartupString(fqdn);
		if (!string.isPresent()) {
			return -1;
		}
		String s = string.get();
		int pos = s.indexOf(' ');
		String time = s.substring(0, pos - 1);
		try {
			Date date = parser.parse(time);
			return date.getTime();
		} catch (ParseException e) {
			throw new IllegalStateException(String.format("date parse error -> [%s]", time), e);
		}
	}

	protected Optional<String> getTomcatStartupString(String fqdn) {
		String protocol = "http://";
		String fragment = "/tomcat/logs/heap.log";
		String location = protocol + fqdn + fragment;
		Optional<String> heap = read(location, 4096);
		if (!heap.isPresent()) {
			return Optional.absent();
		}
		String gc = StringUtils.substringBetween(heap.get(), "{", "}");
		List<String> lines = LINE_SPLITTER.splitToList(gc);
		for (String line : lines) {
			if (line.startsWith("201")) { // This will only work for the next 6 years :)
				return Optional.of(line);
			}
		}
		return Optional.absent();
	}

	protected Optional<String> read(String location, int maxBytes) {
		InputStream in = null;
		try {
			StringBuilder sb = new StringBuilder();
			in = LocationUtils.getInputStream(location);
			byte[] buffer = new byte[4096];
			int len = -1;
			len = in.read(buffer);
			while (len != -1) {
				String string = new String(buffer, 0, len, Encodings.UTF8);
				sb.append(string);
				if (sb.length() >= maxBytes) {
					break;
				}
				len = in.read(buffer);
			}
			return Optional.of(sb.toString());
		} catch (IOException e) {
			logger.warn(String.format("error reading -> [%s]", location));
		} finally {
			IOUtils.closeQuietly(in);
		}
		return Optional.absent();
	}

	protected String getJavaVersion(String fqdn) {
		String protocol = "http://";
		String fragment = "/tomcat/logs/env.jsp";
		String location = protocol + fqdn + fragment;
		try {
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
		} catch (Exception e) {
			logger.warn(String.format("error getting java version -> [%s]", location));
		}
		return "na";
	}

	protected String getTomcatVersion(String fqdn) {
		String protocol = "http://";
		String fragment = "/tomcat/RELEASE-NOTES";
		String location = protocol + fqdn + fragment;
		try {
			List<String> lines = LocationUtils.readLines(location);
			String token = "Apache Tomcat Version";
			for (String line : lines) {
				if (line.contains(token)) {
					int pos = line.indexOf(token) + token.length();
					String version = line.substring(pos);
					return StringUtils.trim(version);
				}
			}
		} catch (Exception e) {
			logger.warn(String.format("error getting tomcat version -> [%s]", location));
		}
		return "na";
	}

	protected Properties getProjectProperties(String fqdn, Map<String, String> manifest) {
		Optional<String> bundleSymbolicName = Optional.fromNullable(manifest.get("Bundle-SymbolicName"));
		if (!bundleSymbolicName.isPresent()) {
			return new Properties();
		} else {
			String location = getProjectPropertiesPath(fqdn, bundleSymbolicName.get());
			Properties properties = PropertyUtils.loadOrCreateSilently(location);
			String revision = manifest.get("SVN-Revision");
			if (revision == null) {
				revision = "na";
			}
			properties.setProperty("project.scm.revision", revision);
			return properties;
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
			logger.warn(String.format("error getting manifest -> [%s]", location));
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

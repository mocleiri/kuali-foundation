package org.kuali.common.devops.status;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkState;
import static java.lang.String.format;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
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
import org.kuali.common.devops.logic.Auth;
import org.kuali.common.devops.logic.DNS;
import org.kuali.common.devops.logic.Databases;
import org.kuali.common.devops.logic.Environments;
import org.kuali.common.devops.logic.Groups;
import org.kuali.common.devops.logic.Instances;
import org.kuali.common.devops.model.Application;
import org.kuali.common.devops.model.EC2Instance;
import org.kuali.common.devops.model.Environment;
import org.kuali.common.devops.model.Group;
import org.kuali.common.devops.model.Tomcat;
import org.kuali.common.util.Encodings;
import org.kuali.common.util.FormatUtils;
import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.Str;
import org.kuali.common.util.file.CanonicalFile;
import org.kuali.common.util.log.LoggerUtils;
import org.kuali.common.util.project.KualiProjectConstants;
import org.kuali.common.util.project.ProjectUtils;
import org.kuali.common.util.project.model.Project;
import org.kuali.common.util.properties.rice.RiceLoader;
import org.slf4j.Logger;

import com.amazonaws.services.ec2.model.Instance;
import com.google.common.base.Joiner;
import com.google.common.base.Optional;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.common.collect.Table;

public class StatusTest {

	private static final Splitter SPLITTER = Splitter.on('.');
	private static final Joiner JOINER = Joiner.on(',');
	private static final Splitter LINE_SPLITTER = Splitter.on('\n');
	private static final Splitter CSV_SPLITTER = Splitter.on(',');
	private static final Splitter PIPE_SPLITTER = Splitter.on('|');
	private static final Splitter EQUALS_SPLITTER = Splitter.on('=');
	private static final SimpleDateFormat PARSER = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZZZZZ");

	private static final List<Environment> PROBLEMS = Lists.newArrayList();

	private static final Logger logger = LoggerUtils.make();

	@Test
	@Ignore
	public void test1() {
		try {
			String path = "/tmp/environments.txt";
			List<Environment> envs = getEnvironments(path);
			Collections.sort(envs);
			List<Group> groups = Groups.getGroups(envs);
			info("%s groups", groups.size());
			Table<Integer, Integer, ?> table = Environments.getTable(envs);
			String html = "<center>" + Environments.html(table) + "</center>";
			FileUtils.write(new File("/tmp/envs.htm"), html);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	@Test
	public void test() {
		try {
			long start = System.currentTimeMillis();
			Map<String, String> fqdns = DNS.getMap();
			Map<String, List<Instance>> instances = Instances.getMap(Auth.getAwsCredentials());
			List<EC2Instance> records = Instances.convert(instances);
			List<Environment> envs = merge(records, fqdns);
			Collections.sort(envs);
			for (Environment env : envs) {
				fillIn(env);
			}
			Databases.update(envs);
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
		Databases.update(envs);
		return envs;
	}

	protected Environment getEnvironment(String csv) {
		List<String> tokens = CSV_SPLITTER.splitToList(csv);
		Environment env = new Environment();
		env.setProject(tokens.get(0));
		env.setId(tokens.get(1));
		env.setFqdn(tokens.get(2));
		env.setType(tokens.get(3));
		env.setJava(tokens.get(4));
		// group,env,fqdn,type,java,tomcat,startup,project,config
		env.setTomcat(getTomcat(tokens));
		env.setApplication(getApplication(tokens));
		return env;
	}

	protected Optional<Application> getApplication(List<String> tokens) {
		String project = tokens.get(7);
		String config = tokens.get(8);
		Properties p1 = project.equals("na") ? new Properties() : fromString(project);
		Properties p2 = config.equals("na") ? new Properties() : fromString(config);
		if (p1.isEmpty()) {
			return Optional.absent();
		} else {
			Application application = new Application();
			application.setProject(ProjectUtils.getProject(p1));
			application.setConfiguration(p2);
			return Optional.of(application);
		}
	}

	protected String deflate(String s) {
		return s.replace("\r", "${cr}").replace("\n", "${lf}").replace(",", "${comma}").replace("|", "${pipe}");
	}

	protected String inflate(String s) {
		return s.replace("${cr}", "\r").replace("${lf}", "\n").replace("${comma}", ",").replace("${pipe}", "|");
	}

	protected Tomcat getTomcat(List<String> tokens) {
		String version = tokens.get(5);
		long startup = Long.parseLong(tokens.get(6));
		return Tomcat.create(version, startup);
	}

	protected void write(List<Environment> envs) throws IOException {
		List<String> lines = Lists.newArrayList();
		lines.add("group,env,fqdn,type,java,tomcat,startup,project,config");
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
		tokens.add(env.getFqdn());
		tokens.add(env.getType());
		tokens.add(env.getJava());
		tokens.add(env.getTomcat().getVersion());
		tokens.add(env.getTomcat().getStartupTime() + "");
		tokens.addAll(getTokens(env.getApplication()));
		return JOINER.join(tokens.iterator());
	}

	protected List<String> getTokens(Optional<Application> application) {
		List<String> tokens = Lists.newArrayList();
		if (application.isPresent()) {
			Project project = application.get().getProject();
			Properties configuration = application.get().getConfiguration();
			tokens.add(toString(project.getProperties()));
			if (!configuration.isEmpty()) {
				tokens.add(toString(configuration));
			} else {
				tokens.add("na");
			}
		} else {
			tokens.add("na");
			tokens.add("na");
		}
		return tokens;
	}

	protected Properties fromString(String string) {
		List<String> values = PIPE_SPLITTER.splitToList(string);
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
		logger.info(format("examining -> %s", env.getFqdn()));
		String fqdn = env.getFqdn();
		String java = getJavaVersion(fqdn);
		Tomcat tomcat = getTomcat(fqdn);
		env.setTomcat(tomcat);
		env.setJava(java);
		Map<String, String> manifest = getManifest(fqdn);
		Properties properties = getProjectProperties(fqdn, manifest);
		String artifactId = properties.getProperty("project.artifactId");
		if (artifactId != null) {
			Project project = ProjectUtils.getProject(properties);
			Application application = new Application();
			application.setProject(project);
			env.setApplication(Optional.of(application));
			Properties config = getConfig(env);
			application.setConfiguration(config);
		} else {
			env.setApplication(Optional.<Application> absent());
		}
	}

	protected Tomcat getTomcat(String fqdn) {
		String version = getTomcatVersion(fqdn);
		long startup = getTomcatStartupTime(fqdn, PARSER);
		return Tomcat.create(version, startup);
	}

	/**
	 * time format is -> 2014-01-06T21:23:15.299+0000: 0.957: [GC
	 * 
	 * @param fqdn
	 * @param parser
	 * @return
	 */
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
			throw new IllegalStateException(format("date parse error -> [%s]", time), e);
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
			int len = in.read(buffer);
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
		return getSystemProperty(fqdn, "java.version");
	}

	protected String getSystemProperty(String fqdn, String property) {
		String protocol = "http://";
		String fragment = "/tomcat/logs/env.jsp";
		String location = protocol + fqdn + fragment;
		try {
			List<String> lines = LocationUtils.readLines(location);
			for (String line : lines) {
				String token = "<td>" + property + "</td>";
				if (line.contains(token)) {
					int pos = line.indexOf(token) + token.length();
					String substring = line.substring(pos);
					String value = StringUtils.substringBetween(substring, "<td>", "</td>");
					return StringUtils.trim(value);
				}
			}
		} catch (Exception e) {
			logger.warn(String.format("error getting system property [%s] -> [%s]", property, location));
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

			// Only way to get a reliable SVN url is from the manifest
			String url = getScmUrl(manifest);
			properties.setProperty("project.scm.url", url);
			return properties;
		}
	}

	protected String getScmUrl(Map<String, String> manifest) {
		String url = manifest.get("SVN-URL");
		if (url == null) {
			return "na";
		}
		if (url.indexOf("${") != -1) {
			return "na";
		}
		if (!LocationUtils.exists(url)) {
			return "na";
		}
		return "scm:svn:" + url;
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
			// Shorten "org.kuali.student.*" -> "org.kuali.student"
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

	protected List<Environment> merge(List<EC2Instance> records, Map<String, String> fqdns) {
		List<Environment> envs = Lists.newArrayList();
		for (EC2Instance record : records) {
			Environment env = new Environment();
			env.setId(record.getName());
			env.setProject(record.getProject());
			env.setType(record.getType());
			String fqdn = fqdns.get(record.getDns());
			checkState(fqdn != null, "No DNSME record for %s [%s=%s]", record.getProject(), record.getName(), record.getDns());
			env.setFqdn(fqdn);
			envs.add(env);
		}
		return envs;
	}

	public void info(String msg, Object... args) {
		if (args != null && args.length > 0) {
			logger.info(format(msg, args));
		} else {
			logger.info(msg);
		}
	}

	protected Properties getConfig(Environment env) {
		if (!env.getApplication().isPresent()) {
			return new Properties();
		}
		String protocol = "http://";
		String fragment = getConfigFragment(env);
		String location = protocol + env.getFqdn() + fragment;
		try {
			return RiceLoader.load(location);
		} catch (Exception e) {
			info("error loading [%s] -> [%s]", location, e.getMessage());
			PROBLEMS.add(env);
			return new Properties();
		}
	}

	protected String getConfigFragment(Environment env) {
		Project project = env.getApplication().get().getProject();
		String groupId = project.getGroupId();
		if (groupId.equals(KualiProjectConstants.STUDENT_GROUP_ID)) {
			return "/home/kuali/main/dev/" + project.getArtifactId() + "-config.xml";
		} else if (groupId.equals(KualiProjectConstants.OLE_GROUP_ID)) {
			String environment = getSystemProperty(env.getFqdn(), "environment");
			return "/home/kuali/main/" + environment + "/common-config.xml";
		} else {
			return "/home/kuali/main/dev/common-config.xml";
		}
	}

}

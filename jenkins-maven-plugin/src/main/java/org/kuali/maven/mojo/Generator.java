package org.kuali.maven.mojo;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.maven.project.MavenProject;
import org.codehaus.plexus.util.StringUtils;
import org.kuali.maven.common.Extractor;
import org.kuali.maven.common.PropertiesUtils;

public class Generator {
	private static final String FS = System.getProperty("file.separator");
	Extractor extractor = new Extractor();
	PropertiesUtils pu = new PropertiesUtils();

	public void generate(JobContext context) throws IOException {
		Properties properties = getProperties(context);
		String xml = read(context.getTemplate());
		String resolvedXml = pu.getResolvedValue(xml, properties);
		write(context.getFilename(), resolvedXml);
	}

	public JobContext getJobContext(MavenProject project, String filename, String type, String template) {
		Extractor extractor = new Extractor();
		String scmType = extractor.getScmType(project.getScm());
		String scmUrl = extractor.getScmUrl(project.getScm());
		String majorVersion = extractor.getMajorVersion(project.getVersion());

		JobContext context = new JobContext();
		context.setTemplate(template);
		context.setProject(project);
		context.setJobType(type);
		context.setScmType(scmType);
		context.setScmUrl(scmUrl);
		context.setMajorVersion(majorVersion);

		if (!StringUtils.isEmpty(filename)) {
			context.setFilename(filename);
		} else {
			String defaultFilename = getDefaultFilename(context);
			context.setFilename(defaultFilename);
		}

		return context;
	}

	protected String getDefaultFilename(JobContext context) {
		String majorVersion = context.getMajorVersion();
		MavenProject project = context.getProject();
		String artifactId = project.getArtifactId();
		String buildDir = project.getBuild().getDirectory();
		StringBuilder sb = new StringBuilder();
		sb.append(buildDir);
		sb.append(FS);
		sb.append("jenkins");
		sb.append(FS);
		sb.append(artifactId);
		sb.append("-");
		sb.append(majorVersion);
		sb.append("-");
		sb.append(context.getJobType());
		sb.append("-");
		sb.append("config");
		sb.append(".xml");
		return sb.toString();
	}

	protected void write(String filename, String contents) throws IOException {
		File file = new File(filename);
		OutputStream out = null;
		try {
			out = FileUtils.openOutputStream(file);
			IOUtils.write(contents, out);
		} finally {
			IOUtils.closeQuietly(out);
		}

	}

	protected String read(String location) throws IOException {
		InputStream in = null;
		try {
			in = pu.getInputStream(location);
			return IOUtils.toString(in);
		} finally {
			IOUtils.closeQuietly(in);
		}
	}

	protected Properties getProperties(JobContext context) throws IOException {
		MavenProject project = context.getProject();

		List<String> locations = getLocations(context);
		Properties resourceProperties = pu.getProperties(locations);
		Properties jenkinsProperties = getJenkinsProperties(context);
		Properties projectProperties = project.getProperties();
		Properties environmentProperties = pu.getEnvironmentProperties();
		Properties systemProperties = System.getProperties();

		Properties properties = new Properties();
		properties.putAll(resourceProperties);
		properties.putAll(jenkinsProperties);
		properties.putAll(projectProperties);
		properties.putAll(environmentProperties);
		properties.putAll(systemProperties);
		return properties;
	}

	protected Properties getJenkinsProperties(JobContext context) {
		MavenProject project = context.getProject();
		Properties properties = new Properties();
		properties.setProperty("jenkins.project.scmType", context.getScmType());
		properties.setProperty("jenkins.project.scmUrl", context.getScmUrl());
		properties.setProperty("jenkins.project.majorVersion", context.getMajorVersion());
		properties.setProperty("jenkins.project.groupId", project.getGroupId());
		properties.setProperty("jenkins.project.artifactId", project.getArtifactId());
		return properties;
	}

	protected List<String> getLocations(JobContext context) {
		List<String> locations = new ArrayList<String>();
		locations.add("classpath:org/kuali/jenkins/kuali.properties");
		locations.add("classpath:org/kuali/jenkins/jenkins.properties");
		locations.add("classpath:org/kuali/jenkins/jobs/properties/common.xml");
		locations.add("classpath:org/kuali/jenkins/jobs/properties/" + context.getScmType() + ".xml");
		locations.add("classpath:org/kuali/jenkins/jobs/properties/types/" + context.getJobType() + ".xml");
		return locations;
	}

}

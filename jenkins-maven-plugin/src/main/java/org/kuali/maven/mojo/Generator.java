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
import org.kuali.maven.common.Extractor;
import org.kuali.maven.common.PropertiesUtils;

public class Generator {
    Extractor extractor = new Extractor();
    PropertiesUtils pu = new PropertiesUtils();
    private static final String FS = System.getProperty("file.separator");

    public String getDefaultFilename(MavenProject project, String type) {

        String majorVersion = extractor.getMajorVersion(project.getVersion());
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
        sb.append(type);
        sb.append(".xml");
        return sb.toString();
    }

    public void generate(JobContext context) throws IOException {
        MavenProject project = context.getProject();
        Type template = context.getType();
        String filename = context.getFilename();
        Properties properties = getProperties(project, template);
        String xml = getContent("classpath:org/kuali/jenkins/jobs/templates/jenkins.xml");
        String resolvedXml = pu.getResolvedValue(xml, properties);
        write(filename, resolvedXml);
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

    protected String getContent(String location) throws IOException {
        InputStream in = null;
        try {
            in = pu.getInputStream(location);
            return IOUtils.toString(in);
        } finally {
            IOUtils.closeQuietly(in);
        }
    }

    protected Properties getProperties(MavenProject project, Type template) throws IOException {
        String scmType = extractor.getScmType(project.getScm()).toLowerCase();
        String scmUrl = extractor.getScmUrl(project.getScm());
        String majorVersion = extractor.getMajorVersion(project.getVersion());

        List<String> locations = getLocations(scmType, scmUrl, template);
        Properties resourceProperties = pu.getProperties(locations);
        Properties jenkinsProperties = getJenkinsProperties(scmType, scmUrl, majorVersion, project);
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

    protected Properties getJenkinsProperties(String scmType, String scmUrl, String majorVersion, MavenProject project) {
        Properties properties = new Properties();
        properties.setProperty("jenkins.project.scmType", scmType);
        properties.setProperty("jenkins.project.scmUrl", scmUrl);
        properties.setProperty("jenkins.project.majorVersion", majorVersion);
        properties.setProperty("jenkins.project.groupId", project.getGroupId());
        properties.setProperty("jenkins.project.artifactId", project.getArtifactId());
        return properties;
    }

    protected List<String> getLocations(String scmType, String scmUrl, Type template) {
        List<String> locations = new ArrayList<String>();
        locations.add("classpath:org/kuali/cm/jenkins/kuali.properties");
        locations.add("classpath:org/kuali/cm/jenkins/jenkins.properties");
        locations.add("classpath:org/kuali/cm/jenkins/jobs/properties/common.xml");
        locations.add("classpath:org/kuali/cm/jenkins/jobs/properties/" + scmType + ".xml");
        String lowerCase = template.toString().toLowerCase();
        locations.add("classpath:org/kuali/cm/jenkins/jobs/properties/types/" + lowerCase + ".xml");
        return locations;
    }

}

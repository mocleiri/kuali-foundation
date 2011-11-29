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

    public void generate(JobContext context) throws IOException {
        MavenProject project = context.getProject();
        Template template = context.getTemplate();
        String filename = context.getFilename();
        Properties properties = getProperties(project, template);
        String xml = getContent("classpath:org/kuali/cm/jenkins/jobs/templates/jenkins.xml");
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

    protected Properties getProperties(MavenProject project, Template template) throws IOException {
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

    protected List<String> getLocations(String scmType, String scmUrl, Template template) {
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

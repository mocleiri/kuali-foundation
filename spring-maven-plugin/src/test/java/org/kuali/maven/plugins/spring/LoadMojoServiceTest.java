package org.kuali.maven.plugins.spring;

import java.io.File;
import java.util.Properties;

import org.apache.maven.model.Build;
import org.apache.maven.model.CiManagement;
import org.apache.maven.model.IssueManagement;
import org.apache.maven.project.MavenProject;
import org.junit.Test;
import org.kuali.common.util.LocationUtils;

public class LoadMojoServiceTest {

	protected MavenProject getMavenProject() {
		Properties properties = new Properties();
		properties.setProperty("foo", "bar");

		CiManagement ci = new CiManagement();
		ci.setUrl("http://ci.rice.kuali.org");
		ci.setSystem("jenkins");

		IssueManagement im = new IssueManagement();
		im.setSystem("jira");
		im.setUrl("http://jira.kuali.org");

		File basedir = new File(".");

		// properties.setProperty("project.build.directory", project.getBuild().getDirectory());
		// properties.setProperty("project.build.outputDirectory", project.getBuild().getOutputDirectory());
		// properties.setProperty("project.build.testOutputDirectory", project.getBuild().getTestOutputDirectory());
		// properties.setProperty("project.build.sourceDirectory", project.getBuild().getSourceDirectory());
		// properties.setProperty("project.build.scriptSourceDirectory", project.getBuild().getScriptSourceDirectory());
		// properties.setProperty("project.build.testSourceDirectory", project.getBuild().getTestSourceDirectory());

		Build build = new Build();
		build.setSourceDirectory(LocationUtils.getCanonicalPath(new File(basedir, "src/main")));
		build.setDirectory(LocationUtils.getCanonicalPath(new File(basedir, "target")));
		build.setOutputDirectory(LocationUtils.getCanonicalPath(new File(basedir, "target/classes")));
		build.setTestOutputDirectory(LocationUtils.getCanonicalPath(new File(basedir, "target/test-classes")));
		build.setScriptSourceDirectory(LocationUtils.getCanonicalPath(new File(basedir, "src/scripts")));
		build.setTestSourceDirectory(LocationUtils.getCanonicalPath(new File(basedir, "src/test")));

		TestableMavenProject project = new TestableMavenProject(properties);
		project.setPackaging("jar");
		project.setDescription("description");
		project.setInceptionYear("2013");
		project.setCiManagement(ci);
		project.setIssueManagement(im);
		project.setBasedir(basedir);
		project.setBuild(build);
		return project;
	}

	@Test
	public void test() {
		try {

			MavenProject project = getMavenProject();
			LoadMojo mojo = new LoadMojo();
			mojo.setProject(project);
			LoadMojoService service = new LoadMojoService();
			service.execute(mojo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

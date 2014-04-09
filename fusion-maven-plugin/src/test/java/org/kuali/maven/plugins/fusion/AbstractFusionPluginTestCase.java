/**
 * 
 */
package org.kuali.maven.plugins.fusion;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.apache.maven.plugin.testing.AbstractMojoTestCase;
import org.apache.maven.project.MavenProject;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;

/**
 * @author ocleirig
 *
 */
public abstract class AbstractFusionPluginTestCase extends AbstractMojoTestCase {

	/**
	 * 
	 */
	public AbstractFusionPluginTestCase() {
	}

	@Override
	protected final void setUp() throws Exception {
		
		super.setUp();
		
		
		
		onBefore();
	}

	protected void onBefore() throws Exception {
		
	}

	@Override
	protected final void tearDown() throws Exception {
		super.tearDown();
		
		onAfter();
	}

	protected void onAfter() throws Exception {
		
	}
	
	
	protected MavenProject readProjectFromPom (File pomFile) throws IOException, XmlPullParserException {
		Model model = null;
		FileReader reader = null;
		MavenXpp3Reader mavenreader = new MavenXpp3Reader();
		reader = new FileReader(pomFile);
		model = mavenreader.read(reader);
		model.setPomFile(pomFile);
		MavenProject project = new MavenProject(model);

		project.setFile(pomFile);
		
		reader.close();
		
		return project;
	}
	

	
	
	

}

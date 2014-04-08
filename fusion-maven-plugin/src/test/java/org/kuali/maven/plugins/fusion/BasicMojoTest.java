/**
 * 
 */
package org.kuali.maven.plugins.fusion;

import java.io.File;

import org.apache.maven.plugin.testing.AbstractMojoTestCase;
import org.junit.Test;

/**
 * @author ocleirig
 *
 */
public class BasicMojoTest extends AbstractMojoTestCase {

	/**
	 * 
	 */
	public BasicMojoTest() {
	}
	
	@Test
	public void testBasic() throws Exception {
		/*
		 * Simply verify that the parameters are being configured properly on the FuseMojo.
		 */
		File pomFile = getTestFile("src/test/resources/basic-pom.xml");
		
		assertNotNull(pomFile);
		
		assertTrue(pomFile.exists());
		
		FuseMojo mojo = (FuseMojo) lookupMojo(FusionMavenPluginConstants.FUSE_MOJO, pomFile);
		
		assertNotNull(mojo);
		
		assertEquals(5, mojo.getMappings().size());
		
		for (Mapping mapping : mojo.getMappings()) {
			
			if (mapping.getModule().equals("ks-api")) {
				assertEquals("ks-api", mapping.getBranchName());
				assertEquals("ks.api.version", mapping.getVersionProperty());
			}
			else if (mapping.getModule().equals("ks-core")) {
				assertEquals("ks-core", mapping.getBranchName());
				assertEquals("ks.core.version", mapping.getVersionProperty());
			}
			else if (mapping.getModule().equals("ks-lum")) {
				assertEquals("ks-lum", mapping.getBranchName());
				assertEquals("ks.lum.version", mapping.getVersionProperty());
			}
			else if (mapping.getModule().equals("ks-enroll")) {
				assertEquals("ks-enroll", mapping.getBranchName());
				assertEquals("ks.enroll.version", mapping.getVersionProperty());
			}
			else if (mapping.getModule().equals("ks-deployments")) {
				assertEquals("ks-deployments", mapping.getBranchName());
				assertEquals("ks.deployments.version", mapping.getVersionProperty());
			}
		}
		
		
	}

}

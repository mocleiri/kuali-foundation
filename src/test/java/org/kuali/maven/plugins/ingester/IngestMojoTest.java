package org.kuali.maven.plugins.ingester;

import java.io.File;

import org.apache.maven.plugin.testing.*;
import org.kuali.maven.plugins.ingester.*;
import org.junit.Before;
import org.junit.Test;

public class IngestMojoTest extends AbstractMojoTestCase {

	@Before
	protected void setUp() throws Exception {
        // required for mojo lookups to work
        super.setUp();
    }

	@Test
	public void testMojoGoal() throws Exception {
		File testPom = new File( getBasedir(), "src/test/resources/unit/ingest-basic-test/plugin-config.xml" );
		IngestMojo mojo = (IngestMojo) lookupMojo ( "ingest", testPom );
	}
	
}

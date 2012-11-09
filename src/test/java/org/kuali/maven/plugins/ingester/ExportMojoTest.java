/**
 * Copyright 2011-2012 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl2.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.maven.plugins.ingester;

import java.io.File;

import org.apache.maven.plugin.testing.*;
import org.junit.Before;
import org.junit.Test;

public class ExportMojoTest extends AbstractMojoTestCase {

	@Before
	protected void setUp() throws Exception {
        // required for mojo lookups to work
        super.setUp();
    }

	@Test
	public void testMojoGoal() throws Exception {
		File testPom = new File( getBasedir(), "src/test/resources/unit/export-basic-test/plugin-config.xml" );
		ExportMojo mojo = (ExportMojo) lookupMojo ( "export", testPom );
	}
}

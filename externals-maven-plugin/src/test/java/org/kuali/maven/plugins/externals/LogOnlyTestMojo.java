/**
 * Copyright 2011-2014 The Kuali Foundation
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
/**
 * 
 */
package org.kuali.maven.plugins.externals;

import org.apache.maven.plugin.Mojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugin.logging.Log;
import org.apache.maven.plugin.logging.SystemStreamLog;

/**
 * @author ocleirig
 * 
 * A mojo for use in testing the MojoHelper that provides only a stream based logger.
 *
 */
public class LogOnlyTestMojo implements Mojo {

	private static final SystemStreamLog logger = new SystemStreamLog();
	
	/**
	 * 
	 */
	public LogOnlyTestMojo() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see org.apache.maven.plugin.Mojo#execute()
	 */
	@Override
	public void execute() throws MojoExecutionException, MojoFailureException {
		// intentionally does nothing

	}

	/* (non-Javadoc)
	 * @see org.apache.maven.plugin.Mojo#setLog(org.apache.maven.plugin.logging.Log)
	 */
	@Override
	public void setLog(Log log) {
		// intentionally does nothing

	}

	@Override
	public Log getLog() {
		return logger;
	}

	

}

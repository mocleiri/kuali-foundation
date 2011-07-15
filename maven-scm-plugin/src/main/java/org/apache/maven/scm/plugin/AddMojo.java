package org.apache.maven.scm.plugin;

/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.scm.ScmException;
import org.apache.maven.scm.ScmFile;
import org.apache.maven.scm.command.add.AddScmResult;
import org.apache.maven.scm.repository.ScmRepository;

import java.io.IOException;
import java.util.List;

/**
 * Add a file set to the project.
 * 
 * @author <a href="julien.henry@capgemini.com">Julien Henry</a>
 * @version $Id: AddMojo.java 685670 2008-08-13 20:25:56Z vsiveton $
 * @goal add
 * @aggregator
 */
public class AddMojo extends AbstractScmMojo {
	/** {@inheritDoc} */
	public void execute() throws MojoExecutionException {
		super.execute();

		try {
			ScmRepository repository = getScmRepository();

			AddScmResult result = getScmManager().add(repository, getFileSet());

			checkResult(result);

			List<ScmFile> addedFiles = result.getAddedFiles();
			for (ScmFile addedFile : addedFiles) {
				getLog().info("Added " + addedFile);
			}

		} catch (IOException e) {
			throw new MojoExecutionException("Cannot run add command : ", e);
		} catch (ScmException e) {
			throw new MojoExecutionException("Cannot run add command : ", e);
		}
	}
}

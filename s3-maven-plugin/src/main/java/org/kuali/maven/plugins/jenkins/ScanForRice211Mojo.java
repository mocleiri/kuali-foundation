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
package org.kuali.maven.plugins.jenkins;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;

/**
 * @goal rice
 */
public class ScanForRice211Mojo extends AbstractMojo {
	@Override
	public void execute() throws MojoExecutionException {
		try {
			File dir = new File("/var/lib/jenkins/m2/repository/org/kuali/rice");
			IOFileFilter filter = new Rice211Filter();
			Collection<File> c = FileUtils.listFiles(dir, filter, TrueFileFilter.INSTANCE);
			List<File> list = new ArrayList<File>(c);
			Collections.sort(list);
			getLog().info(list.size() + "");
			long size = 0;
			for (File file : list) {
				getLog().info(file.getAbsolutePath());
				size += file.length();
			}
			long mb = 1024 * 1024;
			getLog().info("size=" + (size / mb) + "mb");
		} catch (Exception e) {
			throw new MojoExecutionException("Unexpected error", e);
		}
	}
}
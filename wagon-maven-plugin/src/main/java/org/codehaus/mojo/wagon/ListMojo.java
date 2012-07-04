/**
 * Copyright 2008-2012 The Kuali Foundation
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
package org.codehaus.mojo.wagon;

import java.util.List;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.wagon.Wagon;
import org.apache.maven.wagon.WagonException;
import org.codehaus.mojo.wagon.shared.WagonFileSet;

/**
 * Lists the files under a specified directory for a repository
 *
 * @goal list
 * @requiresProject false
 */
public class ListMojo extends AbstractWagonListMojo {

    @Override
    protected void execute(Wagon wagon) throws MojoExecutionException, WagonException {
        WagonFileSet wagonFileSet = getWagonFileSet();
        List<String> files = wagonDownload.getFileList(wagon, wagonFileSet, getLog());
        getLog().info("File Count: " + files.size());
        for (String file : files) {
            getLog().info(file);
        }
    }
}

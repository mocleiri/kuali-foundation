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

import java.io.File;

import org.apache.maven.wagon.Wagon;
import org.apache.maven.wagon.WagonException;
import org.codehaus.mojo.wagon.shared.WagonFileSet;

/**
 * Transfers a set of files from a remote URL to a specified local directory.
 *
 * @goal download
 * @requiresProject false
 */
public class DownloadMojo extends AbstractWagonListMojo {

    /**
     * Local directory that files are downloaded to
     *
     * @parameter expression="${wagon.toDir}" default-value="${project.build.directory}/wagon-plugin"
     */
    private File toDir;

    /**
     * If true, files that already exist on the local file system are not downloaded
     *
     * @parameter expression="${wagon.skipExisting}" default-value="false"
     */
    private boolean skipExisting;

    @Override
    protected void execute(Wagon wagon) throws WagonException {
        WagonFileSet fileSet = this.getWagonFileSet();
        fileSet.setDownloadDirectory(this.toDir);
        this.wagonDownload.download(wagon, fileSet, this.getLog(), skipExisting);
    }

}

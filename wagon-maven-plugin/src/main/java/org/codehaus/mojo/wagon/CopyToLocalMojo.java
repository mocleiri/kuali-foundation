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
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.wagon.Wagon;
import org.codehaus.mojo.wagon.shared.WagonCopy;
import org.codehaus.mojo.wagon.shared.WagonFileSet;
import org.codehaus.mojo.wagon.shared.WagonUtils;

/**
 * Copy artifacts from a Maven repository to the local file system
 *
 * @goal copytolocal
 * @requiresProject false
 */
public class CopyToLocalMojo extends AbstractSingleWagonMojo {

    /**
     * Comma separated list of Ant's includes to scan for remote files
     *
     * @parameter expression="${wagon.includes}" default-value="**";
     */
    private String includes;

    /**
     * Comma separated list of Ant's excludes to scan for remote files
     *
     * @parameter expression="${wagon.excludes}"
     */
    private String excludes;

    /**
     * Location on the local file system where files will be copied
     *
     * @parameter expression="${wagon.localRepository}" default-value="${user.home}/.m2/repository"
     */
    private String localRepository;

    /**
     * @component
     */
    private WagonCopy wagonCopy;

    @Override
    protected void execute(Wagon wagon) throws MojoExecutionException {
        try {
            WagonFileSet wfs = WagonUtils.getWagonFileSet(includes, excludes);
            wfs.setDownloadDirectory(prepareFileSystem());
            wagonCopy.copyToLocal(wagon, wfs, getLog());
        } catch (Exception e) {
            throw new MojoExecutionException("Unexpected error", e);
        }
    }

    protected File prepareFileSystem() throws IOException {
        File file = new File(localRepository);
        FileUtils.forceMkdir(file);
        return file;
    }

    public String getIncludes() {
        return includes;
    }

    public void setIncludes(String includes) {
        this.includes = includes;
    }

    public String getExcludes() {
        return excludes;
    }

    public void setExcludes(String excludes) {
        this.excludes = excludes;
    }

    public WagonCopy getWagonCopy() {
        return wagonCopy;
    }

    public void setWagonCopy(WagonCopy wagonCopy) {
        this.wagonCopy = wagonCopy;
    }

    public String getLocalRepository() {
        return localRepository;
    }

    public void setLocalRepository(String localRepository) {
        this.localRepository = localRepository;
    }

}
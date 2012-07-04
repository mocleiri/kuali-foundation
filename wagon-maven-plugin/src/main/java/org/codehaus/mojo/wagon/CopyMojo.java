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

import java.io.IOException;

import org.apache.maven.wagon.Wagon;
import org.apache.maven.wagon.WagonException;
import org.codehaus.mojo.wagon.shared.WagonCopy;
import org.codehaus.mojo.wagon.shared.WagonFileSet;

/**
 * Copy artifacts from one Wagon repository to another Wagon repository.
 *
 * @goal copy
 * @requiresProject false
 */
public class CopyMojo extends AbstractCopyMojo {

    /**
     * Directory path relative to source's Wagon
     *
     * @parameter expression="${wagon.fromDir}" default-value=""
     */
    private String fromDir = "";

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
     * Whether to consider remote path case sensitivity during scan
     *
     * @parameter expression="${wagon.caseSensitive}"
     */
    private boolean caseSensitive = true;

    /**
     * Remote path relative to target's url to copy files to.
     *
     * @parameter expression="${wagon.toDir}" default-value="";
     */
    private String toDir = "";

    /**
     * @component
     */
    private WagonCopy wagonCopy;

    @Override
    protected void copy(Wagon srcWagon, Wagon targetWagon) throws IOException, WagonException {
        WagonFileSet fileSet = getWagonFileSet(fromDir, includes, excludes, caseSensitive, toDir);
        wagonCopy.copy(srcWagon, fileSet, targetWagon, optimize, getLog());
    }

}
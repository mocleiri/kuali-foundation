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

/*
 * Licensed to the Apache Software Foundation (ASF) under one or more contributor license
 * agreements. See the NOTICE file distributed with this work for additional information regarding
 * copyright ownership. The ASF licenses this file to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance with the License. You may obtain a
 * copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

import java.util.Iterator;
import java.util.List;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.wagon.Wagon;
import org.apache.maven.wagon.WagonException;
import org.codehaus.mojo.wagon.shared.WagonFileSet;

/**
 * Lists the content of the specified directory (remotePath) under a specified repository (url)
 *
 * @goal list
 * @requiresProject false
 */
public class ListMojo extends AbstractWagonListMojo {

    @Override
    protected void execute(Wagon wagon) throws MojoExecutionException, WagonException {
        WagonFileSet wagonFileSet = getWagonFileSet();
        List<?> files = wagonDownload.getFileList(wagon, wagonFileSet, this.getLog());
        getLog().info("File Count: " + files.size());
        for (Iterator<?> iterator = files.iterator(); iterator.hasNext();) {
            String file = (String) iterator.next();
            getLog().info("\t" + file);
        }
    }
}

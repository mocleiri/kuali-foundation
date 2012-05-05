/**
 * Copyright 2004-2012 The Kuali Foundation
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
package org.kuali.maven.plugins;

import org.apache.commons.lang.StringUtils;
import org.apache.maven.plugin.MojoExecutionException;

/**
 * Examine a MANIFEST.MF file for the presence of an SVN revision number.
 *
 * @author Jeff Caddel
 * @goal getsvnrevision
 */
public class GetSvnRevisionMojo extends GetAttributeMojo {

    @Override
    protected void validate(String revisionNumber) throws MojoExecutionException {
        if (StringUtils.isBlank(revisionNumber)) {
            throw new MojoExecutionException("Could not locate a value for " + getAttribute() + " in " + getFilename());
        }
        Integer number = Integer.parseInt(revisionNumber);
        if (number < 0) {
            throw new MojoExecutionException("Negative revision number");
        }
    }

}

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

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import org.apache.commons.lang.StringUtils;
import org.apache.maven.plugin.MojoExecutionException;

/**
 * Examine a MANIFEST.MF file for the presence of an SVN url.
 *
 * @author Jeff Caddel
 * @goal getsvnurl
 */
public class GetSvnUrlMojo extends GetAttributeMojo {

    /**
     * The suffix to append to the url (if any)
     *
     * @parameter expression="${manifest.suffix}"
     */
    private String suffix;

    @Override
    protected void validate(String svnUrl) throws MojoExecutionException {
        if (StringUtils.isBlank(svnUrl)) {
            throw new MojoExecutionException("Could not locate a value for " + getAttribute() + " in " + getFilename());
        }
        try {
            URI uri = new URI(svnUrl);
            new URL(uri.normalize().toString());
        } catch (Exception e) {
            throw new MojoExecutionException("Invalid url", e);
        }
    }

    @Override
    protected String process(String svnUrl) throws MojoExecutionException {
        try {
            if (!StringUtils.isBlank(suffix)) {
                svnUrl += suffix;
            }
            URI uri = new URI(svnUrl);
            return uri.normalize().toString();
        } catch (URISyntaxException e) {
            throw new MojoExecutionException("Invalid url", e);
        }
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }
}

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
            URI uri = new URI(svnUrl);
            return uri.normalize().toString();
        } catch (URISyntaxException e) {
            throw new MojoExecutionException("Invalid url", e);
        }
    }
}

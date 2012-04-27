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

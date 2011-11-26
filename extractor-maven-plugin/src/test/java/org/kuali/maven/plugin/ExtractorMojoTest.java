package org.kuali.maven.plugin;

import junit.framework.Assert;

import org.apache.maven.model.Scm;
import org.junit.Test;

/**
 *
 */
public class ExtractorMojoTest {

    @Test
    public void testGetScmUrl() {
        ExtractorMojo mojo = new ExtractorMojo();
        String dev = "scm:git:git@github.com:jcaddel/maven-s3-wagon.git";
        Scm scm = new Scm();
        scm.setDeveloperConnection(dev);
        String scmUrl = mojo.getScmUrl(scm);
        Assert.assertEquals("git@github.com:jcaddel/maven-s3-wagon.git", scmUrl);
    }
}

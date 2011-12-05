package org.kuali.maven.plugins.jenkins.helper;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JavaHelperTest {
    private static final Logger logger = LoggerFactory.getLogger(JavaHelperTest.class);
    JavaHelper helper = new JavaHelper();

    @Test
    public void testGetExecutable() {
        String executable = helper.getExecutable();
        logger.info(executable);
        Assert.assertNotNull(executable);
    }

    @Test
    public void testExecuteJar() {
        String userDir = System.getProperty("user.dir");
        System.out.println(userDir);
    }

}

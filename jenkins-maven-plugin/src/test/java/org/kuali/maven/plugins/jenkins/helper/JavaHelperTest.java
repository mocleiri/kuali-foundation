package org.kuali.maven.plugins.jenkins.helper;

import java.io.File;

import org.junit.Assert;
import org.junit.Test;
import org.kuali.maven.plugins.jenkins.context.ProcessResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JavaHelperTest {
    private static final String FS = System.getProperty("file.separator");
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
        String path = "." + FS + "src" + FS + "test" + FS + "resources" + FS + "jenkins-cli-1.440.jar";
        File jar = new File(path);
        ProcessResult result = helper.executeJar(jar);
        logger.info("Result: " + result.getExitValue());
        for (String line : result.getOutputLines()) {
            logger.info(line);
        }
        Assert.assertEquals(255, result.getExitValue());
    }
}

package org.kuali.maven.plugins.jenkins.helper;

import org.junit.Assert;
import org.junit.Test;
import org.kuali.maven.plugins.jenkins.context.ProcessResult;
import org.kuali.maven.plugins.jenkins.helper.JavaHelper;
import org.kuali.maven.plugins.jenkins.helper.ProcessHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProcessHelperTest {
    private static final Logger logger = LoggerFactory.getLogger(ProcessHelperTest.class);
    ProcessHelper pHelper = new ProcessHelper();
    JavaHelper jHelper = new JavaHelper();

    @Test
    public void testExecute() {
        String java = jHelper.getExecutable();

        ProcessResult result = pHelper.execute(java, "-version");
        for (String line : result.getOutputLines()) {
            logger.info(line);
        }
        Assert.assertEquals(0, result.getExitValue());
    }

}

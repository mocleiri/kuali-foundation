/**
 * Copyright 2011-2012 The Kuali Foundation
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
package org.kuali.maven.plugins.jenkins.helper;

import org.junit.Assert;
import org.junit.Test;
import org.kuali.maven.plugins.jenkins.context.ProcessResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProcessHelperTest {
    private static final Logger logger = LoggerFactory.getLogger(ProcessHelperTest.class);
    ProcessHelper pHelper = new ProcessHelper();
    JavaHelper jHelper = new JavaHelper();

    @Test
    public void testExecuteMvn() {
        String mvn = "mvn";

        ProcessResult result = pHelper.execute(mvn, "-v");
        for (String line : result.getOutputLines()) {
            logger.info(line);
        }
        Assert.assertEquals(0, result.getExitValue());
    }

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

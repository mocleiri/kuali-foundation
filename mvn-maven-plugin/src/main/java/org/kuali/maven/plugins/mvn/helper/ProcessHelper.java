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
package org.kuali.maven.plugins.mvn.helper;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.kuali.maven.plugins.mvn.context.ProcessContext;
import org.kuali.maven.plugins.mvn.context.ProcessException;
import org.kuali.maven.plugins.mvn.context.ProcessResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Helper class for executing external processes. Default behavior is to start a process and wait for it to complete
 * before continuing. The stdin of the spawned process can be provided with input via the "input" string. Any output
 * (error or otherwise) that the process sends to its stdout is captured in the "output" variable of the corresponding
 * ProcessResult object.
 */
public class ProcessHelper {
    private static final Logger logger = LoggerFactory.getLogger(ProcessHelper.class);

    public ProcessResult execute(String executable) {
        return execute(executable, (String[]) null);
    }

    public ProcessResult execute(String executable, String... args) {
        return execute(executable, args, null);
    }

    public ProcessResult execute(String executable, String[] args, String input) {
        ProcessContext context = new ProcessContext();
        context.setExecutable(executable);
        context.setArgs(args);
        context.setInput(input);
        return execute(context);
    }

    public ProcessResult execute(ProcessContext context) {
        try {
            String[] command = getProcessBuilderCommand(context.getExecutable(), context.getArgs());
            ProcessBuilder builder = new ProcessBuilder(command);
            builder.redirectErrorStream(true);
            long start = System.currentTimeMillis();
            logger.debug("Starting process");
            Process process = builder.start();
            logger.debug("Process started");
            if (!StringUtils.isBlank(context.getInput())) {
                logger.debug("Writing input=" + context.getInput());
                IOUtils.write(context.getInput(), process.getOutputStream());
                logger.debug("Done writing input");
                process.getOutputStream().close();
            }
            logger.debug("Reading output");
            String output = IOUtils.toString(process.getInputStream());
            logger.debug("Done reading output=" + output);
            List<String> outputLines = Helper.getLines(output);
            int exitValue = process.waitFor();
            long stop = System.currentTimeMillis();
            long elapsed = stop - start;
            ProcessResult result = new ProcessResult();
            result.setContext(context);
            result.setExitValue(exitValue);
            result.setOutput(output);
            result.setOutputLines(outputLines);
            result.setStart(start);
            result.setStop(stop);
            result.setElapsed(elapsed);
            return result;
        } catch (Exception e) {
            throw new ProcessException(e);
        }
    }

    protected String[] getProcessBuilderCommand(String executable, String... args) {
        List<String> command = new ArrayList<String>();
        command.add(executable);
        Helper.addToList(command, args);
        return Helper.toArray(command);
    }
}

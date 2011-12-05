package org.kuali.maven.plugins.jenkins;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.codehaus.plexus.util.StringUtils;
import org.kuali.maven.plugins.jenkins.context.ProcessContext;
import org.kuali.maven.plugins.jenkins.context.ProcessException;
import org.kuali.maven.plugins.jenkins.context.ProcessResult;

public class ProcessHelper {

    public ProcessResult execute(ProcessContext context) {
        String input = context.getInput();
        String[] args = context.getArgs();
        ProcessBuilder builder = new ProcessBuilder(args);
        builder.redirectErrorStream(true);
        try {
            long start = System.currentTimeMillis();
            Process process = builder.start();
            if (!StringUtils.isBlank(input)) {
                IOUtils.write(input, process.getOutputStream());
            }
            String output = IOUtils.toString(process.getInputStream());
            List<String> outputLines = getOutputLines(output);
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

    protected List<String> getOutputLines(String s) throws IOException {
        if (StringUtils.isBlank(s)) {
            return new ArrayList<String>();
        }
        InputStream in = new ByteArrayInputStream(s.getBytes());
        return IOUtils.readLines(in);
    }

}

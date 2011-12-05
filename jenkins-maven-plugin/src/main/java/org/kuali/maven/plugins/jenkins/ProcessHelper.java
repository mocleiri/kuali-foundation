package org.kuali.maven.plugins.jenkins;

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
            Process process = builder.start();
            if (!StringUtils.isBlank(input)) {
                IOUtils.write(input, process.getOutputStream());
            }
            List<String> output = IOUtils.readLines(process.getInputStream());
            int exitValue = process.waitFor();
        } catch (Exception e) {
            throw new ProcessException(e);
        }
        return null;
    }

}

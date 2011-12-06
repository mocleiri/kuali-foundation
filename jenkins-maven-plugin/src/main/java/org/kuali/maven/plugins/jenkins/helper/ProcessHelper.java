package org.kuali.maven.plugins.jenkins.helper;

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
            Process process = builder.start();
            if (!StringUtils.isBlank(context.getInput())) {
                IOUtils.write(context.getInput(), process.getOutputStream());
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

    protected String[] getProcessBuilderCommand(String executable, String... args) {
        List<String> command = new ArrayList<String>();
        command.add(executable);
        Helper.addToList(command, args);
        return command.toArray(new String[command.size()]);
    }

    protected List<String> getOutputLines(String s) throws IOException {
        if (StringUtils.isBlank(s)) {
            return new ArrayList<String>();
        }
        InputStream in = new ByteArrayInputStream(s.getBytes());
        return IOUtils.readLines(in);
    }

}

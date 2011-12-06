/**
 * Copyright 2004-2011 The Kuali Foundation
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
package org.kuali.maven.plugins.jenkins.context;

import java.util.List;

/**
 * <p>
 * Holds the result of executing an external process
 * </p>
 *
 * context - holds the executable, arguments, and any input provided to the process<br>
 * exitValue - the return value of the process - by convention zero means success<br>
 * output - holds anything written to standard out by the process<br>
 * outputLines - same content as 'output' but split up into individual lines<br>
 * start - the time the process started (millis since the epoch)<br>
 * stop - the time the process finished (millis since the epoch)<br>
 * elapsed - the total elapsed time in millis for the process<br>
 *
 * @author jeffcaddel
 */

public class ProcessResult {
    ProcessContext context;
    int exitValue;
    String output;
    List<String> outputLines;
    long start;
    long stop;
    long elapsed;

    public ProcessContext getContext() {
        return context;
    }

    public void setContext(ProcessContext context) {
        this.context = context;
    }

    public int getExitValue() {
        return exitValue;
    }

    public void setExitValue(int exitValue) {
        this.exitValue = exitValue;
    }

    public List<String> getOutputLines() {
        return outputLines;
    }

    public void setOutputLines(List<String> outputLines) {
        this.outputLines = outputLines;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public long getStop() {
        return stop;
    }

    public void setStop(long stop) {
        this.stop = stop;
    }

    public long getElapsed() {
        return elapsed;
    }

    public void setElapsed(long elapsed) {
        this.elapsed = elapsed;
    }
}

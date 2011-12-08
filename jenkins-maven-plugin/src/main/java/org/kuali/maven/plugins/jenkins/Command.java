/**
 * Copyright 2011-2011 The Kuali Foundation
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
package org.kuali.maven.plugins.jenkins;

import java.io.File;
import java.util.List;

public class Command {
    private List<String> args;
    private String stdin;
    private String stdinUrl;
    private File stdout;

    public List<String> getArgs() {
        return args;
    }

    public void setArgs(List<String> args) {
        this.args = args;
    }

    public String getStdin() {
        return stdin;
    }

    public void setStdin(String stdin) {
        this.stdin = stdin;
    }

    public String getStdinUrl() {
        return stdinUrl;
    }

    public void setStdinUrl(String stdinUrl) {
        this.stdinUrl = stdinUrl;
    }

    public File getStdout() {
        return stdout;
    }

    public void setStdout(File stdout) {
        this.stdout = stdout;
    }
}

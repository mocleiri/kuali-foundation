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
package org.kuali.maven.plugins.mvn.context;

public class JenkinsException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 3462717504491983361L;

    public JenkinsException() {
        super();
    }

    public JenkinsException(String message) {
        super(message);
    }

    public JenkinsException(Throwable exception) {
        super(exception);
    }

    public JenkinsException(String message, Throwable exception) {
        super(message, exception);
    }

}

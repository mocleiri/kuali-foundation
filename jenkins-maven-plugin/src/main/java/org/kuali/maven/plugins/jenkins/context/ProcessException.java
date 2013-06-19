/**
 * Copyright 2011-2013 The Kuali Foundation
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

public class ProcessException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1784042884373514140L;

    public ProcessException() {
        super();
    }

    public ProcessException(String message) {
        super(message);
    }

    public ProcessException(Throwable exception) {
        super(exception);
    }

    public ProcessException(String message, Throwable exception) {
        super(message, exception);
    }

}

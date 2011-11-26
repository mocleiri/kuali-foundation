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
package org.kuali.common.threads;

public class ThreadHandlerException extends RuntimeException {

    private static final long serialVersionUID = -9141511246502324732L;

    public ThreadHandlerException() {
    }

    public ThreadHandlerException(String message) {
        super(message);
    }

    public ThreadHandlerException(Throwable cause) {
        super(cause);
    }

    public ThreadHandlerException(String message, Throwable cause) {
        super(message, cause);
    }

}

/*
 * Copyright 2008 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl1.php
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.core.db.torque;

import java.io.PrintWriter;
import java.io.StringWriter;

import static org.kuali.core.db.torque.Loggable.*;

/**
 * Normally a StackTrace from a <code>{@link Throwable}</code> is not a
 * <code>{@link Loggable}</code>. Since <code>{@link Loggable}</code> instances 
 * automatically report stack when logging, and can specify verbosity for the
 * Stacktrace report, it is important that it be a <code>{@link Loggable}</code>
 * itself.
 * 
 */
public class LoggableStackTrace implements Loggable {
    
    private StackTraceElement[] stackTrace;
    
    public LoggableStackTrace(StackTraceElement[] stackTrace) {
        setStackTrace(stackTrace);
    }

    /**
     * @see org.kuali.core.db.torque.Loggable#getLogMessage(java.lang.Object[])
     */
    public String getLogMessage(Object... objs) {
        if (objs.length < 1) {
            return null;
        }
        
        Integer stackTraceLevel = 0;
        
        if (!(objs[0] instanceof Integer)) {
            return null;
        }
        
        stackTraceLevel = (Integer) objs[0];
                
        StringWriter retval = new StringWriter();
        
        if (stackTraceLevel == UBER_TRACING) {
            Throwable throwable = new Throwable();
            throwable.setStackTrace(getStackTrace());
            throwable.printStackTrace(new PrintWriter(retval));
        }
        
        return retval.toString();
    }

    public StackTraceElement[] getStackTrace() {
        return stackTrace;
    }

    public void setStackTrace(StackTraceElement[] stackTrace) {
        this.stackTrace = stackTrace;
    }

    /**
     * This doesn't really apply for the <code>{@link LoggableStackTrace}</code>, so it automatically defaults to not logged; otherwise,
     * we would hit inifinite recursion really quick.
     * 
     * @see org.kuali.core.db.torque.Loggable#getStackOutputLevel()
     */
    public int getStackOutputLevel() {
        return NO_TRACING;
    }

}

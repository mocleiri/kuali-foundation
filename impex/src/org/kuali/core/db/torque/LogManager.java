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

import org.apache.commons.logging.Log;
import static org.apache.commons.logging.LogFactory.getLog;

import static org.kuali.core.db.torque.Loggable.*;

/**
 * Handles logging. Will only log objects that are prebuilt for logging (ie., implements <code>{@link Loggable}</code>.)
 */
public class LogManager {
    
    /**
     * @param e
     * @param objs
     */
    public static void exception(Exception e, Object ... objs) {
        warn(new ThrownLoggable(e), objs);
        
    }
   
    /**
     * 
     * This method...
     * @param e
     * @param objs
     */
    public static void error(Error e, Object ... objs) {
        error(new ThrownLoggable(e), objs);
    }
   
    /**
     * Logs a given <code>{@link Loggable}</code> instance with parameterized objects.
     * 
     * @param loggableObj
     * @param objs
     */
    public static void debug(Loggable loggableObj, Object ... objs) {
        Log log = getLog(loggableObj.getClass());
        if (log.isDebugEnabled()) {
            log.debug(loggableObj.getLogMessage(objs));
        }
    }
    
    /**
     * Logs a given <code>{@link Loggable}</code> instance with parameterized objects.
     * 
     * @param loggableObj
     * @param objs
     */
    public static void warn(Loggable loggableObj, Object ... objs) {
        Log log = getLog(loggableObj.getClass());
        if (log.isWarnEnabled()) {
            log.warn(loggableObj.getLogMessage(objs));
            
            if (loggableObj.getStackOutputLevel() != NO_TRACING) {
                warn(new LoggableStackTrace(new Throwable().getStackTrace()), new Integer(loggableObj.getStackOutputLevel()));
            }
        }
    }

    /**
     * Logs a given <code>{@link Loggable}</code> instance with parameterized objects.
     * 
     * @param loggableObj
     * @param objs
     */
    public static void info(Loggable loggableObj, Object ... objs) {
        Log log = getLog(loggableObj.getClass());
        if (log.isInfoEnabled()) {
            log.info(loggableObj.getLogMessage(objs));
        }
    }

    /**
     * Logs a given <code>{@link Loggable}</code> instance with parameterized objects.
     * 
     * @param loggableObj
     * @param objs
     */
    public static void trace(Loggable loggableObj, Object ... objs) {
        Log log = getLog(loggableObj.getClass());
        if (log.isTraceEnabled()) {
            log.trace(loggableObj.getLogMessage(objs));
        }
    }

    /**
     * Logs a given <code>{@link Loggable}</code> instance with parameterized objects.
     * 
     * @param loggableObj
     * @param objs
     */
    public static void error(Loggable loggableObj, Object ... objs) {
        Log log = getLog(loggableObj.getClass());
        if (log.isErrorEnabled()) {
            log.error(loggableObj.getLogMessage(objs));
        }
    }

    /**
     * Logs a given <code>{@link Loggable}</code> instance with parameterized objects.
     * 
     * @param loggableObj
     * @param objs
     */
    public static void fatal(Loggable loggableObj, Object ... objs) {
        Log log = getLog(loggableObj.getClass());
        if (log.isFatalEnabled()) {
            log.fatal(loggableObj.getLogMessage(objs));
        }
    }

    /**
     * Wrapper class to log <code>{@link Exception}</code> instances and <code>{@link Error}</code> instances.
     * 
     * @see Exception
     * @see Error
     * @see Throwable
     */
    public static class ThrownLoggable implements Loggable {
        private Throwable throwable;
        
        public ThrownLoggable(Throwable t) {
            this.throwable = t;
        }

        public Throwable getThrowable() {
            return throwable;
        }

        public void setThrowable(Throwable throwable) {
            this.throwable = throwable;
        }

        /**
         * 
         * @see org.kuali.core.db.torque.Loggable#getLogMessage(java.lang.Object[])
         */
        public String getLogMessage(Object ... objs) {
            StringBuffer retval = new StringBuffer("\n***********************************************************************\n");
            
            if (getThrowable() instanceof Exception) {
                retval.append("* !EXCEPTION!\n");
            }
            else if (getThrowable() instanceof Error) {
                retval.append("* !ERROR!\n");
            }
            
            retval.append("* Type: ").append(getThrowable().getClass().getSimpleName())
                .append("\n* Message: ").append(getThrowable().getMessage()).append("\n")
                .append("***********************************************************************\n");
            
            return retval.toString();
        }

        /**
         * 
         * @see org.kuali.core.db.torque.Loggable#getStackOutputLevel()
         */
        public int getStackOutputLevel() {
            return NO_TRACING;
        }
    }
}


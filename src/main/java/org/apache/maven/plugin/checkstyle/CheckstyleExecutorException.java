package org.apache.maven.plugin.checkstyle;

/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

/**
 * @author <a href="mailto:olamy@apache.org">olamy</a>
 * @since 2.5
 * @version $Id: CheckstyleExecutorException.java 907329 2010-02-06 22:28:11Z olamy $
 */
public class CheckstyleExecutorException
    extends Exception
{

    /**
     * 
     */
    public CheckstyleExecutorException()
    {
        // nothing
    }

    /**
     * @param message
     */
    public CheckstyleExecutorException( String message )
    {
        super( message );
    }

    /**
     * @param cause
     */
    public CheckstyleExecutorException( Throwable cause )
    {
        super( cause );
    }

    /**
     * @param message
     * @param cause
     */
    public CheckstyleExecutorException( String message, Throwable cause )
    {
        super( message, cause );
    }

}

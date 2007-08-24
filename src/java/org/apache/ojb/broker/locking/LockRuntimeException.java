package org.apache.ojb.broker.locking;

/* Copyright 2002-2005 The Apache Software Foundation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.apache.ojb.broker.OJBRuntimeException;

/**
 * Exception was thrown when unexpected behavior in conjunction with locking
 * was detected. 
 *
 * @version $Id: LockRuntimeException.java,v 1.1 2007-08-24 22:17:41 ewestfal Exp $
 */
public class LockRuntimeException extends OJBRuntimeException
{
    public LockRuntimeException()
    {
    }

    public LockRuntimeException(String msg)
    {
        super(msg);
    }

    public LockRuntimeException(Throwable cause)
    {
        super(cause);
    }

    public LockRuntimeException(String msg, Throwable cause)
    {
        super(msg, cause);
    }
}

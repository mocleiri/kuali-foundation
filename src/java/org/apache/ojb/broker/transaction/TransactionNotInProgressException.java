package org.apache.ojb.broker.transaction;

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
 *
 *
 * @author <a href="mailto:arminw@apache.org">Armin Waibel</a>
 * @version $Id: TransactionNotInProgressException.java,v 1.1 2007-08-24 22:17:39 ewestfal Exp $
 */
public class TransactionNotInProgressException extends OJBRuntimeException
{
    public TransactionNotInProgressException()
    {
    }

    public TransactionNotInProgressException(String msg)
    {
        super(msg);
    }

    public TransactionNotInProgressException(Throwable cause)
    {
        super(cause);
    }

    public TransactionNotInProgressException(String msg, Throwable cause)
    {
        super(msg, cause);
    }
}

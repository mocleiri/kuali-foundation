package org.apache.ojb.otm.core;

/* Copyright 2003-2005 The Apache Software Foundation
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

import org.apache.commons.lang.exception.NestableRuntimeException;
import org.apache.ojb.otm.lock.LockingException;

public class LockingPassthruException extends NestableRuntimeException
{
    /**
	 * Constructor for LockingPassthruException.
	 * @param arg0
	 */
	public LockingPassthruException(LockingException cause)
	{
		super(cause);
	}

	
	/**
	 * @see java.lang.Throwable#getCause()
	 */
	public LockingException getLockingException ()
	{
		return (LockingException) getCause();
	}

}

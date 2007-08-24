package org.apache.ojb.otm.copy;

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

import org.apache.ojb.broker.PersistenceBroker;

public interface ObjectCopyStrategy
{

    /**
     *
     * Make a copy of the given object
     *
     * @param obj object to be copied
     * @return Object the copy of the object
     */
    public Object copy(Object obj, PersistenceBroker broker);

}

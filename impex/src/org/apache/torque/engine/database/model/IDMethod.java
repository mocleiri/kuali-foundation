package org.apache.torque.engine.database.model;

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
 * Interface for various ID retrieval method types.
 * This currently includes native, ID broker, and none.
 *
 * @author <a href="mailto:dlr@collab.net">Daniel Rall</a>
 * @version $Id: IDMethod.java,v 1.1.4.2 2008-03-12 09:24:59 lprzybyl Exp $
 */
public interface IDMethod
{
    /**
     * Key generation via database-specific ID method.
     * For example, this would be auto-increment for MySQL,
     * sequence for Oracle, etc.
     */
    String NATIVE = "native";

    /**
     * Key generation via the IDBroker table.
     */
    String ID_BROKER = "idbroker";

    /**
     * No RDBMS key generation (keys may be generated by the
     * application).
     */
    String NO_ID_METHOD = "none";
}

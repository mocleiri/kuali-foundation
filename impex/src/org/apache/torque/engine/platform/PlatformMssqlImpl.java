package org.apache.torque.engine.platform;

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

import org.apache.torque.engine.database.model.Domain;
import org.apache.torque.engine.database.model.SchemaType;

/**
 * MS SQL Platform implementation.
 *
 * @author <a href="mailto:mpoeschl@marmot.at">Martin Poeschl</a>
 * @author <a href="mailto:greg.monroe@dukece.com">Greg Monroe</a>
 * @version $Id: PlatformMssqlImpl.java,v 1.1 2007-10-21 07:57:27 abyrne Exp $
 */
public class PlatformMssqlImpl extends PlatformDefaultImpl
{
    /**
     * Default constructor.
     */
    public PlatformMssqlImpl()
    {
        super();
        initialize();
    }

    /**
     * Initializes db specific domain mapping.
     */
    private void initialize()
    {
        setSchemaDomainMapping(new Domain(SchemaType.INTEGER, "INT"));
        setSchemaDomainMapping(new Domain(SchemaType.BOOLEANINT, "INT"));
        setSchemaDomainMapping(new Domain(SchemaType.DOUBLE, "FLOAT"));
        setSchemaDomainMapping(new Domain(SchemaType.LONGVARCHAR, "TEXT"));
        setSchemaDomainMapping(new Domain(SchemaType.DATE, "DATETIME"));
        setSchemaDomainMapping(new Domain(SchemaType.TIME, "DATETIME"));
        setSchemaDomainMapping(new Domain(SchemaType.TIMESTAMP, "DATETIME"));
        setSchemaDomainMapping(new Domain(SchemaType.BINARY, "BINARY(7132)"));
        setSchemaDomainMapping(new Domain(SchemaType.VARBINARY, "IMAGE"));
        setSchemaDomainMapping(new Domain(SchemaType.LONGVARBINARY, "IMAGE"));
        setSchemaDomainMapping(new Domain(SchemaType.BLOB, "IMAGE"));
        setSchemaDomainMapping(new Domain(SchemaType.CLOB, "TEXT"));
    }

    /**
     * @see Platform#getMaxColumnNameLength()
     */
    public int getMaxColumnNameLength()
    {
        return 30;
    }

    /**
     * @return Explicitly returns <code>NULL</code> if null values are
     * allowed (as recomended by Microsoft).
     * @see Platform#getNullString(boolean)
     */
    public String getNullString(boolean notNull)
    {
        return (notNull ? "NOT NULL" : "NULL");
    }

}

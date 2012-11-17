package org.apache.torque.engine.platform;

/*
 * Licensed to the Apache Software Foundation (ASF) under one or more contributor license agreements. See the NOTICE
 * file distributed with this work for additional information regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.lang.StringUtils;
import org.apache.torque.engine.database.model.Domain;
import org.apache.torque.engine.database.model.SchemaType;

/**
 * H2 Platform implementation.
 * 
 * @author Travis Schneeberger
 */
public class PlatformH2Impl extends PlatformDefaultImpl {

    public PlatformH2Impl() {
        super();
        initialize();
    }

    /**
     * Initializes db specific domain mapping.
     */
    private void initialize() {
        //no schema mapping for: IDENTITY, UUID, IDENTITY
        setSchemaDomainMapping(new Domain(SchemaType.INTEGER, "INT", "10", "0"));
        setSchemaDomainMapping(new Domain(SchemaType.BOOLEANCHAR, "BOOLEAN"));
        setSchemaDomainMapping(new Domain(SchemaType.BOOLEANINT, "BOOLEAN"));
        setSchemaDomainMapping(new Domain(SchemaType.TINYINT, "TINYINT", "3", "0"));
        setSchemaDomainMapping(new Domain(SchemaType.SMALLINT, "SMALLINT", "5", "0"));
        setSchemaDomainMapping(new Domain(SchemaType.BIGINT, "BIGINT", "20", "0"));
        setSchemaDomainMapping(new Domain(SchemaType.DECIMAL, "DECIMAL"));
        setSchemaDomainMapping(new Domain(SchemaType.DOUBLE, "DOUBLE"));
        setSchemaDomainMapping(new Domain(SchemaType.REAL, "REAL"));
        setSchemaDomainMapping(new Domain(SchemaType.TIME, "TIME"));
        //hack to get oracle dates into TIMESTAMPs
        //setSchemaDomainMapping(new Domain(SchemaType.DATE, "DATE"));
        setSchemaDomainMapping(new Domain(SchemaType.DATE, "TIMESTAMP"));
        setSchemaDomainMapping(new Domain(SchemaType.TIMESTAMP, "TIMESTAMP"));
        setSchemaDomainMapping(new Domain(SchemaType.BINARY, "BINARY"));
        setSchemaDomainMapping(new Domain(SchemaType.OTHER, "OTHER"));
        setSchemaDomainMapping(new Domain(SchemaType.VARCHAR, "VARCHAR"));
        setSchemaDomainMapping(new Domain(SchemaType.CHAR, "CHAR"));
        setSchemaDomainMapping(new Domain(SchemaType.BLOB, "BLOB"));
        setSchemaDomainMapping(new Domain(SchemaType.CLOB, "CLOB"));
        setSchemaDomainMapping(new Domain(SchemaType.ARRAY, "ARRAY"));
    }

    @Override
    public boolean isSpecialDefault(final String defaultValue) {
        final String upperDefaultValue = defaultValue.toUpperCase();
        return upperDefaultValue.contains("SYSDATE") || upperDefaultValue.contains("RANDOM_UUID()");
    }

    @Override
    public String filterInvalidDefaultValues(final String defaultValue) {
        if (defaultValue == null) {
            return null;
        }

        final String upperDefaultValue = defaultValue.toUpperCase();
        //SYS_GUID() is not valid in H2.  This must be mapped to RANDOM_UUID()
        if (upperDefaultValue.contains("SYS_GUID()")) {
            return "RANDOM_UUID()";
        }
        return defaultValue;
    }

    @Override
    public String getAutoIncrement() {
        return "AUTO_INCREMENT";
    }

    @Override
    public String getNativeIdMethod() {
        return Platform.SEQUENCE;
    }
}

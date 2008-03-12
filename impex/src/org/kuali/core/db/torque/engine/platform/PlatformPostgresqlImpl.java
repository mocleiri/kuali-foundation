package org.kuali.core.db.torque.engine.platform;

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
import org.apache.torque.engine.platform.Platform;
import org.apache.torque.engine.platform.PlatformDefaultImpl;

/**
 * Postgresql Platform implementation.
 * 
 * @author <a href="mailto:mpoeschl@marmot.at">Martin Poeschl</a>
 * @version $Id: PlatformPostgresqlImpl.java,v 1.1 2007/10/21 07:57:27 abyrne
 *          Exp $
 */
public class PlatformPostgresqlImpl extends PlatformDefaultImpl {
	/**
	 * Default constructor.
	 */
	public PlatformPostgresqlImpl() {
		super();
		initialize();
	}

	/**
	 * Initializes db specific domain mapping.
	 */
	private void initialize() {
		setSchemaDomainMapping(new Domain(SchemaType.BIT, "BOOLEAN"));
		setSchemaDomainMapping(new Domain(SchemaType.TINYINT, "INT2"));
		setSchemaDomainMapping(new Domain(SchemaType.SMALLINT, "INT2"));
		setSchemaDomainMapping(new Domain(SchemaType.BIGINT, "INT8"));
		setSchemaDomainMapping(new Domain(SchemaType.REAL, "FLOAT"));
		setSchemaDomainMapping(new Domain(SchemaType.BOOLEANCHAR, "CHAR"));
		setSchemaDomainMapping(new Domain(SchemaType.BOOLEANINT, "INT2"));
		setSchemaDomainMapping(new Domain(SchemaType.DOUBLE, "DOUBLE PRECISION"));
		setSchemaDomainMapping(new Domain(SchemaType.LONGVARCHAR, "TEXT"));
		setSchemaDomainMapping(new Domain(SchemaType.BINARY, "BYTEA"));
		setSchemaDomainMapping(new Domain(SchemaType.VARBINARY, "BYTEA"));
		setSchemaDomainMapping(new Domain(SchemaType.LONGVARBINARY, "BYTEA"));
		setSchemaDomainMapping(new Domain(SchemaType.BLOB, "BYTEA"));
		setSchemaDomainMapping(new Domain(SchemaType.CLOB, "TEXT"));
	}

	/**
	 * @see Platform#getNativeIdMethod()
	 */
	public String getNativeIdMethod() {
		return Platform.SEQUENCE;
	}

	/**
	 * @see Platform#getAutoIncrement()
	 */
	public String getAutoIncrement() {
		return "";
	}

	/**
	 * @see Platform#hasScale(String) TODO collect info for all platforms
	 */
	public boolean hasScale(String sqlType) {
		if ("INT2".equalsIgnoreCase(sqlType)
				|| "TEXT".equalsIgnoreCase(sqlType)) {
			return false;
		}
		return true;
	}

	/**
	 * @see Platform#hasSize(String) TODO collect info for all platforms
	 */
	public boolean hasSize(String sqlType) {
		if ("INT2".equalsIgnoreCase(sqlType)
				|| "TEXT".equalsIgnoreCase(sqlType)) {
			return false;
		}
		return true;
	}
}

/**
 * Copyright 2010-2013 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl2.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.common.jdbc.context;

/**
 * @deprecated
 */
@Deprecated
public class SqlExecutionContext {

	String key;
	String group;
	SqlMode mode;
	String context;

	public SqlExecutionContext() {
		this(null, null);
	}

	public SqlExecutionContext(String group, SqlMode mode) {
		this(null, group, mode, null);
	}

	public SqlExecutionContext(String key, String group, SqlMode mode) {
		this(key, group, mode, null);
	}

	public SqlExecutionContext(String key, String group, SqlMode mode, String context) {
		super();
		this.key = key;
		this.group = group;
		this.mode = mode;
		this.context = context;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public SqlMode getMode() {
		return mode;
	}

	public void setMode(SqlMode mode) {
		this.mode = mode;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}
}

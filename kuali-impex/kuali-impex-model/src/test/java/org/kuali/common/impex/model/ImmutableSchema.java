/**
 * Copyright 2011 The Kuali Foundation Licensed under the
 * Educational Community License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may
 * obtain a copy of the License at
 *
 * http://www.osedu.org/licenses/ECL-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package org.kuali.common.impex.model;

import java.util.Collections;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.kuali.common.util.Assert;
import org.kuali.common.util.CollectionUtils;

@XmlRootElement(name = "schema")
@XmlAccessorType(XmlAccessType.PROPERTY)
public final class ImmutableSchema {

	@XmlElement
	private final String name;

	@XmlElement(name = "table")
	private final List<ImmutableTable> tables;

	@SuppressWarnings("unused")
	private ImmutableSchema() {
		this(null, null, false);
	}

	private ImmutableSchema(String name, List<ImmutableTable> tables, boolean validate) {
		if (validate) {
			Assert.noBlanks(name);
			Assert.noNulls(tables);
			this.tables = Collections.unmodifiableList(tables);
		} else {
			this.tables = tables;
		}
		this.name = name;
	}

	public ImmutableSchema(String name, ImmutableTable table) {
		this(name, CollectionUtils.singletonList(table));
	}

	public ImmutableSchema(String name, List<ImmutableTable> tables) {
		this(name, tables, true);
	}

	public String getName() {
		return name;
	}

	public List<ImmutableTable> getTables() {
		return tables;
	}

}

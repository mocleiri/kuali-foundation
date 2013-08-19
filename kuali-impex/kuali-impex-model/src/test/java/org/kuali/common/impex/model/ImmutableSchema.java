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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.kuali.common.util.Assert;
import org.kuali.common.util.nullify.NullUtils;

@XmlRootElement(name = "schema")
@XmlAccessorType(XmlAccessType.PROPERTY)
public final class ImmutableSchema {

	@XmlElement
	private final String name;

	@XmlElement(name = "table")
	private final List<ImmutableTable> tables;

	@SuppressWarnings("unused")
	private ImmutableSchema() {
		this(NullUtils.NONE, new ArrayList<ImmutableTable>());
	}

	public ImmutableSchema(String name, List<ImmutableTable> tables) {
		Assert.noBlanks(name);
		Assert.noNulls(tables);
		this.name = name;
		this.tables = tables;
	}

	public String getName() {
		return name;
	}

	public List<ImmutableTable> getTables() {
		return Collections.unmodifiableList(tables);
	}

}

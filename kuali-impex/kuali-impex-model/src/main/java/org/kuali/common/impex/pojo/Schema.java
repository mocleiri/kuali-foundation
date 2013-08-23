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

package org.kuali.common.impex.pojo;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class Schema {

	String name;
	List<Table> tables = new ArrayList<Table>();
	List<Sequence> sequences = new ArrayList<Sequence>();
	List<View> views = new ArrayList<View>();
	List<ForeignKey> foreignKeys = new ArrayList<ForeignKey>();

	public Schema() {
		super();
	}

	@XmlElement
	public String getName() {
		return name;
	}

	@XmlElement(name = "table")
	public List<Table> getTables() {
		return tables;
	}

	@XmlElement(name = "foreignKey")
	public List<ForeignKey> getForeignKeys() {
		return foreignKeys;
	}

	@XmlElement(name = "sequence")
	public List<Sequence> getSequences() {
		return sequences;
	}

	@XmlElement(name = "view")
	public List<View> getViews() {
		return views;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setForeignKeys(List<ForeignKey> foreignKeys) {
		this.foreignKeys = foreignKeys;
	}

	public void setSequences(List<Sequence> sequences) {
		this.sequences = sequences;
	}

	public void setTables(List<Table> tables) {
		this.tables = tables;
	}

	public void setViews(List<View> views) {
		this.views = views;
	}
}

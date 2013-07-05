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
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import org.kuali.common.util.CollectionUtils;

/**
 * This class represents any named connection between columns
 */
@XmlAccessorType(XmlAccessType.PROPERTY)
public abstract class Constraint implements NamedElement {

	String name;
	List<String> columnNames = new ArrayList<String>();

	/**
	 * This is a copy constructor. It must create a perfect, deep, copy of this object
	 */
	public Constraint(Constraint constraint) {
		this.name = constraint.getName();
		this.columnNames = new ArrayList<String>(CollectionUtils.toEmptyList(constraint.getColumnNames()));
	}

	public Constraint() {
		super();
	}

	public Constraint(List<String> columnNames, String name) {
		this.columnNames = columnNames;
		this.name = name;
	}

	@XmlElement(name = "column")
	public List<String> getColumnNames() {
		return columnNames;
	}

	@Override
	@XmlAttribute
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setColumnNames(List<String> columnNames) {
		this.columnNames = columnNames;
	}
}

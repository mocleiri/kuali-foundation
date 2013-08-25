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

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.kuali.common.util.nullify.NullUtils;
import org.kuali.common.util.xml.jaxb.adapter.ImmutableListAdapter;
import org.kuali.common.util.xml.jaxb.adapter.OmitFalseAdapter;

import com.google.common.collect.ImmutableList;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public final class Index implements NamedElement {

	@XmlAttribute
	@XmlJavaTypeAdapter(OmitFalseAdapter.class)
	private final Boolean unique;

	@XmlAttribute
	private final String name;

	@XmlElement
	@XmlJavaTypeAdapter(ImmutableListAdapter.class)
	private final List<String> columns;

	@SuppressWarnings("unused")
	private Index() {
		this(NullUtils.NONE, NullUtils.NONE);
	}

	public Index(String name, List<String> columns) {
		this(name, false, columns);
	}

	public Index(String name, String column) {
		this(name, false, ImmutableList.of(column));
	}

	public Index(String name, String... columns) {
		this(name, false, ImmutableList.copyOf(columns));
	}

	public Index(String name, boolean unique, String... columns) {
		this(name, unique, ImmutableList.copyOf(columns));
	}

	public Index(String name, boolean unique, String column) {
		this(name, unique, ImmutableList.of(column));
	}

	public Index(String name, boolean unique, List<String> columns) {
		this.name = name;
		this.columns = columns;
		this.unique = unique;
	}

	public boolean isUnique() {
		return unique;
	}

	public Boolean getUnique() {
		return unique;
	}

	@Override
	public String getName() {
		return name;
	}

	public List<String> getColumns() {
		return columns;
	}

}

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

import org.kuali.common.util.Assert;
import org.kuali.common.util.xml.jaxb.adapter.OmitFalseAdapter;
import org.springframework.util.CollectionUtils;

import com.google.common.collect.ImmutableList;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public final class Index implements NamedElement {

	private static final boolean DEFAULT_UNIQUE = false;

	@XmlAttribute
	@XmlJavaTypeAdapter(OmitFalseAdapter.class)
	private final Boolean unique;

	@XmlAttribute
	private final String name;

	@XmlElement
	private final List<String> columns;

	@SuppressWarnings("unused")
	private Index() {
		this.name = null;
		this.columns = null;
		this.unique = DEFAULT_UNIQUE;
	}

	public Index(String name, List<String> columns) {
		this(name, DEFAULT_UNIQUE, columns);
	}

	public Index(String name, String column) {
		this(name, DEFAULT_UNIQUE, ImmutableList.of(column));
	}

	public Index(String name, String... columns) {
		this(name, DEFAULT_UNIQUE, ImmutableList.copyOf(columns));
	}

	public Index(String name, boolean unique, String... columns) {
		this(name, unique, ImmutableList.copyOf(columns));
	}

	public Index(String name, boolean unique, String column) {
		this(name, unique, ImmutableList.of(column));
	}

	public Index(String name, boolean unique, List<String> columns) {
		Assert.noBlanks(name); // Name can't be blank
		Assert.isFalse(CollectionUtils.isEmpty(columns)); // The column list can't be null and must contain at least one element
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

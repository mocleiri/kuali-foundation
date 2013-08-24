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
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.kuali.common.util.Assert;
import org.kuali.common.util.ListUtils;
import org.kuali.common.util.xml.jaxb.adapter.ImmutableListAdapter;
import org.springframework.util.CollectionUtils;

/**
 * This class represents any named connection between columns
 */
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class Constraint implements NamedElement {

	@XmlAttribute
	private final String name;

	@XmlElement
	@XmlJavaTypeAdapter(ImmutableListAdapter.class)
	private final List<String> columns;

	public Constraint(String name, List<String> columns) {
		Assert.noBlanks(name);
		Assert.isFalse(CollectionUtils.isEmpty(columns));
		this.columns = ListUtils.newImmutableArrayList(columns);
		this.name = name;
	}

	public List<String> getColumns() {
		return columns;
	}

	@Override
	public String getName() {
		return name;
	}

}

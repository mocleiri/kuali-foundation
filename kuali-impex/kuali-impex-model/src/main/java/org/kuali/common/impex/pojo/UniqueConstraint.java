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
import javax.xml.bind.annotation.XmlRootElement;

import org.kuali.common.util.nullify.NullUtils;
import org.kuali.common.util.xml.jaxb.XmlBind;

import com.google.common.collect.ImmutableList;

/**
 * A unique constraint
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlBind(classes = { Constraint.class })
public final class UniqueConstraint extends Constraint {

	@SuppressWarnings("unused")
	private UniqueConstraint() {
		this(NullUtils.NONE, NullUtils.NONE);
	}

	public UniqueConstraint(String name, String column) {
		this(name, ImmutableList.of(column));
	}

	public UniqueConstraint(String name, List<String> column) {
		super(name, column);
	}

}

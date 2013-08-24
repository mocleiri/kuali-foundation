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

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import org.kuali.common.util.Assert;
import org.kuali.common.util.nullify.NullUtils;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Sequence implements NamedElement {

	@XmlAttribute
	private final String name;

	@XmlAttribute
	private final String startValue;

	public Sequence() {
		this(NullUtils.NONE, NullUtils.NONE);
	}

	public Sequence(String name, String startValue) {
		Assert.noBlanks(name, startValue);
		this.name = name;
		this.startValue = startValue;
	}

	@Override
	public String getName() {
		return name;
	}

	public String getStartValue() {
		return startValue;
	}

}

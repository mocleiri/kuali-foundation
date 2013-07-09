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

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

/**
 * Bean that contains size information of a column data type
 */
@XmlAccessorType(XmlAccessType.PROPERTY)
public class Size {

	public static final Boolean DEFAULT_SCALE_SET = false;

	Boolean scaleSet = DEFAULT_SCALE_SET;
	Integer value;
	Integer scale;

	/**
	 * This is a copy constructor. It must create a perfect, deep, copy of this object
	 */
	public Size(Size size) {
		super();
		this.value = size.getValue();
		this.scale = size.getScale();
		this.scaleSet = size.isScaleSet();
	}

	public Size() {
		this(null, null);
	}

	public Size(Integer size) {
		this(size, null);
	}

	public Size(Integer size, Integer scale) {
		this.value = size;
		this.scale = scale;
		this.scaleSet = (this.scale != null);
	}

	@XmlAttribute
	public Integer getValue() {
		return value;
	}

	@XmlAttribute
	public Integer getScale() {
		return scale;
	}

	@XmlAttribute
	public Boolean isScaleSet() {
		return scaleSet;
	}

	public void setScale(Integer scale) {
		this.scale = scale;
	}

	public void setScaleSet(Boolean scaleSet) {
		this.scaleSet = scaleSet;
	}

	public void setValue(Integer size) {
		this.value = size;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		if (!(obj instanceof Size)) {
			return false;
		}

		Size other = (Size) obj;

		if (!getValue().equals(other.getValue())) {
			return false;
		}

		if (isScaleSet() != other.isScaleSet()) {
			return false;
		}

		if (isScaleSet() && !getScale().equals(other.getScale())) {
			return false;
		}

		return true;
	}

}

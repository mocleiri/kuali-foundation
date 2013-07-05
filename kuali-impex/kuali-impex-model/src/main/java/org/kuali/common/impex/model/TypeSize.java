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
public class TypeSize {

	public static final Boolean DEFAULT_SCALE_SET = false;

	Integer size;
	Integer scale;
	Boolean scaleSet = DEFAULT_SCALE_SET;

	public TypeSize() {
		this(null, null);
	}

	public TypeSize(Integer size) {
		this.size = size;
		this.scale = null;
		scaleSet = DEFAULT_SCALE_SET;
	}

	public TypeSize(Integer size, Integer scale) {
		this.size = size;
		this.scale = scale;
		this.scaleSet = (this.scale != null);
	}

	@XmlAttribute
	public Integer getSize() {
		return size;
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

	public void setSize(Integer size) {
		this.size = size;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		if (!(obj instanceof TypeSize)) {
			return false;
		}

		TypeSize other = (TypeSize) obj;

		if (!getSize().equals(other.getSize())) {
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

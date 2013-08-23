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
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.kuali.common.util.xml.jaxb.OmitFalseAdapter;
import org.kuali.common.util.xml.jaxb.OmitNegativeOneAdapter;
import org.springframework.util.Assert;

@XmlAccessorType(XmlAccessType.FIELD)
public final class DataTypeSize {

	public static final boolean DEFAULT_SCALED = false;
	public static final int NO_SCALE = -1;

	@XmlAttribute
	private final Integer value;

	@XmlAttribute
	@XmlJavaTypeAdapter(OmitFalseAdapter.class)
	private final Boolean scaled;

	@XmlAttribute
	@XmlJavaTypeAdapter(OmitNegativeOneAdapter.class)
	private final Integer scale;

	@SuppressWarnings("unused")
	private DataTypeSize() {
		this(0);
	}

	public DataTypeSize(int size) {
		this(size, DEFAULT_SCALED, NO_SCALE);
	}

	public DataTypeSize(int size, boolean scaled, int scale) {
		Assert.isTrue(size >= 0, "size is negative");
		if (scaled) {
			Assert.isTrue(scale >= 0, "scale is negative");
		} else {
			Assert.isTrue(scale == NO_SCALE, "scale must be set to -1 if scaled is false");
		}
		this.value = size;
		this.scale = scale;
		this.scaled = scaled;
	}

	public boolean isScaled() {
		return scaled;
	}

	public int getValue() {
		return value;
	}

	public int getScale() {
		return scale;
	}

}

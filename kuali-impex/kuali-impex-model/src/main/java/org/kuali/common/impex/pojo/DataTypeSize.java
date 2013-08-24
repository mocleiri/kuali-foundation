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

import org.kuali.common.util.xml.jaxb.adapter.OmitOptionalIntegerAdapter;
import org.springframework.util.Assert;

import com.google.common.base.Optional;

@XmlAccessorType(XmlAccessType.FIELD)
public final class DataTypeSize {

	@XmlAttribute
	private final Integer value; // Integer instead of int for for JAXB and it's XmlAdapter API

	@XmlAttribute
	@XmlJavaTypeAdapter(OmitOptionalIntegerAdapter.class)
	private final Optional<Integer> scale;

	@SuppressWarnings("unused")
	private DataTypeSize() {
		this(0);
	}

	public DataTypeSize(int size) {
		this(size, Optional.<Integer> absent());
	}

	public DataTypeSize(int size, int scale) {
		this(size, Optional.<Integer> of(scale));
	}

	private DataTypeSize(int size, Optional<Integer> scale) {
		Assert.isTrue(size >= 0, "size is negative");
		Assert.notNull(scale);
		if (scale.isPresent()) {
			Assert.isTrue(scale.get() >= 0, "scale is negative");
		}
		this.value = size;
		this.scale = scale;
	}

	public int getValue() {
		return value;
	}

	public Optional<Integer> getScale() {
		return scale;
	}

}

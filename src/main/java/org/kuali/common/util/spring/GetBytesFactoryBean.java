/**
 * Copyright 2010-2013 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl2.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.common.util.spring;

import org.kuali.common.util.FormatUtils;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.util.Assert;

public class GetBytesFactoryBean implements FactoryBean<Long> {

	String size;

	@Override
	public Long getObject() {
		Assert.notNull(size, "size is null");
		return FormatUtils.getBytes(size);
	}

	@Override
	public Class<Long> getObjectType() {
		return Long.class;
	}

	@Override
	public boolean isSingleton() {
		return false;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

}

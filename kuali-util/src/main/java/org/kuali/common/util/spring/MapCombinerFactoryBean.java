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

import java.util.List;
import java.util.Map;

import org.kuali.common.util.CollectionUtils;
import org.springframework.beans.factory.FactoryBean;

public class MapCombinerFactoryBean<K, V> implements FactoryBean<Map<K, V>> {

	List<Map<K, V>> listOfMaps;

	@Override
	public Map<K, V> getObject() throws Exception {
		return CollectionUtils.combineMaps(listOfMaps);
	}

	@Override
	public Class<?> getObjectType() {
		return Map.class;
	}

	@Override
	public boolean isSingleton() {
		return false;
	}

	public List<Map<K, V>> getListOfMaps() {
		return listOfMaps;
	}

	public void setListOfMaps(List<Map<K, V>> listOfMaps) {
		this.listOfMaps = listOfMaps;
	}

}

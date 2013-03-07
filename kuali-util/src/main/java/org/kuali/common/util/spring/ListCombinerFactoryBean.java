/**
 * Copyright 2010-2012 The Kuali Foundation
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

import org.kuali.common.util.CollectionUtils;
import org.springframework.beans.factory.FactoryBean;

public class ListCombinerFactoryBean<T> implements FactoryBean<List<T>> {

	List<List<T>> listOfLists;

	@Override
	public List<T> getObject() throws Exception {
		return CollectionUtils.combineLists(listOfLists);
	}

	@Override
	public Class<?> getObjectType() {
		return List.class;
	}

	@Override
	public boolean isSingleton() {
		return false;
	}

	public List<List<T>> getListOfLists() {
		return listOfLists;
	}

	public void setListOfLists(List<List<T>> listOfLists) {
		this.listOfLists = listOfLists;
	}

}

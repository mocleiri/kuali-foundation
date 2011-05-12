/*
 * Copyright 2011 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl1.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.rice.krms.impl.repository;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.kuali.rice.kns.service.BusinessObjectService;

/**
 * This is a description of what this class does - dseibert don't forget to fill this in. 
 * 
 * @author Kuali Rice Team (rice.collab@kuali.org)
 *
 */
public class AttibuteMapUtils {

    private KrmsAttributeDefinitionService krmsAttributeService;
	
	public static Map<String,String> convertAttributesToMap(Set<ActionAttributeBo> attributes){
		Map<String, String> map = new HashMap<String,String>();
		if (CollectionUtils.isEmpty(attributes)){
			return Collections.emptyMap();
		} else {
			for (ActionAttributeBo bo : attributes){
				
			}
		}
		return map;
	}
}

/*
 * Copyright 2005-2008 The Kuali Foundation
 * 
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
package org.kuali.rice.kew.docsearch.xml;

import java.util.Map;

import org.kuali.rice.kew.docsearch.SearchableAttribute;
import org.kuali.rice.kew.rule.XmlConfiguredAttribute;


/**
 * Interface representing attributes that are backed by xml.
 *
 * @author Kuali Rice Team (rice.collab@kuali.org)
 */
public interface GenericXMLSearchableAttribute extends SearchableAttribute, XmlConfiguredAttribute {

	public void setParamMap(Map paramMap);
	public Map getParamMap();
	
}

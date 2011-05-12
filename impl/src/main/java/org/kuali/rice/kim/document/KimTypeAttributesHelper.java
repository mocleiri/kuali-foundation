/*
 * Copyright 2007-2009 The Kuali Foundation
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
package org.kuali.rice.kim.document;

import org.apache.commons.lang.StringUtils;
import org.kuali.rice.core.util.AttributeSet;
import org.kuali.rice.kim.api.type.KimType;
import org.kuali.rice.kim.bo.impl.KimAttributes;
import org.kuali.rice.kim.bo.types.dto.AttributeDefinitionMap;
import org.kuali.rice.kim.service.KIMServiceLocatorInternal;
import org.kuali.rice.kim.service.KIMServiceLocatorWeb;
import org.kuali.rice.kim.service.support.KimTypeService;
import org.kuali.rice.kim.util.KimConstants;
import org.kuali.rice.kns.datadictionary.AttributeDefinition;
import org.kuali.rice.kns.datadictionary.KimDataDictionaryAttributeDefinition;
import org.kuali.rice.kns.datadictionary.KimNonDataDictionaryAttributeDefinition;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * This is a description of what this class does - bhargavp don't forget to fill this in. 
 * 
 * @author Kuali Rice Team (rice.collab@kuali.org)
 *
 */
public class KimTypeAttributesHelper implements Serializable {

	private KimType kimType;
	private transient KimTypeService kimTypeService;
	private List<? extends KimAttributes> attributes;
	private transient AttributeDefinitionMap definitions;
	private transient Map<String,Object> attributeEntry;

	public KimTypeAttributesHelper(KimType kimType){
		this.kimType = kimType;
	}

	/**
	 * @return the attributes
	 */
	public List<? extends KimAttributes> getAttributes() {
		return this.attributes;
	}

	/**
	 * @return the kimType
	 */
	public KimType getKimType() {
		return this.kimType;
	}

	public KimTypeService getKimTypeService(KimType kimType){
		if(this.kimTypeService==null){
	    	this.kimTypeService = KIMServiceLocatorWeb.getKimTypeService(kimType);
		}
		return this.kimTypeService;
	}
	
	public Map<String,Object> getAttributeEntry() {
		if(attributeEntry==null || attributeEntry.isEmpty())
			attributeEntry = KIMServiceLocatorInternal.getUiDocumentService().getAttributeEntries(getDefinitions());
		return attributeEntry;
	}

    public String getKimAttributeDefnId(AttributeDefinition definition){
    	if (definition instanceof KimDataDictionaryAttributeDefinition) {
    		return ((KimDataDictionaryAttributeDefinition)definition).getKimAttrDefnId();
    	} 
    	return ((KimNonDataDictionaryAttributeDefinition)definition).getKimAttrDefnId();
    }
    
	/**
	 * Returns an {@link AttributeDefinitionMap}
	 * 
	 * @return
	 */
	public AttributeDefinitionMap getDefinitions() {
		if (definitions == null || definitions.isEmpty()) {
	        KimTypeService kimTypeService = getKimTypeService(getKimType());
	        if(kimTypeService!=null)
	        	this.definitions = kimTypeService.getAttributeDefinitions(getKimType().getId());
		}
		return this.definitions;
	}

	public AttributeDefinitionMap getDefinitionsKeyedByAttributeName() {
		AttributeDefinitionMap definitionsKeyedBySortCode = getDefinitions();
		AttributeDefinitionMap returnValue = new AttributeDefinitionMap();
		if (definitionsKeyedBySortCode != null) {
			for (AttributeDefinition definition : definitionsKeyedBySortCode.values()) {
				returnValue.put(definition.getName(), definition);
			}
		}
		return returnValue;
	}
	
	public String getCommaDelimitedAttributesLabels(String commaDelimitedAttributesNamesList){
		String[] names = StringUtils.splitByWholeSeparator(commaDelimitedAttributesNamesList, KimConstants.KimUIConstants.COMMA_SEPARATOR);
		StringBuffer commaDelimitedAttributesLabels = new StringBuffer();
		for(String name: names){
			commaDelimitedAttributesLabels.append(getAttributeEntry().get(name.trim())+KimConstants.KimUIConstants.COMMA_SEPARATOR);
		}
        if(commaDelimitedAttributesLabels.toString().endsWith(KimConstants.KimUIConstants.COMMA_SEPARATOR))
        	commaDelimitedAttributesLabels.delete(commaDelimitedAttributesLabels.length()-KimConstants.KimUIConstants.COMMA_SEPARATOR.length(), commaDelimitedAttributesLabels.length());
        return commaDelimitedAttributesLabels.toString();
	}

	public AttributeDefinition getAttributeDefinition(String attributeName){
		return getDefinitionsKeyedByAttributeName().get(attributeName);
	}
	
	public String getAttributeValue(AttributeSet aSet, String attributeName){
		if(StringUtils.isEmpty(attributeName) || aSet==null) return null;
		for(String attributeNameKey: aSet.keySet()){
			if(attributeName.equals(attributeNameKey))
				return aSet.get(attributeNameKey);
		}
		return null;
	}
}

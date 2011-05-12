/*
 * Copyright 2005-2007 The Kuali Foundation
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
package org.kuali.rice.kns.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.kuali.rice.core.api.config.property.ConfigurationService;
import org.kuali.rice.core.util.ClassLoaderUtils;
import org.kuali.rice.core.web.format.Formatter;
import org.kuali.rice.kew.dto.DocumentTypeDTO;
import org.kuali.rice.kew.exception.WorkflowException;
import org.kuali.rice.kns.bo.BusinessObject;
import org.kuali.rice.kns.datadictionary.AttributeDefinition;
import org.kuali.rice.kns.datadictionary.AttributeSecurity;
import org.kuali.rice.kns.datadictionary.BusinessObjectEntry;
import org.kuali.rice.kns.datadictionary.CollectionDefinition;
import org.kuali.rice.kns.datadictionary.DataDictionary;
import org.kuali.rice.kns.datadictionary.DataDictionaryEntryBase;
import org.kuali.rice.kns.datadictionary.DocumentEntry;
import org.kuali.rice.kns.datadictionary.InactivationBlockingMetadata;
import org.kuali.rice.kns.datadictionary.PrimitiveAttributeDefinition;
import org.kuali.rice.kns.datadictionary.RelationshipDefinition;
import org.kuali.rice.kns.datadictionary.control.ControlDefinition;
import org.kuali.rice.kns.datadictionary.exception.UnknownBusinessClassAttributeException;
import org.kuali.rice.kns.datadictionary.exception.UnknownDocumentTypeException;
import org.kuali.rice.kns.datadictionary.exporter.DataDictionaryMap;
import org.kuali.rice.kns.datadictionary.validation.ValidationPattern;
import org.kuali.rice.kns.document.Document;
import org.kuali.rice.kns.lookup.keyvalues.KeyValuesFinder;
import org.kuali.rice.kns.rule.PromptBeforeValidation;
import org.kuali.rice.kns.service.DataDictionaryService;
import org.kuali.rice.kns.service.KNSServiceLocatorWeb;
import org.kuali.rice.kns.service.KualiModuleService;
import org.kuali.rice.kns.uif.container.View;
import org.kuali.rice.kns.workflow.service.KualiWorkflowInfo;

/**
 * This class is the service implementation for a DataDictionary. It is a thin wrapper around creating, initializing, and
 * returning a DataDictionary. This is the default, Kuali delivered implementation.
 */
public class DataDictionaryServiceImpl implements DataDictionaryService {
    private static final Logger LOG = Logger.getLogger( DataDictionaryServiceImpl.class );
    
    
    private DataDictionary dataDictionary;
    private DataDictionaryMap dataDictionaryMap = new DataDictionaryMap(this);

    private ConfigurationService kualiConfigurationService;
    private KualiModuleService kualiModuleService;
    private KualiWorkflowInfo workflowInfoService; 

    
    /**
     * @see org.kuali.rice.kns.service.DataDictionaryService#setBaselinePackages(java.lang.String)
     */
    public void setBaselinePackages(List baselinePackages) throws IOException {
        this.addDataDictionaryLocations(baselinePackages);
    }

    /**
     * Default constructor.
     */
    public DataDictionaryServiceImpl() {
        this.dataDictionary = new DataDictionary();
    }
    
    public DataDictionaryServiceImpl(DataDictionary dataDictionary) {
        this.dataDictionary = dataDictionary;
    }

    /**
     * @see org.kuali.rice.kns.service.DataDictionaryService#getDataDictionary()
     */
    public DataDictionary getDataDictionary() {
        return dataDictionary;
    }
    
    /**
     * @see org.kuali.rice.kns.service.BusinessObjectDictionaryService#getAttributeControlDefinition(java.lang.String)
     */
    public ControlDefinition getAttributeControlDefinition(String entryName, String attributeName) {
        ControlDefinition controlDefinition = null;

        AttributeDefinition attributeDefinition = getAttributeDefinition(entryName, attributeName);
        if (attributeDefinition != null) {
            controlDefinition = attributeDefinition.getControl();
        }

        return controlDefinition;
    }

    /**
     * @see org.kuali.rice.kns.service.BusinessObjectDictionaryService#getAttributeSize(java.lang.String)
     */
    public Integer getAttributeSize(String entryName, String attributeName) {
        Integer size = null;

        AttributeDefinition attributeDefinition = getAttributeDefinition(entryName, attributeName);
        if (attributeDefinition != null) {
            ControlDefinition controlDefinition = attributeDefinition.getControl();
            if (controlDefinition.isText() || controlDefinition.isCurrency()) {
                size = controlDefinition.getSize();
            }
        }

        return size;
    }

    /**
     * @see org.kuali.rice.kns.service.BusinessObjectDictionaryService#getAttributeMinLength(java.lang.String)
     */
    public Integer getAttributeMinLength(String entryName, String attributeName) {
        Integer minLength = null;

        AttributeDefinition attributeDefinition = getAttributeDefinition(entryName, attributeName);
        if (attributeDefinition != null) {
        	minLength = attributeDefinition.getMinLength();
        }

        return minLength;
    }
    
    /**
     * @see org.kuali.rice.kns.service.BusinessObjectDictionaryService#getAttributeMaxLength(java.lang.String)
     */
    public Integer getAttributeMaxLength(String entryName, String attributeName) {
        Integer maxLength = null;

        AttributeDefinition attributeDefinition = getAttributeDefinition(entryName, attributeName);
        if (attributeDefinition != null) {
            maxLength = attributeDefinition.getMaxLength();
        }

        return maxLength;
    }

    /**
     * @see org.kuali.rice.kns.service.DataDictionaryService#getAttributeExclusiveMin
     */
    public String getAttributeExclusiveMin(String entryName, String attributeName) {
        AttributeDefinition attributeDefinition = getAttributeDefinition(entryName, attributeName);
        return attributeDefinition == null ? null : attributeDefinition.getExclusiveMin();
    }

    /**
     * @see org.kuali.rice.kns.service.DataDictionaryService#getAttributeInclusiveMax
     */
    public String getAttributeInclusiveMax(String entryName, String attributeName) {
        AttributeDefinition attributeDefinition = getAttributeDefinition(entryName, attributeName);
        return attributeDefinition == null ? null : attributeDefinition.getInclusiveMax();
    }

    /**
     * @see org.kuali.rice.kns.service.BusinessObjectDictionaryService#getAttributeValidatingExpression(java.lang.String)
     */
    public Pattern getAttributeValidatingExpression(String entryName, String attributeName) {
        Pattern regex = null;

        AttributeDefinition attributeDefinition = getAttributeDefinition(entryName, attributeName);
        if (attributeDefinition != null) {
            if (attributeDefinition.hasValidationPattern()) {
                regex = attributeDefinition.getValidationPattern().getRegexPattern();
            } else {
                // workaround for existing calls which don't bother checking for null return values
                regex = Pattern.compile(".*");
            }
        }

        return regex;
    }

    /**
     * @see org.kuali.rice.kns.service.BusinessObjectDictionaryService#getAttributeLabel(java.lang.String)
     */
    public String getAttributeLabel(String entryName, String attributeName) {
        String label = "";

        AttributeDefinition attributeDefinition = getAttributeDefinition(entryName, attributeName);
        if (attributeDefinition != null) {
        	// KULRICE-4445 prevent NullPointerException by ensuring a label is set
     	 	label = attributeDefinition.getLabel();
        	if (!StringUtils.isEmpty(attributeDefinition.getDisplayLabelAttribute())) {
                attributeDefinition = getAttributeDefinition(entryName, attributeDefinition.getDisplayLabelAttribute());
                if (attributeDefinition != null) {
                    label = attributeDefinition.getLabel();
                }
            }
        }

        return label;
    }

    /**
     * @see org.kuali.rice.kns.service.BusinessObjectDictionaryService#getAttributeShortLabel(java.lang.String)
     */
    public String getAttributeShortLabel(String entryName, String attributeName) {
        String shortLabel = "";

        AttributeDefinition attributeDefinition = getAttributeDefinition(entryName, attributeName);
        if (attributeDefinition != null) {
            if (!StringUtils.isEmpty(attributeDefinition.getDisplayLabelAttribute())) {
                attributeDefinition = getAttributeDefinition(entryName, attributeDefinition.getDisplayLabelAttribute());
                if (attributeDefinition != null) {
                    shortLabel = attributeDefinition.getShortLabel();
                }
            } else {
                shortLabel = attributeDefinition.getShortLabel();
            }
        }

        return shortLabel;
    }

    /**
     * @see org.kuali.rice.kns.service.BusinessObjectDictionaryService#getAttributeErrorLabel(java.lang.String)
     */
    public String getAttributeErrorLabel(String entryName, String attributeName) {
        String longAttributeLabel = this.getAttributeLabel(entryName, attributeName);
        String shortAttributeLabel = this.getAttributeShortLabel(entryName, attributeName);
        return longAttributeLabel + " (" + shortAttributeLabel + ")";
    }

    /**
     * @see org.kuali.rice.kns.service.BusinessObjectDictionaryService#getAttributeFormatter(java.lang.String)
     */
    public Class<? extends Formatter> getAttributeFormatter(String entryName, String attributeName) {
        Class formatterClass = null;

        AttributeDefinition attributeDefinition = getAttributeDefinition(entryName, attributeName);
        if (attributeDefinition != null) {
            if (attributeDefinition.hasFormatterClass()) {
                formatterClass = ClassLoaderUtils.getClass(attributeDefinition.getFormatterClass());
            }
        }

        return formatterClass;
    }

    /**
     * @see org.kuali.rice.kns.service.BusinessObjectDictionaryService#getAttributeForceUppercase(java.lang.String)
     */
    public Boolean getAttributeForceUppercase(String entryName, String attributeName) throws UnknownBusinessClassAttributeException {
        Boolean forceUppercase = null;

        AttributeDefinition attributeDefinition = getAttributeDefinition(entryName, attributeName);
        if (attributeDefinition == null) {
            throw new UnknownBusinessClassAttributeException("Could not find a matching data dictionary business class attribute entry for " + entryName + "." + attributeName);
        }
        forceUppercase = attributeDefinition.getForceUppercase();

        return forceUppercase;
    }
    
    /**
     * @see org.kuali.rice.kns.service.DataDictionaryService#getAttributeDisplayMask(java.lang.String, java.lang.String)
     */
    public AttributeSecurity getAttributeSecurity(String entryName, String attributeName) {
        AttributeSecurity attributeSecurity = null;

        AttributeDefinition attributeDefinition = getAttributeDefinition(entryName, attributeName);
        if (attributeDefinition != null) {
            attributeSecurity = attributeDefinition.getAttributeSecurity();
        }

        return attributeSecurity;
    }


    /**
     * @see org.kuali.rice.kns.service.BusinessObjectDictionaryService#getAttributeSummary(java.lang.String)
     */
    public String getAttributeSummary(String entryName, String attributeName) {
        String summary = null;

        AttributeDefinition attributeDefinition = getAttributeDefinition(entryName, attributeName);
        if (attributeDefinition != null) {
            summary = attributeDefinition.getSummary();
        }

        return summary;
    }

    /**
     * @see org.kuali.rice.kns.service.BusinessObjectDictionaryService#getAttributeDescription(java.lang.String)
     */
    public String getAttributeDescription(String entryName, String attributeName) {
        String description = null;

        AttributeDefinition attributeDefinition = getAttributeDefinition(entryName, attributeName);
        if (attributeDefinition != null) {
            description = attributeDefinition.getDescription();
        }

        return description;
    }

    /**
     * @see org.kuali.rice.kns.service.BusinessObjectDictionaryService#isAttributeRequired(java.lang.Class, java.lang.String)
     */
    public Boolean isAttributeRequired(String entryName, String attributeName) {
        Boolean required = null;

        AttributeDefinition attributeDefinition = getAttributeDefinition(entryName, attributeName);
        if (attributeDefinition != null) {
            required = attributeDefinition.isRequired();
        }

        return required;
    }

    /**
     * @see org.kuali.rice.kns.service.BusinessObjectDictionaryService#isAttributeDefined(java.lang.Class, java.lang.String)
     */
    public Boolean isAttributeDefined(String entryName, String attributeName) {
        boolean isDefined = false;

        AttributeDefinition attributeDefinition = getAttributeDefinition(entryName, attributeName);
        if (attributeDefinition != null) {
            isDefined = true;
        }

        return isDefined;
    }

    /**
     * @see org.kuali.rice.kns.service.BusinessObjectDictionaryService#getAttributeValuesScopeId(java.lang.Class,
     *      java.lang.String)
     */
    public Class<? extends KeyValuesFinder> getAttributeValuesFinderClass(String entryName, String attributeName) {
        Class valuesFinderClass = null;

        AttributeDefinition attributeDefinition = getAttributeDefinition(entryName, attributeName);
        if (attributeDefinition != null) {
            String valuesFinderClassName = attributeDefinition.getControl().getValuesFinderClass();
            valuesFinderClass = ClassLoaderUtils.getClass(valuesFinderClassName);
        }

        return valuesFinderClass;
    }

    /**
     * @see org.kuali.rice.kns.service.BusinessObjectDictionaryService#getCollectionLabel(java.lang.Class, java.lang.String)
     */
    public String getCollectionLabel(String entryName, String collectionName) {
        String label = "";

        CollectionDefinition collectionDefinition = getCollectionDefinition(entryName, collectionName);
        if (collectionDefinition != null) {
            label = collectionDefinition.getLabel();
        }

        return label;
    }

    /**
     * @see org.kuali.rice.kns.service.BusinessObjectDictionaryService#getCollectionShortLabel(java.lang.Class, java.lang.String)
     */
    public String getCollectionShortLabel(String entryName, String collectionName) {
        String shortLabel = "";

        CollectionDefinition collectionDefinition = getCollectionDefinition(entryName, collectionName);
        if (collectionDefinition != null) {
            shortLabel = collectionDefinition.getShortLabel();
        }

        return shortLabel;
    }

    /**
     * @see org.kuali.rice.kns.service.BusinessObjectDictionaryService#getCollectionElementLabel(java.lang.Class,
     *      java.lang.String)
     */
    public String getCollectionElementLabel(String entryName, String collectionName, Class businessObjectClass) {
        String elementLabel = "";

        CollectionDefinition collectionDefinition = getCollectionDefinition(entryName, collectionName);
        if (collectionDefinition != null) {
            elementLabel = collectionDefinition.getElementLabel();
            if (StringUtils.isEmpty(elementLabel)) {
                BusinessObjectEntry boe = getDataDictionary().getBusinessObjectEntry(businessObjectClass.getName());
                if (boe != null) {
                    elementLabel = boe.getObjectLabel();
                }
            }
        }

        return elementLabel;
    }

    /**
     * @see org.kuali.rice.kns.service.BusinessObjectDictionaryService#getCollectionSummary(java.lang.Class, java.lang.String)
     */
    public String getCollectionSummary(String entryName, String collectionName) {
        String summary = null;

        CollectionDefinition collectionDefinition = getCollectionDefinition(entryName, collectionName);
        if (collectionDefinition != null) {
            summary = collectionDefinition.getSummary();
        }

        return summary;
    }

    /**
     * @see org.kuali.rice.kns.service.BusinessObjectDictionaryService#getCollectionDescription(java.lang.Class,
     *      java.lang.String)
     */
    public String getCollectionDescription(String entryName, String collectionName) {
        String description = null;

        CollectionDefinition collectionDefinition = getCollectionDefinition(entryName, collectionName);
        if (collectionDefinition != null) {
            description = collectionDefinition.getDescription();
        }

        return description;
    }

    public Class<? extends BusinessObject> getRelationshipSourceClass(String entryName, String relationshipName) {
        Class sourceClass = null;

        RelationshipDefinition rd = getRelationshipDefinition(entryName, relationshipName);
        if (rd != null) {
            sourceClass = rd.getSourceClass();
        }

        return sourceClass;
    }

    public Class<? extends BusinessObject> getRelationshipTargetClass(String entryName, String relationshipName) {
        Class targetClass = null;

        RelationshipDefinition rd = getRelationshipDefinition(entryName, relationshipName);
        if (rd != null) {
            targetClass = rd.getTargetClass();
        }

        return targetClass;
    }

    public List<String> getRelationshipSourceAttributes(String entryName, String relationshipName) {
        List<String> sourceAttributes = null;

        RelationshipDefinition rd = getRelationshipDefinition(entryName, relationshipName);
        if (rd != null) {
            sourceAttributes = new ArrayList<String>();

            for (PrimitiveAttributeDefinition pad : rd.getPrimitiveAttributes()) {
                sourceAttributes.add(pad.getSourceName());
            }
        }

        return sourceAttributes;
    }

    public List<String> getRelationshipTargetAttributes(String entryName, String relationshipName) {
        List<String> targetAttributes = null;

        RelationshipDefinition rd = getRelationshipDefinition(entryName, relationshipName);
        if (rd != null) {
            targetAttributes = new ArrayList<String>();

            for (PrimitiveAttributeDefinition pad : rd.getPrimitiveAttributes()) {
                targetAttributes.add(pad.getTargetName());
            }
        }

        return targetAttributes;
    }

    public List<String> getRelationshipEntriesForSourceAttribute(String entryName, String sourceAttributeName) {
        List<String> relationships = new ArrayList<String>();

        DataDictionaryEntryBase entry = (DataDictionaryEntryBase) getDataDictionary().getDictionaryObjectEntry(entryName);

        for (RelationshipDefinition def : entry.getRelationships()) {
            for (PrimitiveAttributeDefinition pddef : def.getPrimitiveAttributes()) {
                if (StringUtils.equals(sourceAttributeName, pddef.getSourceName())) {
                    relationships.add(def.getObjectAttributeName());
                    break;
                }
            }
        }
        return relationships;
    }

    public List<String> getRelationshipEntriesForTargetAttribute(String entryName, String targetAttributeName) {
        List<String> relationships = new ArrayList<String>();

        DataDictionaryEntryBase entry = (DataDictionaryEntryBase) getDataDictionary().getDictionaryObjectEntry(entryName);

        for (RelationshipDefinition def : entry.getRelationships()) {
            for (PrimitiveAttributeDefinition pddef : def.getPrimitiveAttributes()) {
                if (StringUtils.equals(targetAttributeName, pddef.getTargetName())) {
                    relationships.add(def.getObjectAttributeName());
                    break;
                }
            }
        }
        return relationships;
    }

    /**
     * @param objectClass
     * @param attributeName
     * @return AttributeDefinition for the given objectClass and attribute name, or null if there is none
     * @throws IllegalArgumentException
     *             if the given Class is null or is not a BusinessObject class
     */
    public AttributeDefinition getAttributeDefinition(String entryName, String attributeName) {
        if (StringUtils.isBlank(attributeName)) {
            throw new IllegalArgumentException("invalid (blank) attributeName");
        }
        AttributeDefinition attributeDefinition = null;

        DataDictionaryEntryBase entry = (DataDictionaryEntryBase) getDataDictionary().getDictionaryObjectEntry(entryName);
        if (entry != null) {
            attributeDefinition = entry.getAttributeDefinition(attributeName);
        }

        return attributeDefinition;
    }
	
    
    /**
     * @param entryName
     * @param collectionName
     * @return CollectionDefinition for the given entryName and collectionName, or null if there is none
     */
    private CollectionDefinition getCollectionDefinition(String entryName, String collectionName) {
        if (StringUtils.isBlank(collectionName)) {
            throw new IllegalArgumentException("invalid (blank) collectionName");
        }
        CollectionDefinition collectionDefinition = null;

        DataDictionaryEntryBase entry = (DataDictionaryEntryBase) getDataDictionary().getDictionaryObjectEntry(entryName);
        if (entry != null) {
            collectionDefinition = entry.getCollectionDefinition(collectionName);
        }

        return collectionDefinition;
    }

    /**
     * @param entryName
     * @param relationshipName
     * @return RelationshipDefinition for the given entryName and relationshipName, or null if there is none
     */
    private RelationshipDefinition getRelationshipDefinition(String entryName, String relationshipName) {
        if (StringUtils.isBlank(relationshipName)) {
            throw new IllegalArgumentException("invalid (blank) relationshipName");
        }

        RelationshipDefinition relationshipDefinition = null;

        DataDictionaryEntryBase entry = (DataDictionaryEntryBase) getDataDictionary().getDictionaryObjectEntry(entryName);
        if (entry != null) {
            relationshipDefinition = entry.getRelationshipDefinition(relationshipName);
        }

        return relationshipDefinition;
    }

    /**
     * @see org.kuali.rice.kns.service.DataDictionaryService#getRelationshipAttributeMap(java.lang.String, java.lang.String)
     */
    public Map<String, String> getRelationshipAttributeMap(String entryName, String relationshipName) {
        Map<String, String> attributeMap = new HashMap<String, String>();
        RelationshipDefinition relationshipDefinition = getRelationshipDefinition(entryName, relationshipName);
        for (Iterator iter = relationshipDefinition.getPrimitiveAttributes().iterator(); iter.hasNext();) {
            PrimitiveAttributeDefinition attribute = (PrimitiveAttributeDefinition) iter.next();
            attributeMap.put(attribute.getTargetName(), attribute.getSourceName());
        }
        return attributeMap;
    }

    public boolean hasRelationship(String entryName, String relationshipName) {
        return getRelationshipDefinition(entryName, relationshipName) != null;
    }

    public List<String> getRelationshipNames(String entryName) {
        DataDictionaryEntryBase entry = (DataDictionaryEntryBase) getDataDictionary().getDictionaryObjectEntry(entryName);

        List<String> relationshipNames = new ArrayList<String>();
        for (RelationshipDefinition def : entry.getRelationships()) {
            relationshipNames.add(def.getObjectAttributeName());
        }
        return relationshipNames;
    }

    /**
     * @see org.kuali.rice.kns.service.DataDictionaryService#getAttributeControlDefinition(java.lang.String, java.lang.String)
     */
    public ControlDefinition getAttributeControlDefinition(Class businessObjectClass, String attributeName) {
        return getAttributeControlDefinition(businessObjectClass.getName(), attributeName);
    }

    /**
     * @see org.kuali.rice.kns.service.DataDictionaryService#getAttributeDescription(java.lang.String, java.lang.String)
     */
    public String getAttributeDescription(Class businessObjectClass, String attributeName) {
        return getAttributeDescription(businessObjectClass.getName(), attributeName);
    }

    /**
     * @see org.kuali.rice.kns.service.DataDictionaryService#getAttributeForceUppercase(java.lang.String, java.lang.String)
     */
    public Boolean getAttributeForceUppercase(Class businessObjectClass, String attributeName) {
        return getAttributeForceUppercase(businessObjectClass.getName(), attributeName);
    }

    /**
     * @see org.kuali.rice.kns.service.DataDictionaryService#getAttributeFormatter(java.lang.String, java.lang.String)
     */
    public Class<? extends Formatter> getAttributeFormatter(Class businessObjectClass, String attributeName) {
        return getAttributeFormatter(businessObjectClass.getName(), attributeName);
    }

    /**
     * @see org.kuali.rice.kns.service.DataDictionaryService#getAttributeLabel(java.lang.String, java.lang.String)
     */
    public String getAttributeLabel(Class businessObjectClass, String attributeName) {
        return getAttributeLabel(businessObjectClass.getName(), attributeName);
    }

    /**
     * @see org.kuali.rice.kns.service.DataDictionaryService#getAttributeMaxLength(java.lang.String, java.lang.String)
     */
    public Integer getAttributeMaxLength(Class businessObjectClass, String attributeName) {
        return getAttributeMaxLength(businessObjectClass.getName(), attributeName);
    }

    /**
     * @see org.kuali.rice.kns.service.DataDictionaryService#getAttributeShortLabel(java.lang.String, java.lang.String)
     */
    public String getAttributeShortLabel(Class businessObjectClass, String attributeName) {
        return getAttributeShortLabel(businessObjectClass.getName(), attributeName);
    }

    /**
     * @see org.kuali.rice.kns.service.DataDictionaryService#getAttributeErrorLabel(java.lang.String, java.lang.String)
     */
    public String getAttributeErrorLabel(Class businessObjectClass, String attributeName) {
        return getAttributeErrorLabel(businessObjectClass.getName(), attributeName);
    }

    /**
     * @see org.kuali.rice.kns.service.DataDictionaryService#getAttributeSize(java.lang.String, java.lang.String)
     */
    public Integer getAttributeSize(Class businessObjectClass, String attributeName) {
        return getAttributeSize(businessObjectClass.getName(), attributeName);
    }

    /**
     * @see org.kuali.rice.kns.service.DataDictionaryService#getAttributeSummary(java.lang.String, java.lang.String)
     */
    public String getAttributeSummary(Class businessObjectClass, String attributeName) {
        return getAttributeSummary(businessObjectClass.getName(), attributeName);
    }

    /**
     * @see org.kuali.rice.kns.service.DataDictionaryService#getAttributeValidatingExpression(java.lang.String, java.lang.String)
     */
    public Pattern getAttributeValidatingExpression(Class businessObjectClass, String attributeName) {
        return getAttributeValidatingExpression(businessObjectClass.getName(), attributeName);
    }

    /**
     * @see org.kuali.rice.kns.service.DataDictionaryService#getAttributeValuesFinderClass(java.lang.String, java.lang.String)
     */
    public Class getAttributeValuesFinderClass(Class businessObjectClass, String attributeName) {
        return getAttributeValuesFinderClass(businessObjectClass.getName(), attributeName);
    }

    /**
	 * @see org.kuali.rice.kns.service.DataDictionaryService#getAttributeValidatingErrorMessageKey(java.lang.String, java.lang.String)
	 */
	public String getAttributeValidatingErrorMessageKey(String entryName, String attributeName) {
        AttributeDefinition attributeDefinition = getAttributeDefinition(entryName, attributeName);
        if (attributeDefinition != null) {
        	if (attributeDefinition.hasValidationPattern()) {
        		ValidationPattern validationPattern = attributeDefinition.getValidationPattern();
        		return validationPattern.getValidationErrorMessageKey();
        	}
        }
        return null;
	}

	/**
	 * @see org.kuali.rice.kns.service.DataDictionaryService#getAttributeValidatingErrorMessageParameters(java.lang.String, java.lang.String)
	 */
	public String[] getAttributeValidatingErrorMessageParameters(String entryName, String attributeName) {
		AttributeDefinition attributeDefinition = getAttributeDefinition(entryName, attributeName);
        if (attributeDefinition != null) {
        	if (attributeDefinition.hasValidationPattern()) {
        		ValidationPattern validationPattern = attributeDefinition.getValidationPattern();
        		String attributeLabel = getAttributeErrorLabel(entryName, attributeName);
        		return validationPattern.getValidationErrorMessageParameters(attributeLabel);
        	}
        }
        return null;
	}

	/**
     * @see org.kuali.rice.kns.service.DataDictionaryService#getCollectionDescription(java.lang.String, java.lang.String)
     */
    public String getCollectionDescription(Class businessObjectClass, String collectionName) {
        return getCollectionDescription(businessObjectClass.getName(), collectionName);
    }

    /**
     * @see org.kuali.rice.kns.service.DataDictionaryService#getCollectionLabel(java.lang.String, java.lang.String)
     */
    public String getCollectionLabel(Class businessObjectClass, String collectionName) {
        return getCollectionLabel(businessObjectClass.getName(), collectionName);
    }

    /**
     * @see org.kuali.rice.kns.service.DataDictionaryService#getCollectionShortLabel(java.lang.String, java.lang.String)
     */
    public String getCollectionShortLabel(Class businessObjectClass, String collectionName) {
        return getCollectionShortLabel(businessObjectClass.getName(), collectionName);
    }

    /**
     * @see org.kuali.rice.kns.service.DataDictionaryService#getCollectionSummary(java.lang.String, java.lang.String)
     */
    public String getCollectionSummary(Class businessObjectClass, String collectionName) {
        return getCollectionSummary(businessObjectClass.getName(), collectionName);
    }

    /**
     * @see org.kuali.rice.kns.service.DataDictionaryService#isAttributeDefined(java.lang.String, java.lang.String)
     */
    public Boolean isAttributeDefined(Class businessObjectClass, String attributeName) {
        return isAttributeDefined(businessObjectClass.getName(), attributeName);
    }

    /**
     * @see org.kuali.rice.kns.service.DataDictionaryService#isAttributeRequired(java.lang.String, java.lang.String)
     */
    public Boolean isAttributeRequired(Class businessObjectClass, String attributeName) {
        return isAttributeRequired(businessObjectClass.getName(), attributeName);

    }

    /**
     * @see org.kuali.rice.kns.service.DataDictionaryService#getDocumentLabelByClass(java.lang.Class)
     */
    public String getDocumentLabelByClass(Class documentOrBusinessObjectClass) {
        return getDocumentLabelByTypeName(getDocumentTypeNameByClass(documentOrBusinessObjectClass));
    }

    /**
     * @see org.kuali.rice.kns.service.DataDictionaryService#getDocumentLabelByTypeName(java.lang.String)
     */
    public String getDocumentLabelByTypeName(String documentTypeName) {
        String label = null;
        if (StringUtils.isNotBlank(documentTypeName)) {
            try {
                DocumentTypeDTO documentType = getWorkflowInfoService().getDocType(documentTypeName);
                if (documentType != null) {
                    label = documentType.getDocTypeLabel();
                }
            } catch (WorkflowException e) {
                // stop blowing up when workflow doc does not exist
                LOG.error("Caught Workflow Exception trying to get document type '" + documentTypeName + "'", e);
                return documentTypeName;
            }
        }
        return label;
    }

    /**
     * @see org.kuali.rice.kns.service.DataDictionaryService#getDocumentTypeNameByClass(java.lang.Class)
     */
    public String getDocumentTypeNameByClass(Class documentClass) {
        if (documentClass == null) {
            throw new IllegalArgumentException("invalid (null) documentClass");
        }
        if (!Document.class.isAssignableFrom(documentClass)) {
            throw new IllegalArgumentException("invalid (non-Document) documentClass");
        }

        String documentTypeName = null;

        DocumentEntry documentEntry = getDataDictionary().getDocumentEntry(documentClass.getName());
        if (documentEntry != null) {
            documentTypeName = documentEntry.getDocumentTypeName();
        }

        return documentTypeName;
    }

    /**
     * @see org.kuali.rice.kns.service.DataDictionaryService#getValidDocumentTypeNameByClass(java.lang.Class)
     */
    public String getValidDocumentTypeNameByClass(Class documentClass) {
        String documentTypeName = getDocumentTypeNameByClass(documentClass);
        if (StringUtils.isBlank(documentTypeName)) {
            throw new UnknownDocumentTypeException("unable to get documentTypeName for unknown documentClass '" + documentClass.getName() + "'");
        }
        return documentTypeName;
    }

    /**
     * @see org.kuali.rice.kns.service.DataDictionaryService#getDocumentClassByTypeName(java.lang.String)
     */
    public Class<? extends Document> getDocumentClassByTypeName(String documentTypeName) {
        Class clazz = null;

        DocumentEntry documentEntry = getDataDictionary().getDocumentEntry(documentTypeName);
        if (documentEntry != null) {
            clazz = documentEntry.getDocumentClass();
        }

        return clazz;
    }

    /**
     * @see org.kuali.rice.kns.service.DataDictionaryService#getValidDocumentClassByTypeName(java.lang.String)
     */
    public Class<? extends Document> getValidDocumentClassByTypeName(String documentTypeName) {
        Class clazz = getDocumentClassByTypeName(documentTypeName);
        if (clazz == null) {
            throw new UnknownDocumentTypeException("unable to get class for unknown documentTypeName '" + documentTypeName + "'");
        }
        return clazz;
    }

    /**
     * @see org.kuali.rice.kns.service.DataDictionaryService#getPromptBeforeValidationClass(java.lang.String)
     */
    public Class<? extends PromptBeforeValidation> getPromptBeforeValidationClass(String docTypeName) {
        Class preRulesCheckClass = null;

        DocumentEntry documentEntry = getDataDictionary().getDocumentEntry(docTypeName);
        if (documentEntry != null) {
            preRulesCheckClass = documentEntry.getPromptBeforeValidationClass();
        }

        return preRulesCheckClass;
    }

	public View getViewById(String viewId) {
		return dataDictionary.getViewById(viewId);
	}
    
	public View getViewByTypeIndex(String viewTypeName, Map<String, String> indexKey) {
		return dataDictionary.getViewByTypeIndex(viewTypeName, indexKey);
	}

	public void addDataDictionaryLocation(String location) throws IOException {
        dataDictionary.addConfigFileLocation(location);
    }

    public void addDataDictionaryLocations(List<String> locations) throws IOException {
        for (String location : locations) {
            addDataDictionaryLocation(location);
        }
    }
    
    public Map getDataDictionaryMap() {
        return dataDictionaryMap;
    }

    public void setKualiConfigurationService(ConfigurationService kualiConfigurationService) {
        this.kualiConfigurationService = kualiConfigurationService;
    }

    public ConfigurationService getKualiConfigurationService() {
        return kualiConfigurationService;
    }

    public KualiModuleService getKualiModuleService() {
        return kualiModuleService;
    }

    public void setKualiModuleService(KualiModuleService kualiModuleService) {
        this.kualiModuleService = kualiModuleService;
    }

    /**
     * Returns all of the inactivation blocks registered for a particular business object
     * 
     * @see org.kuali.rice.kns.service.DataDictionaryService#getAllInactivationBlockingDefinitions(java.lang.Class)
     */
    public Set<InactivationBlockingMetadata> getAllInactivationBlockingDefinitions(Class inactivationBlockedBusinessObjectClass) {
        Set<InactivationBlockingMetadata> blockingClasses = dataDictionary.getAllInactivationBlockingMetadatas(inactivationBlockedBusinessObjectClass);
        if (blockingClasses == null) {
            return Collections.emptySet();
        }
        return blockingClasses;
    }

	public KualiWorkflowInfo getWorkflowInfoService() {
		if ( workflowInfoService == null ) {
			workflowInfoService = KNSServiceLocatorWeb.getWorkflowInfoService();
		}
		return workflowInfoService;
	}


}

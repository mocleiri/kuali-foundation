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
package org.kuali.rice.kns.web.struts.form;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.kuali.rice.kns.lookup.LookupUtils;
import org.kuali.rice.kns.lookup.Lookupable;
import org.kuali.rice.kns.service.DataDictionaryService;
import org.kuali.rice.kns.service.KNSServiceLocatorWeb;
import org.kuali.rice.kns.util.ExternalizableBusinessObjectUtils;
import org.kuali.rice.kns.util.KNSConstants;
import org.kuali.rice.kns.web.ui.Field;
import org.kuali.rice.kns.web.ui.Row;

/**
 * This class is the action form for all lookups.
 */
public class LookupForm extends KualiForm {
    private static final long serialVersionUID = 1L;

    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(LookupForm.class);

    private String formKey;
    private Map<String, String> fields;
    private Map fieldsForLookup;
    private String lookupableImplServiceName;
    private String conversionFields;
    private Map fieldConversions;
    private String businessObjectClassName;
    private Lookupable lookupable;
    private boolean hideReturnLink = false;
    private boolean suppressActions = false;
    private boolean multipleValues = false;
    private String lookupAnchor;
    private String readOnlyFields;
    private List readOnlyFieldsList;
    private String referencesToRefresh;
    private boolean searchUsingOnlyPrimaryKeyValues;
    private String primaryKeyFieldLabels;
    private boolean showMaintenanceLinks = false;
    private String docNum;
    private String htmlDataType;
    private String lookupObjectId;
	private boolean lookupCriteriaEnabled = true;
    private boolean supplementalActionsEnabled = false;
    private boolean actionUrlsExist = false;
    private boolean ddExtraButton = false;
	private boolean headerBarEnabled = true;
	private boolean disableSearchButtons = false;
    
    /**
     * @see org.kuali.rice.kns.web.struts.form.KualiForm#addRequiredNonEditableProperties()
     */
    public void addRequiredNonEditableProperties(){
    	super.addRequiredNonEditableProperties();
    	registerRequiredNonEditableProperty(KNSConstants.LOOKUPABLE_IMPL_ATTRIBUTE_NAME);
    	registerRequiredNonEditableProperty(KNSConstants.BUSINESS_OBJECT_CLASS_ATTRIBUTE);
    	registerRequiredNonEditableProperty(KNSConstants.DISPATCH_REQUEST_PARAMETER);
    	registerRequiredNonEditableProperty(KNSConstants.DOC_FORM_KEY);
    	registerRequiredNonEditableProperty(KNSConstants.REFRESH_CALLER);
    	registerRequiredNonEditableProperty(KNSConstants.DOC_NUM);
    	registerRequiredNonEditableProperty(KNSConstants.REFERENCES_TO_REFRESH);
    	registerRequiredNonEditableProperty(KNSConstants.FORM_KEY);
    	registerRequiredNonEditableProperty(KNSConstants.CONVERSION_FIELDS_PARAMETER);
    	registerRequiredNonEditableProperty(KNSConstants.FIELDS_CONVERSION_PARAMETER);
    	registerRequiredNonEditableProperty(KNSConstants.HIDE_LOOKUP_RETURN_LINK);
    	registerRequiredNonEditableProperty(KNSConstants.MULTIPLE_VALUE);
    	registerRequiredNonEditableProperty(KNSConstants.BACK_LOCATION);
    	registerRequiredNonEditableProperty(KNSConstants.LOOKUP_ANCHOR);
    	registerRequiredNonEditableProperty("searchUsingOnlyPrimaryKeyValues");
    	registerRequiredNonEditableProperty(KNSConstants.MULTIPLE_VALUE_LOOKUP_PREVIOUSLY_SELECTED_OBJ_IDS_PARAM);
    	registerRequiredNonEditableProperty(KNSConstants.TableRenderConstants.VIEWED_PAGE_NUMBER);
    }
    
    /**
	 * @return the htmlDataType
	 */
	public String getHtmlDataType() {
		return this.htmlDataType;
	}

	/**
	 * @param htmlDataType the htmlDataType to set
	 */
	public void setHtmlDataType(String htmlDataType) {
		this.htmlDataType = htmlDataType;
	}

	/**
	 * @return the docNum
	 */
	public String getDocNum() {
		return this.docNum;
	}

	/**
	 * @param docNum the docNum to set
	 */
	public void setDocNum(String docNum) {
		this.docNum = docNum;
	}

	/**
     * Whether the results contain at least one row that is returnable.
     */
    private boolean hasReturnableRow;
    
    
    // used for internal purposes in populate
    private Map requestParameters;
    
    /**
     * Stores the incoming request parameters so that they can be passed to the Lookupable implementation.
     */
    @Override
    public void postprocessRequestParameters(Map requestParameters) {
        this.requestParameters = requestParameters;
        super.postprocessRequestParameters(requestParameters);
    }

    /**
     * Picks out business object name from the request to get retrieve a lookupable and set properties.
     */
    public void populate(HttpServletRequest request) {
        super.populate(request);

        DataDictionaryService ddService = KNSServiceLocatorWeb.getDataDictionaryService();

        try {
            Lookupable localLookupable = null;
            if (StringUtils.isBlank(getParameter(request, KNSConstants.LOOKUPABLE_IMPL_ATTRIBUTE_NAME)) && StringUtils.isBlank(getLookupableImplServiceName())) {
                // get the business object class for the lookup
                String localBusinessObjectClassName = getParameter(request, KNSConstants.BUSINESS_OBJECT_CLASS_ATTRIBUTE);
                if ( ExternalizableBusinessObjectUtils.isExternalizableBusinessObjectInterface(localBusinessObjectClassName) ) {
                	Class localBusinessObjectClass = Class.forName(localBusinessObjectClassName);
                	localBusinessObjectClassName = KNSServiceLocatorWeb.getKualiModuleService().getResponsibleModuleService(localBusinessObjectClass).getExternalizableBusinessObjectImplementation(localBusinessObjectClass).getName();
                }
                setBusinessObjectClassName(localBusinessObjectClassName);
                if (StringUtils.isBlank(localBusinessObjectClassName)) {
                    LOG.error("Business object class not passed to lookup.");
                    throw new RuntimeException("Business object class not passed to lookup.");
                }

                // call data dictionary service to get lookup impl for bo class
                String lookupImplID = KNSServiceLocatorWeb.getBusinessObjectDictionaryService().getLookupableID(Class.forName(localBusinessObjectClassName));
                if (lookupImplID == null) {
                    lookupImplID = "kualiLookupable";
                }

                setLookupableImplServiceName(lookupImplID);
            }
            localLookupable = KNSServiceLocatorWeb.getLookupable(getLookupableImplServiceName());

            if (localLookupable == null) {
                LOG.error("Lookup impl not found for lookup impl name " + getLookupableImplServiceName());
                throw new RuntimeException("Lookup impl not found for lookup impl name " + getLookupableImplServiceName());
            }

			// set parameters on lookupable
            localLookupable.setParameters(requestParameters);
            requestParameters = null;
            
            if (getParameter(request, KNSConstants.LOOKUPABLE_IMPL_ATTRIBUTE_NAME) != null) {
                setLookupableImplServiceName(getParameter(request, KNSConstants.LOOKUPABLE_IMPL_ATTRIBUTE_NAME));
            }

            if (getParameter(request, KNSConstants.LOOKUPABLE_IMPL_ATTRIBUTE_NAME) != null) {
                setLookupableImplServiceName(getParameter(request, KNSConstants.LOOKUPABLE_IMPL_ATTRIBUTE_NAME));
            }

            // check the doc form key is empty before setting so we don't override a restored lookup form
            if (request.getAttribute(KNSConstants.DOC_FORM_KEY) != null && StringUtils.isBlank(this.getFormKey())) {
                setFormKey((String) request.getAttribute(KNSConstants.DOC_FORM_KEY));
            }
            else if (getParameter(request, KNSConstants.DOC_FORM_KEY) != null && StringUtils.isBlank(this.getFormKey())) {
                setFormKey(getParameter(request, KNSConstants.DOC_FORM_KEY));
            }
            
            if (getParameter(request, KNSConstants.DOC_NUM) != null) {
                setDocNum(getParameter(request, KNSConstants.DOC_NUM));
           }

            String returnLocation = getParameter(request, "returnLocation");
            if (StringUtils.isNotBlank(returnLocation)) {
                setBackLocation(returnLocation);
                localLookupable.getLookupableHelperService().setBackLocation(returnLocation);
            }

            if (getParameter(request, "conversionFields") != null) {
                setConversionFields(getParameter(request, "conversionFields"));
            }
            if (getParameter(request, KNSConstants.EXTRA_BUTTON_SOURCE) != null) {
            	//these are not sourced from the DD/Lookupable
            	ddExtraButton=false;
                setExtraButtonSource(getParameter(request, KNSConstants.EXTRA_BUTTON_SOURCE));
            }
            if (getParameter(request, KNSConstants.EXTRA_BUTTON_PARAMS) != null) {
                setExtraButtonParams(getParameter(request, KNSConstants.EXTRA_BUTTON_PARAMS));
            }
            String value = getParameter(request, "multipleValues");
            if (value != null) {
                if ("YES".equals(value.toUpperCase())) {
                    setMultipleValues(true);
                }
                else {
                    setMultipleValues(new Boolean(getParameter(request, "multipleValues")).booleanValue());
                }
            }
            if (getParameter(request, KNSConstants.REFERENCES_TO_REFRESH) != null) {
                setReferencesToRefresh(getParameter(request, KNSConstants.REFERENCES_TO_REFRESH));
            }

            if (getParameter(request, "readOnlyFields") != null) {
                setReadOnlyFields(getParameter(request, "readOnlyFields"));
                setReadOnlyFieldsList(LookupUtils.translateReadOnlyFieldsToList(this.readOnlyFields));
                localLookupable.setReadOnlyFieldsList(getReadOnlyFieldsList());
            }

            // init lookupable with bo class
            localLookupable.setBusinessObjectClass(Class.forName(getBusinessObjectClassName()));
            Map<String, String> fieldValues = new HashMap<String, String>();
            Map<String, String> formFields = getFields();
            Class boClass = Class.forName(getBusinessObjectClassName());

            for (Iterator iter = localLookupable.getRows().iterator(); iter.hasNext();) {
                Row row = (Row) iter.next();

                for (Iterator iterator = row.getFields().iterator(); iterator.hasNext();) {
                    Field field = (Field) iterator.next();

                    // check whether form already has value for field
                    if (formFields != null && formFields.containsKey(field.getPropertyName())) {
                        field.setPropertyValue(formFields.get(field.getPropertyName()));
                    }

                    // override values with request
                    if (getParameter(request, field.getPropertyName()) != null) {
                    	if(!Field.MULTI_VALUE_FIELD_TYPES.contains(field.getFieldType())) {
                    		field.setPropertyValue(getParameter(request, field.getPropertyName()).trim());
                    	} else {
                    		//multi value, set to values
                    		field.setPropertyValues(getParameterValues(request, field.getPropertyName()));
                    	}
                    }

            		field.setPropertyValue(LookupUtils.forceUppercase(boClass, field.getPropertyName(), field.getPropertyValue()));
                	fieldValues.put(field.getPropertyName(), field.getPropertyValue());
                	//LOG.info("field name/value added was: " + field.getPropertyName() + field.getPropertyValue());
                	localLookupable.applyFieldAuthorizationsFromNestedLookups(field);
                }
            }

            if (localLookupable.checkForAdditionalFields(fieldValues)) {
                for (Iterator iter = localLookupable.getRows().iterator(); iter.hasNext();) {
                    Row row = (Row) iter.next();

                    for (Iterator iterator = row.getFields().iterator(); iterator.hasNext();) {
                        Field field = (Field) iterator.next();

                        // check whether form already has value for field
                        if (formFields != null && formFields.containsKey(field.getPropertyName())) {
                            field.setPropertyValue(formFields.get(field.getPropertyName()));
                        }

                        // override values with request
                        if (getParameter(request, field.getPropertyName()) != null) {
                        	if(!Field.MULTI_VALUE_FIELD_TYPES.contains(field.getFieldType())) {
                        		field.setPropertyValue(getParameter(request, field.getPropertyName()).trim());
                        	} else {
                        		//multi value, set to values
                        		field.setPropertyValues(getParameterValues(request, field.getPropertyName()));
                        	}
                        }
                        fieldValues.put(field.getPropertyName(), field.getPropertyValue());
                    }
                }
               
            }
            fieldValues.put(KNSConstants.DOC_FORM_KEY, this.getFormKey());
            fieldValues.put(KNSConstants.BACK_LOCATION, this.getBackLocation());
            if(this.getDocNum() != null){
            	fieldValues.put(KNSConstants.DOC_NUM, this.getDocNum());
            }
            if (StringUtils.isNotBlank(getReferencesToRefresh())) {
                fieldValues.put(KNSConstants.REFERENCES_TO_REFRESH, this.getReferencesToRefresh());
            }

            this.setFields(fieldValues);

            setFieldConversions(LookupUtils.translateFieldConversions(this.conversionFields));
            localLookupable.setFieldConversions(getFieldConversions());
            if(StringUtils.isNotEmpty(localLookupable.getExtraButtonSource())) {
            	setExtraButtonSource(localLookupable.getExtraButtonSource());
            	//also set the boolean so the jsp can use an action button
            	ddExtraButton=true;
            }
            if(StringUtils.isNotEmpty(localLookupable.getExtraButtonParams())) {
            	setExtraButtonParams(localLookupable.getExtraButtonParams());
            }
            setLookupable(localLookupable);
            setFieldsForLookup(fieldValues);

            // if showMaintenanceLinks is not already true, only show maintenance links if the lookup was called from the portal (or index.html for the generated applications)
            if (!isShowMaintenanceLinks()) {
            	if (StringUtils.contains(getBackLocation(), "/"+KNSConstants.PORTAL_ACTION) 
            			|| StringUtils.contains(getBackLocation(), "/index.html")) {
            		showMaintenanceLinks = true;
            	}
            }
        }
        catch (ClassNotFoundException e) {
            LOG.error("Business Object class " + getBusinessObjectClassName() + " not found");
            throw new RuntimeException("Business Object class " + getBusinessObjectClassName() + " not found", e);
        }
    }

    /**
     * @return Returns the lookupableImplServiceName.
     */
    public String getLookupableImplServiceName() {
        return lookupableImplServiceName;
    }

    /**
     * @param lookupableImplServiceName The lookupableImplServiceName to set.
     */
    public void setLookupableImplServiceName(String lookupableImplServiceName) {
        this.lookupableImplServiceName = lookupableImplServiceName;
    }

    /**
     * @return Returns the formKey.
     */
    public String getFormKey() {
        return formKey;
    }

    /**
     * @param formKey The formKey to set.
     */
    public void setFormKey(String formKey) {
        this.formKey = formKey;
    }

    /**
     * @return Returns the fields.
     */
    public Map<String, String> getFields() {
        return fields;
    }

    /**
     * @param fields The fields to set.
     */
    public void setFields(Map<String, String> fields) {
        this.fields = fields;
    }

    /**
     * @return Returns the conversionFields.
     */
    public String getConversionFields() {
        return conversionFields;
    }

    /**
     * @param conversionFields The conversionFields to set.
     */
    public void setConversionFields(String conversionFields) {
        this.conversionFields = conversionFields;
    }

    /**
     * @return Returns the fieldConversions.
     */
    public Map getFieldConversions() {
        return fieldConversions;
    }

    /**
     * @param fieldConversions The fieldConversions to set.
     */
    public void setFieldConversions(Map fieldConversions) {
        this.fieldConversions = fieldConversions;
    }

    /**
     * @return Returns the businessObjectClassName.
     */
    public String getBusinessObjectClassName() {
        return businessObjectClassName;
    }

    /**
     * @param businessObjectClassName The businessObjectClassName to set.
     */
    public void setBusinessObjectClassName(String businessObjectClassName) {
        this.businessObjectClassName = businessObjectClassName;
    }


    /**
     * @return Returns the kualiLookupable.
     */
    public Lookupable getLookupable() {
        return lookupable;
    }


    /**
     * @param lookupable The kualiLookupable to set.
     */
    public void setLookupable(Lookupable lookupable) {
        this.lookupable = lookupable;
    }


    /**
     * @return Returns the hideReturnLink.
     */
    public boolean isHideReturnLink() {
        return hideReturnLink;
    }

    /**
     * @param suppressActions The suppressActions to set.
     */
    public void setSuppressActions(boolean suppressActions) {
        this.suppressActions = suppressActions;
    }

    /**
     * @return Returns the suppressActions.
     */
    public boolean isSuppressActions() {
        return suppressActions;
    }


    /**
     * @param hideReturnLink The hideReturnLink to set.
     */
    public void setHideReturnLink(boolean hideReturnLink) {
        this.hideReturnLink = hideReturnLink;
    }

    // TODO: remove these once DD changes have been made
    public String getExtraButtonParams() {
        return extraButtons.get(0).getExtraButtonParams();
    }

    // TODO: remove these once DD changes have been made
    public void setExtraButtonParams(String extraButtonParams) {
        extraButtons.get(0).setExtraButtonParams( extraButtonParams );
    }

    // TODO: remove these once DD changes have been made
    public String getExtraButtonSource() {
        return extraButtons.get(0).getExtraButtonSource();
    }

    // TODO: remove these once DD changes have been made
    public void setExtraButtonSource(String extraButtonSource) {
        extraButtons.get(0).setExtraButtonSource( extraButtonSource );
    }


    /**
     *
     * @return whether this form returns multiple values
     */
    public boolean isMultipleValues() {
        return multipleValues;
    }

    /**
     *
     * @param multipleValues - specify whether this form returns multiple values (i.e. a Collection)
     */
    public void setMultipleValues(boolean multipleValues) {
        this.multipleValues = multipleValues;
    }

    public String getLookupAnchor() {
        return lookupAnchor;
    }

    public void setLookupAnchor(String lookupAnchor) {
        this.lookupAnchor = lookupAnchor;
    }

    /**
     * Gets the fieldsForLookup attribute.
     * @return Returns the fieldsForLookup.
     */
    public Map getFieldsForLookup() {
        return fieldsForLookup;
    }

    /**
     * Sets the fieldsForLookup attribute value.
     * @param fieldsForLookup The fieldsForLookup to set.
     */
    public void setFieldsForLookup(Map fieldsForLookup) {
        this.fieldsForLookup = fieldsForLookup;
    }

    /**
     * Gets the readOnlyFields attribute.
     * @return Returns the readOnlyFields.
     */
    public String getReadOnlyFields() {
        return readOnlyFields;
    }

    /**
     * Sets the readOnlyFields attribute value.
     * @param readOnlyFields The readOnlyFields to set.
     */
    public void setReadOnlyFields(String readOnlyFields) {
        this.readOnlyFields = readOnlyFields;
    }

    /**
     * Gets the readOnlyFieldsList attribute.
     * @return Returns the readOnlyFieldsList.
     */
    public List getReadOnlyFieldsList() {
        return readOnlyFieldsList;
    }

    /**
     * Sets the readOnlyFieldsList attribute value.
     * @param readOnlyFieldsList The readOnlyFieldsList to set.
     */
    public void setReadOnlyFieldsList(List readOnlyFieldsList) {
        this.readOnlyFieldsList = readOnlyFieldsList;
    }

    public String getReferencesToRefresh() {
        return referencesToRefresh;
    }

    public void setReferencesToRefresh(String referencesToRefresh) {
        this.referencesToRefresh = referencesToRefresh;
    }

    public String getPrimaryKeyFieldLabels() {
        return primaryKeyFieldLabels;
    }

    public void setPrimaryKeyFieldLabels(String primaryKeyFieldLabels) {
        this.primaryKeyFieldLabels = primaryKeyFieldLabels;
    }

    public boolean isSearchUsingOnlyPrimaryKeyValues() {
        return searchUsingOnlyPrimaryKeyValues;
    }

    public void setSearchUsingOnlyPrimaryKeyValues(boolean searchUsingOnlyPrimaryKeyValues) {
        this.searchUsingOnlyPrimaryKeyValues = searchUsingOnlyPrimaryKeyValues;
    }

    /**
     * Gets the showMaintenanceLinks attribute.
     * @return Returns the showMaintenanceLinks.
     */
    public boolean isShowMaintenanceLinks() {
        return showMaintenanceLinks;
    }

    /**
     * Sets the showMaintenanceLinks attribute value.
     * @param showMaintenanceLinks The showMaintenanceLinks to set.
     */
    public void setShowMaintenanceLinks(boolean showMaintenanceLinks) {
        this.showMaintenanceLinks = showMaintenanceLinks;
    }

    /**
     * Returns whether the results contain at least one row that is returnable
     * 
     * @return
     */
    public boolean isHasReturnableRow() {
        return this.hasReturnableRow;
    }

    /**
     * Sets whether the results contain at least one row that is returnable
     * 
     * @param hasReturnableRow
     */
    public void setHasReturnableRow(boolean hasReturnableRow) {
        this.hasReturnableRow = hasReturnableRow;
    }

	/**
	 * @return the lookupObjectId
	 */
	public String getLookupObjectId() {
		return this.lookupObjectId;
	}

	/**
	 * @param lookupObjectId the lookupObjectId to set
	 */
	public void setLookupObjectId(String lookupObjectId) {
		this.lookupObjectId = lookupObjectId;
	}

	/**
	 * @return the lookupCriteriaEnabled
	 */
	public boolean isLookupCriteriaEnabled() {
		return this.lookupCriteriaEnabled;
	}

	/**
	 * @param lookupCriteriaEnabled the lookupCriteriaEnabled to set
	 */
	public void setLookupCriteriaEnabled(boolean lookupCriteriaEnabled) {
		this.lookupCriteriaEnabled = lookupCriteriaEnabled;
	}

	/**
	 * @return the supplementalActionsEnabled
	 */
	public boolean isSupplementalActionsEnabled() {
		return this.supplementalActionsEnabled;
	}

	/**
	 * @param supplementalActionsEnabled the supplementalActionsEnabled to set
	 */
	public void setSupplementalActionsEnabled(boolean supplementalActionsEnabled) {
		this.supplementalActionsEnabled = supplementalActionsEnabled;
	}


	/**
	 * @param actionUrlsExist the actionUrlsExist to set
	 */
	public void setActionUrlsExist(boolean actionUrlsExist) {
		this.actionUrlsExist = actionUrlsExist;
	}

	/**
	 * @return the actionUrlsExist
	 */
	public boolean isActionUrlsExist() {
		return actionUrlsExist;
	}

	/**
	 * @return the ddExtraButton
	 */
	public boolean isDdExtraButton() {
		return this.ddExtraButton;
	}

	/**
	 * @param ddExtraButton the ddExtraButton to set
	 */
	public void setDdExtraButton(boolean ddExtraButton) {
		this.ddExtraButton = ddExtraButton;
	}

	public boolean isHeaderBarEnabled() {
		return headerBarEnabled;
	}

	public void setHeaderBarEnabled(boolean headerBarEnabled) {
		this.headerBarEnabled = headerBarEnabled;
	}	

	public boolean isDisableSearchButtons() {
		return this.disableSearchButtons;
	}

	public void setDisableSearchButtons(boolean disableSearchButtons) {
		this.disableSearchButtons = disableSearchButtons;
	}
	
	/**
	 * Determines whether the search/clear buttons should be rendering based on the form property
	 * and what is configured in the data dictionary for the lookup
	 * 
	 * @return boolean true if the buttons should be rendered, false if they should not be
	 */
	public boolean getRenderSearchButtons() {
		boolean renderSearchButtons = true;

		if (disableSearchButtons
				|| KNSServiceLocatorWeb.getBusinessObjectDictionaryService().disableSearchButtonsInLookup(
						getLookupable().getBusinessObjectClass())) {
			renderSearchButtons = false;
		}

		return renderSearchButtons;
	}
}

/*
 * Copyright 2006-2011 The Kuali Foundation
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

package org.kuali.rice.kns.lookup;

import org.apache.commons.lang.StringUtils;
import org.kuali.rice.core.api.config.property.ConfigurationService;
import org.kuali.rice.core.api.encryption.EncryptionService;
import org.kuali.rice.core.api.services.CoreApiServiceLocator;
import org.kuali.rice.core.framework.parameter.ParameterService;
import org.kuali.rice.core.framework.services.CoreFrameworkServiceLocator;
import org.kuali.rice.core.util.RiceKeyConstants;
import org.kuali.rice.core.util.cache.CopiedObject;
import org.kuali.rice.core.util.type.TypeUtils;
import org.kuali.rice.core.web.format.DateFormatter;
import org.kuali.rice.core.web.format.Formatter;
import org.kuali.rice.kim.bo.Person;
import org.kuali.rice.kns.authorization.BusinessObjectRestrictions;
import org.kuali.rice.kns.authorization.FieldRestriction;
import org.kuali.rice.kns.bo.BusinessObject;
import org.kuali.rice.kns.bo.PersistableBusinessObject;
import org.kuali.rice.kns.datadictionary.AttributeSecurity;
import org.kuali.rice.kns.datadictionary.mask.MaskFormatter;
import org.kuali.rice.kns.exception.ValidationException;
import org.kuali.rice.kns.inquiry.Inquirable;
import org.kuali.rice.kns.lookup.HtmlData.AnchorHtmlData;
import org.kuali.rice.kns.lookup.HtmlData.InputHtmlData;
import org.kuali.rice.kns.service.BusinessObjectAuthorizationService;
import org.kuali.rice.kns.service.BusinessObjectDictionaryService;
import org.kuali.rice.kns.service.BusinessObjectMetaDataService;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.service.DataDictionaryService;
import org.kuali.rice.kns.service.KNSServiceLocator;
import org.kuali.rice.kns.service.KNSServiceLocatorWeb;
import org.kuali.rice.kns.service.LookupService;
import org.kuali.rice.kns.service.MaintenanceDocumentDictionaryService;
import org.kuali.rice.kns.service.PersistenceStructureService;
import org.kuali.rice.kns.service.SequenceAccessorService;
import org.kuali.rice.kns.util.FieldUtils;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.util.KNSConstants;
import org.kuali.rice.kns.util.ObjectUtils;
import org.kuali.rice.kns.util.UrlFactory;
import org.kuali.rice.kns.web.comparator.CellComparatorHelper;
import org.kuali.rice.kns.web.struts.form.LookupForm;
import org.kuali.rice.kns.web.struts.form.MultipleValueLookupForm;
import org.kuali.rice.kns.web.ui.Column;
import org.kuali.rice.kns.web.ui.Field;
import org.kuali.rice.kns.web.ui.ResultRow;
import org.kuali.rice.kns.web.ui.Row;

import java.security.GeneralSecurityException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * This class declares many of the common spring injected properties, the get/set-ers for them,
 * and some common util methods that require the injected services
 */
public abstract class AbstractLookupableHelperServiceImpl implements LookupableHelperService {

    protected static final String TITLE_RETURN_URL_PREPENDTEXT_PROPERTY = "title.return.url.value.prependtext";
    protected static final String TITLE_ACTION_URL_PREPENDTEXT_PROPERTY = "title.action.url.value.prependtext";
    protected static final String ACTION_URLS_CHILDREN_SEPARATOR = "&nbsp;|&nbsp;";
    protected static final String ACTION_URLS_CHILDREN_STARTER = "&nbsp;[";
    protected static final String ACTION_URLS_CHILDREN_END = "]";
    protected static final String ACTION_URLS_SEPARATOR = "&nbsp;&nbsp;";
    protected static final String ACTION_URLS_EMPTY = "&nbsp;";

    protected static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(AbstractLookupableHelperServiceImpl.class);

    protected Class businessObjectClass;
    protected Map parameters;
    protected BusinessObjectDictionaryService businessObjectDictionaryService;
    protected BusinessObjectMetaDataService businessObjectMetaDataService;
    protected DataDictionaryService dataDictionaryService;
    protected PersistenceStructureService persistenceStructureService;
    protected EncryptionService encryptionService;
    protected List<String> readOnlyFieldsList;
    protected String backLocation;
    protected String docFormKey;
    protected Map fieldConversions;
    protected LookupService lookupService;
    protected List<Row> rows;
    protected String referencesToRefresh;
    protected SequenceAccessorService sequenceAccessorService;
    protected BusinessObjectService businessObjectService;
    protected LookupResultsService lookupResultsService;
    protected String docNum;
    protected ConfigurationService configurationService;
    protected ParameterService parameterService;
    protected BusinessObjectAuthorizationService businessObjectAuthorizationService;

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

    public AbstractLookupableHelperServiceImpl() {
        rows = null;
    }

    /**
     * This implementation always returns false.
     *
     * @see org.kuali.rice.kns.lookup.LookupableHelperService#checkForAdditionalFields(java.util.Map)
     */
    public boolean checkForAdditionalFields(Map fieldValues) {
        return false;
    }

    /**
     * @see org.kuali.rice.kns.lookup.LookupableHelperService#getBusinessObjectClass()
     */
    public Class getBusinessObjectClass() {
        return businessObjectClass;
    }

    /**
     * @see org.kuali.rice.kns.lookup.LookupableHelperService#setBusinessObjectClass(java.lang.Class)
     */
    public void setBusinessObjectClass(Class businessObjectClass) {
        this.businessObjectClass = businessObjectClass;
        setRows();
    }

    /**
     * @see org.kuali.rice.kns.lookup.LookupableHelperService#getParameters()
     */
    public Map getParameters() {
        return parameters;
    }

    /**
     * @see org.kuali.rice.kns.lookup.LookupableHelperService#setParameters(java.util.Map)
     */
    public void setParameters(Map parameters) {
        this.parameters = parameters;
    }

    /**
     * Gets the dataDictionaryService attribute.
     *
     * @return Returns the dataDictionaryService.
     */
    public DataDictionaryService getDataDictionaryService() {
        return dataDictionaryService != null ? dataDictionaryService : KNSServiceLocatorWeb.getDataDictionaryService();
    }

    /**
     * Sets the dataDictionaryService attribute value.
     *
     * @param dataDictionaryService The dataDictionaryService to set.
     */
    public void setDataDictionaryService(DataDictionaryService dataDictionaryService) {
        this.dataDictionaryService = dataDictionaryService;
    }

    /**
     * Gets the businessObjectDictionaryService attribute.
     *
     * @return Returns the businessObjectDictionaryService.
     */
    public BusinessObjectDictionaryService getBusinessObjectDictionaryService() {
        return businessObjectDictionaryService != null ? businessObjectDictionaryService : KNSServiceLocatorWeb.getBusinessObjectDictionaryService();
    }

    /**
     * Sets the businessObjectDictionaryService attribute value.
     *
     * @param businessObjectDictionaryService
     *         The businessObjectDictionaryService to set.
     */
    public void setBusinessObjectDictionaryService(BusinessObjectDictionaryService businessObjectDictionaryService) {
        this.businessObjectDictionaryService = businessObjectDictionaryService;
    }

    /**
     * Gets the businessObjectMetaDataService attribute.
     *
     * @return Returns the businessObjectMetaDataService.
     */
    public BusinessObjectMetaDataService getBusinessObjectMetaDataService() {
        return businessObjectMetaDataService != null ? businessObjectMetaDataService : KNSServiceLocatorWeb.getBusinessObjectMetaDataService();
    }

    /**
     * Sets the businessObjectMetaDataService attribute value.
     *
     * @param businessObjectMetaDataService The businessObjectMetaDataService to set.
     */
    public void setBusinessObjectMetaDataService(BusinessObjectMetaDataService businessObjectMetaDataService) {
        this.businessObjectMetaDataService = businessObjectMetaDataService;
    }

    /**
     * Gets the persistenceStructureService attribute.
     *
     * @return Returns the persistenceStructureService.
     */
    protected PersistenceStructureService getPersistenceStructureService() {
        return persistenceStructureService != null ? persistenceStructureService : KNSServiceLocator.getPersistenceStructureService();
    }

    /**
     * Sets the persistenceStructureService attribute value.
     *
     * @param persistenceStructureService The persistenceStructureService to set.
     */
    public void setPersistenceStructureService(PersistenceStructureService persistenceStructureService) {
        this.persistenceStructureService = persistenceStructureService;
    }

    /**
     * Gets the encryptionService attribute.
     *
     * @return Returns the encryptionService.
     */
    protected EncryptionService getEncryptionService() {
        return encryptionService != null ? encryptionService : CoreApiServiceLocator.getEncryptionService();
    }

    /**
     * Sets the encryptionService attribute value.
     *
     * @param encryptionService The encryptionService to set.
     */
    public void setEncryptionService(EncryptionService encryptionService) {
        this.encryptionService = encryptionService;
    }

    protected MaintenanceDocumentDictionaryService maintenanceDocumentDictionaryService;

    public MaintenanceDocumentDictionaryService getMaintenanceDocumentDictionaryService() {
        if (maintenanceDocumentDictionaryService == null) {
            maintenanceDocumentDictionaryService = KNSServiceLocatorWeb.getMaintenanceDocumentDictionaryService();
        }
        return maintenanceDocumentDictionaryService;
    }


    public BusinessObjectAuthorizationService getBusinessObjectAuthorizationService() {
        if (businessObjectAuthorizationService == null) {
            businessObjectAuthorizationService = KNSServiceLocatorWeb.getBusinessObjectAuthorizationService();
        }
        return businessObjectAuthorizationService;
    }

    protected Inquirable kualiInquirable;

    public Inquirable getKualiInquirable() {
        if (kualiInquirable == null) {
            kualiInquirable = KNSServiceLocatorWeb.getKualiInquirable();
        }
        return kualiInquirable;
    }

    public void setMaintenanceDocumentDictionaryService(MaintenanceDocumentDictionaryService maintenanceDocumentDictionaryService) {
        this.maintenanceDocumentDictionaryService = maintenanceDocumentDictionaryService;
    }

    public void setKualiInquirable(Inquirable kualiInquirable) {
        this.kualiInquirable = kualiInquirable;
    }


    public ConfigurationService getKualiConfigurationService() {
        if (configurationService == null) {
            configurationService = KNSServiceLocator.getKualiConfigurationService();
        }
        return configurationService;
    }

    public void setParameterService(ConfigurationService configurationService) {
        this.configurationService = configurationService;
    }


    public ParameterService getParameterService() {
        if (parameterService == null) {
            parameterService = CoreFrameworkServiceLocator.getParameterService();
        }
        return parameterService;
    }

    public void setParameterService(ParameterService parameterService) {
        this.parameterService = parameterService;
    }

    /**
     * Determines if underlying lookup bo has associated maintenance document that allows new or copy maintenance actions.
     *
     * @return true if bo has maint doc that allows new or copy actions
     */
    public boolean allowsMaintenanceNewOrCopyAction() {
        boolean allowsNewOrCopy = false;

        String maintDocTypeName = getMaintenanceDocumentTypeName();
        Class boClass = this.getBusinessObjectClass();

        if (StringUtils.isNotBlank(maintDocTypeName)) {
            allowsNewOrCopy = getBusinessObjectAuthorizationService().canCreate(boClass, GlobalVariables.getUserSession().getPerson(), maintDocTypeName);
        }
        return allowsNewOrCopy;
    }

    protected boolean allowsMaintenanceEditAction(BusinessObject businessObject) {
        boolean allowsEdit = false;

        String maintDocTypeName = getMaintenanceDocumentTypeName();

        if (StringUtils.isNotBlank(maintDocTypeName)) {
            allowsEdit = getBusinessObjectAuthorizationService().canMaintain(businessObject, GlobalVariables.getUserSession().getPerson(), maintDocTypeName);
        }
        return allowsEdit;
    }


    /**
     * Build a maintenance url.
     *
     * @param bo           - business object representing the record for maint.
     * @param methodToCall - maintenance action
     * @return
     */
    final public String getMaintenanceUrl(BusinessObject businessObject, HtmlData htmlData, List pkNames, BusinessObjectRestrictions businessObjectRestrictions) {
        htmlData.setTitle(getActionUrlTitleText(businessObject, htmlData.getDisplayText(), pkNames, businessObjectRestrictions));
        return htmlData.constructCompleteHtmlTag();
    }

    /**
     * This method is called by performLookup method to generate action urls.
     * It calls the method getCustomActionUrls to get html data, calls getMaintenanceUrl to get the actual html tag,
     * and returns a formatted/concatenated string of action urls.
     *
     * @see org.kuali.rice.kns.lookup.LookupableHelperService#getActionUrls(org.kuali.rice.kns.bo.BusinessObject)
     */
    final public String getActionUrls(BusinessObject businessObject, List pkNames, BusinessObjectRestrictions businessObjectRestrictions) {
        StringBuffer actions = new StringBuffer();
        List<HtmlData> htmlDataList = getCustomActionUrls(businessObject, pkNames);
        for (HtmlData htmlData : htmlDataList) {
            actions.append(getMaintenanceUrl(businessObject, htmlData, pkNames, businessObjectRestrictions));
            if (htmlData.getChildUrlDataList() != null) {
                if (htmlData.getChildUrlDataList().size() > 0) {
                    actions.append(ACTION_URLS_CHILDREN_STARTER);
                    for (HtmlData childURLData : htmlData.getChildUrlDataList()) {
                        actions.append(getMaintenanceUrl(businessObject, childURLData, pkNames, businessObjectRestrictions));
                        actions.append(ACTION_URLS_CHILDREN_SEPARATOR);
                    }
                    if (actions.toString().endsWith(ACTION_URLS_CHILDREN_SEPARATOR))
                        actions.delete(actions.length() - ACTION_URLS_CHILDREN_SEPARATOR.length(), actions.length());
                    actions.append(ACTION_URLS_CHILDREN_END);
                }
            }
            actions.append(ACTION_URLS_SEPARATOR);
        }
        if (actions.toString().endsWith(ACTION_URLS_SEPARATOR))
            actions.delete(actions.length() - ACTION_URLS_SEPARATOR.length(), actions.length());
        return actions.toString();
    }

    /**
     * Child classes should override this method if they want to return some other action urls.
     *
     * @returns This default implementation returns links to edit and copy maintenance action for
     * the current maintenance record if the business object class has an associated maintenance document.
     * Also checks value of allowsNewOrCopy in maintenance document xml before rendering the copy link.
     * @see org.kuali.rice.kns.lookup.LookupableHelperService#getCustomActionUrls(org.kuali.rice.kns.bo.BusinessObject, java.util.List, java.util.List pkNames)
     */
    public List<HtmlData> getCustomActionUrls(BusinessObject businessObject, List pkNames) {
        List<HtmlData> htmlDataList = new ArrayList<HtmlData>();
        if (allowsMaintenanceEditAction(businessObject)) {
            htmlDataList.add(getUrlData(businessObject, KNSConstants.MAINTENANCE_EDIT_METHOD_TO_CALL, pkNames));
        }
        if (allowsMaintenanceNewOrCopyAction()) {
            htmlDataList.add(getUrlData(businessObject, KNSConstants.MAINTENANCE_COPY_METHOD_TO_CALL, pkNames));
        }
        if (allowsMaintenanceDeleteAction(businessObject)) {
            htmlDataList.add(getUrlData(businessObject, KNSConstants.MAINTENANCE_DELETE_METHOD_TO_CALL, pkNames));
        }
        return htmlDataList;
    }

    /**
     * This method ...
     * for KULRice 3070
     *
     * @return
     */
    protected boolean allowsMaintenanceDeleteAction(BusinessObject businessObject) {

        boolean allowsMaintain = false;
        boolean allowsDelete = false;

        String maintDocTypeName = getMaintenanceDocumentTypeName();

        if (StringUtils.isNotBlank(maintDocTypeName)) {
            allowsMaintain = getBusinessObjectAuthorizationService().canMaintain(businessObject, GlobalVariables.getUserSession().getPerson(), maintDocTypeName);
        }

        allowsDelete = KNSServiceLocatorWeb.getMaintenanceDocumentDictionaryService().getAllowsRecordDeletion(businessObjectClass);

        return allowsDelete && allowsMaintain;
    }

    /**
     * This method constructs an AnchorHtmlData.
     * This method can be overriden by child classes if they want to construct the html data in a different way.
     * Foe example, if they want different type of html tag, like input/image.
     *
     * @param businessObject
     * @param methodToCall
     * @param displayText
     * @param pkNames
     * @return
     */
    protected AnchorHtmlData getUrlData(BusinessObject businessObject, String methodToCall, String displayText, List pkNames) {

        String href = getActionUrlHref(businessObject, methodToCall, pkNames);
        //String title = StringUtils.isBlank(href)?"":getActionUrlTitleText(businessObject, displayText, pkNames);
        AnchorHtmlData anchorHtmlData = new AnchorHtmlData(href, methodToCall, displayText);
        return anchorHtmlData;
    }

    /**
     * This method calls its overloaded method with displayText as methodToCall
     *
     * @param businessObject
     * @param methodToCall
     * @param pkNames
     * @return
     */
    protected AnchorHtmlData getUrlData(BusinessObject businessObject, String methodToCall, List pkNames) {
        return getUrlData(businessObject, methodToCall, methodToCall, pkNames);
    }

    /**
     * A utility method that returns an empty list of action urls.
     *
     * @return
     */
    protected List<HtmlData> getEmptyActionUrls() {
        return new ArrayList<HtmlData>();
    }

    protected HtmlData getEmptyAnchorHtmlData() {
        return new AnchorHtmlData();
    }

    /**
     * This method generates and returns href for the given parameters.
     * This method can be overridden by child classes if they have to generate href differently.
     * For example, refer to IntendedIncumbentLookupableHelperServiceImpl
     *
     * @param businessObject
     * @param methodToCall
     * @param pkNames
     * @return
     */
    protected String getActionUrlHref(BusinessObject businessObject, String methodToCall, List pkNames) {
        Properties parameters = new Properties();
        parameters.put(KNSConstants.DISPATCH_REQUEST_PARAMETER, methodToCall);
        // TODO: why is this not using the businessObject parmeter's class?
        parameters.put(KNSConstants.BUSINESS_OBJECT_CLASS_ATTRIBUTE, businessObject.getClass().getName());
        parameters.putAll(getParametersFromPrimaryKey(businessObject, pkNames));
        if (StringUtils.isNotBlank(getReturnLocation())) {
            parameters.put(KNSConstants.RETURN_LOCATION_PARAMETER, getReturnLocation());
        }
        return UrlFactory.parameterizeUrl(KNSConstants.MAINTENANCE_ACTION, parameters);
    }

    protected Properties getParametersFromPrimaryKey(BusinessObject businessObject, List pkNames) {
        Properties parameters = new Properties();
        for (Iterator iter = pkNames.iterator(); iter.hasNext();) {
            String fieldNm = (String) iter.next();

            Object fieldVal = ObjectUtils.getPropertyValue(businessObject, fieldNm);
            if (fieldVal == null) {
                fieldVal = KNSConstants.EMPTY_STRING;
            }
            if (fieldVal instanceof java.sql.Date) {
                String formattedString = "";
                if (Formatter.findFormatter(fieldVal.getClass()) != null) {
                    Formatter formatter = Formatter.getFormatter(fieldVal.getClass());
                    formattedString = (String) formatter.format(fieldVal);
                    fieldVal = formattedString;
                }
            }

            // Encrypt value if it is a secure field
            if (getBusinessObjectAuthorizationService().attributeValueNeedsToBeEncryptedOnFormsAndLinks(businessObjectClass, fieldNm)) {
                try {
                    fieldVal = getEncryptionService().encrypt(fieldVal) + EncryptionService.ENCRYPTION_POST_PREFIX;
                } catch (GeneralSecurityException e) {
                    LOG.error("Exception while trying to encrypted value for inquiry framework.", e);
                    throw new RuntimeException(e);
                }

            }

            parameters.put(fieldNm, fieldVal.toString());
        }
        return parameters;
    }

    /**
     * This method generates and returns title text for action urls.
     * Child classes can override this if they want to generate the title text differently.
     * For example, refer to BatchJobStatusLookupableHelperServiceImpl
     *
     * @param businessObject
     * @param displayText
     * @param pkNames
     * @return
     */
    protected String getActionUrlTitleText(BusinessObject businessObject, String displayText, List pkNames, BusinessObjectRestrictions businessObjectRestrictions) {
        String prependTitleText = displayText + " "
                + getDataDictionaryService().getDataDictionary().getBusinessObjectEntry(getBusinessObjectClass().getName()).getObjectLabel()
                + " "
                + this.getKualiConfigurationService().getPropertyString(TITLE_ACTION_URL_PREPENDTEXT_PROPERTY);
        return HtmlData.getTitleText(prependTitleText, businessObject, pkNames, businessObjectRestrictions);
    }

    /**
     * Returns the maintenance document type associated with the business object class or null if one does not
     * exist.
     *
     * @return String representing the maintenance document type name
     */
    protected String getMaintenanceDocumentTypeName() {
        MaintenanceDocumentDictionaryService dd = getMaintenanceDocumentDictionaryService();
        String maintDocTypeName = dd.getDocumentTypeName(getBusinessObjectClass());
        return maintDocTypeName;
    }

    /**
     * Gets the readOnlyFieldsList attribute.
     *
     * @return Returns the readOnlyFieldsList.
     */
    public List<String> getReadOnlyFieldsList() {
        return readOnlyFieldsList;
    }


    /**
     * Sets the readOnlyFieldsList attribute value.
     *
     * @param readOnlyFieldsList The readOnlyFieldsList to set.
     */
    public void setReadOnlyFieldsList(List<String> readOnlyFieldsList) {
        this.readOnlyFieldsList = readOnlyFieldsList;
    }

    protected HashMap<String, Boolean> noLookupResultFieldInquiryCache = new HashMap<String, Boolean>();
    protected HashMap<Class, Class> inquirableClassCache = new HashMap<Class, Class>();
    protected HashMap<String, Boolean> forceLookupResultFieldInquiryCache = new HashMap<String, Boolean>();

    /**
     * Returns the inquiry url for a field if one exist.
     *
     * @param bo           the business object instance to build the urls for
     * @param propertyName the property which links to an inquirable
     * @return String url to inquiry
     */
    public HtmlData getInquiryUrl(BusinessObject bo, String propertyName) {
        HtmlData inquiryUrl = new AnchorHtmlData();

        String cacheKey = bo.getClass().getName() + "." + propertyName;
        Boolean noLookupResultFieldInquiry = noLookupResultFieldInquiryCache.get(cacheKey);
        if (noLookupResultFieldInquiry == null) {
            noLookupResultFieldInquiry = getBusinessObjectDictionaryService().noLookupResultFieldInquiry(bo.getClass(), propertyName);
            if (noLookupResultFieldInquiry == null) {
                noLookupResultFieldInquiry = Boolean.TRUE;
            }
            noLookupResultFieldInquiryCache.put(cacheKey, noLookupResultFieldInquiry);
        }
        if (!noLookupResultFieldInquiry) {

            Class<Inquirable> inquirableClass = inquirableClassCache.get(bo.getClass());
            if (!inquirableClassCache.containsKey(bo.getClass())) {
                inquirableClass = getBusinessObjectDictionaryService().getInquirableClass(bo.getClass());
                inquirableClassCache.put(bo.getClass(), inquirableClass);
            }
            Inquirable inq = null;
            try {
                if (inquirableClass != null) {
                    inq = inquirableClass.newInstance();
                } else {
                    inq = getKualiInquirable();
                    if (LOG.isDebugEnabled()) {
                        LOG.debug("Default Inquirable Class: " + inq.getClass());
                    }
                }
                Boolean forceLookupResultFieldInquiry = forceLookupResultFieldInquiryCache.get(cacheKey);
                if (forceLookupResultFieldInquiry == null) {
                    forceLookupResultFieldInquiry = getBusinessObjectDictionaryService().forceLookupResultFieldInquiry(bo.getClass(), propertyName);
                    if (forceLookupResultFieldInquiry == null) {
                        forceLookupResultFieldInquiry = Boolean.FALSE;
                    }
                    forceLookupResultFieldInquiryCache.put(cacheKey, forceLookupResultFieldInquiry);
                }
                inquiryUrl = inq.getInquiryUrl(bo, propertyName, forceLookupResultFieldInquiry);
            } catch (Exception ex) {
                LOG.error("unable to create inquirable to get inquiry URL", ex);
            }
        }

        return inquiryUrl;
    }

    protected CopiedObject<ArrayList<Column>> resultColumns = null;

    /**
     * Constructs the list of columns for the search results. All properties for the column objects come from the DataDictionary.
     */
    public List<Column> getColumns() {
        if (resultColumns == null) {
            ArrayList<Column> columns = new ArrayList<Column>();
            for (String attributeName : getBusinessObjectDictionaryService().getLookupResultFieldNames(getBusinessObjectClass())) {
                Column column = new Column();
                column.setPropertyName(attributeName);
                String columnTitle = getDataDictionaryService().getAttributeLabel(getBusinessObjectClass(), attributeName);
                Boolean useShortLabel = getBusinessObjectDictionaryService().getLookupResultFieldUseShortLabel(businessObjectClass, attributeName);
                if (useShortLabel != null && useShortLabel) {
                    columnTitle = getDataDictionaryService().getAttributeShortLabel(getBusinessObjectClass(), attributeName);
                }
                if (StringUtils.isBlank(columnTitle)) {
                    columnTitle = getDataDictionaryService().getCollectionLabel(getBusinessObjectClass(), attributeName);
                }
                column.setColumnTitle(columnTitle);
                column.setMaxLength(getColumnMaxLength(attributeName));

                if (!businessObjectClass.isInterface()) {
                    try {
                        column.setFormatter(ObjectUtils.getFormatterWithDataDictionary(getBusinessObjectClass()
                                .newInstance(), attributeName));
                    } catch (InstantiationException e) {
                        LOG.info("Unable to get new instance of business object class: " + businessObjectClass.getName(), e);
                        // just swallow exception and leave formatter blank
                    } catch (IllegalAccessException e) {
                        LOG.info("Unable to get new instance of business object class: " + businessObjectClass.getName(), e);
                        // just swallow exception and leave formatter blank
                    }
                }

                String alternateDisplayPropertyName = getBusinessObjectDictionaryService()
                        .getLookupFieldAlternateDisplayAttributeName(getBusinessObjectClass(), attributeName);
                if (StringUtils.isNotBlank(alternateDisplayPropertyName)) {
                    column.setAlternateDisplayPropertyName(alternateDisplayPropertyName);
                }

                String additionalDisplayPropertyName = getBusinessObjectDictionaryService()
                        .getLookupFieldAdditionalDisplayAttributeName(getBusinessObjectClass(), attributeName);
                if (StringUtils.isNotBlank(additionalDisplayPropertyName)) {
                    column.setAdditionalDisplayPropertyName(additionalDisplayPropertyName);
                } else {
                    boolean translateCodes = getBusinessObjectDictionaryService().tranlateCodesInLookup(getBusinessObjectClass());
                    if (translateCodes) {
                        FieldUtils.setAdditionalDisplayPropertyForCodes(getBusinessObjectClass(), attributeName, column);
                    }
                }

                column.setTotal(getBusinessObjectDictionaryService().getLookupResultFieldTotal(getBusinessObjectClass(), attributeName));

                columns.add(column);
            }
            resultColumns = ObjectUtils.deepCopyForCaching(columns);
            return columns;
        }
        return resultColumns.getContent();
    }

    protected static Integer RESULTS_DEFAULT_MAX_COLUMN_LENGTH = null;

    protected int getColumnMaxLength(String attributeName) {
        Integer fieldDefinedMaxLength = getBusinessObjectDictionaryService().getLookupResultFieldMaxLength(getBusinessObjectClass(), attributeName);
        if (fieldDefinedMaxLength == null) {
            if (RESULTS_DEFAULT_MAX_COLUMN_LENGTH == null) {
                try {
                    RESULTS_DEFAULT_MAX_COLUMN_LENGTH = Integer.valueOf(getParameterService().getParameterValueAsString(KNSConstants.KNS_NAMESPACE, KNSConstants.DetailTypes.LOOKUP_PARM_DETAIL_TYPE, KNSConstants.RESULTS_DEFAULT_MAX_COLUMN_LENGTH));
                } catch (NumberFormatException ex) {
                    LOG.error("Lookup field max length parameter not found and unable to parse default set in system parameters (RESULTS_DEFAULT_MAX_COLUMN_LENGTH).");
                }
            }
            return RESULTS_DEFAULT_MAX_COLUMN_LENGTH.intValue();
        }
        return fieldDefinedMaxLength.intValue();
    }

    /**
     * @return Returns the backLocation.
     */
    public String getBackLocation() {
        return backLocation;
    }

    /**
     * @param backLocation The backLocation to set.
     */
    public void setBackLocation(String backLocation) {
        this.backLocation = backLocation;
    }

    /**
     * @see org.kuali.rice.kns.lookup.LookupableHelperService#getReturnLocation()
     */
    public String getReturnLocation() {
        return backLocation;
    }

    /**
     * This method is for lookupable implementations
     *
     * @see org.kuali.rice.kns.lookup.LookupableHelperService#getReturnUrl(org.kuali.rice.kns.bo.BusinessObject, java.util.Map, java.lang.String, java.util.List)
     */
    final public HtmlData getReturnUrl(BusinessObject businessObject, Map fieldConversions, String lookupImpl, List returnKeys, BusinessObjectRestrictions businessObjectRestrictions) {
        String href = getReturnHref(businessObject, fieldConversions, lookupImpl, returnKeys);
        String returnUrlAnchorLabel =
                this.getKualiConfigurationService().getPropertyString(TITLE_RETURN_URL_PREPENDTEXT_PROPERTY);
        AnchorHtmlData anchor = new AnchorHtmlData(href, HtmlData.getTitleText(returnUrlAnchorLabel, businessObject, returnKeys, businessObjectRestrictions));
        anchor.setDisplayText(returnUrlAnchorLabel);
        return anchor;
    }

    /**
     * This method is for lookupable implementations
     *
     * @param businessObject
     * @param fieldConversions
     * @param lookupImpl
     * @param returnKeys
     * @return
     */
    final protected String getReturnHref(BusinessObject businessObject, Map fieldConversions, String lookupImpl, List returnKeys) {
        if (StringUtils.isNotBlank(backLocation)) {
            return UrlFactory.parameterizeUrl(backLocation, getParameters(
                    businessObject, fieldConversions, lookupImpl, returnKeys));
        }
        return "";
    }

    /**
     * @see org.kuali.rice.kns.lookup.LookupableHelperService#getReturnUrl(org.kuali.core.bo.BusinessObject, java.util.Map, java.lang.String)
     */
    public HtmlData getReturnUrl(BusinessObject businessObject, LookupForm lookupForm, List returnKeys, BusinessObjectRestrictions businessObjectRestrictions) {
        Properties parameters = getParameters(
                businessObject, lookupForm.getFieldConversions(), lookupForm.getLookupableImplServiceName(), returnKeys);
        if (StringUtils.isEmpty(lookupForm.getHtmlDataType()) || HtmlData.ANCHOR_HTML_DATA_TYPE.equals(lookupForm.getHtmlDataType()))
            return getReturnAnchorHtmlData(businessObject, parameters, lookupForm, returnKeys, businessObjectRestrictions);
        else
            return getReturnInputHtmlData(businessObject, parameters, lookupForm, returnKeys, businessObjectRestrictions);
    }

    protected HtmlData getReturnInputHtmlData(BusinessObject businessObject, Properties parameters, LookupForm lookupForm, List returnKeys, BusinessObjectRestrictions businessObjectRestrictions) {
        String returnUrlAnchorLabel =
                this.getKualiConfigurationService().getPropertyString(TITLE_RETURN_URL_PREPENDTEXT_PROPERTY);
        String name = KNSConstants.MULTIPLE_VALUE_LOOKUP_SELECTED_OBJ_ID_PARAM_PREFIX + lookupForm.getLookupObjectId();
        InputHtmlData input = new InputHtmlData(name, InputHtmlData.CHECKBOX_INPUT_TYPE);
        input.setTitle(HtmlData.getTitleText(returnUrlAnchorLabel, businessObject, returnKeys, businessObjectRestrictions));
        if (((MultipleValueLookupForm) lookupForm).getCompositeObjectIdMap() == null ||
                ((MultipleValueLookupForm) lookupForm).getCompositeObjectIdMap().get(
                        ((PersistableBusinessObject) businessObject).getObjectId()) == null) {
            input.setChecked("");
        } else {
            input.setChecked(InputHtmlData.CHECKBOX_CHECKED_VALUE);
        }
        input.setValue(InputHtmlData.CHECKBOX_CHECKED_VALUE);
        return input;
    }

    protected HtmlData getReturnAnchorHtmlData(BusinessObject businessObject, Properties parameters, LookupForm lookupForm, List returnKeys, BusinessObjectRestrictions businessObjectRestrictions) {
        String returnUrlAnchorLabel =
                this.getKualiConfigurationService().getPropertyString(TITLE_RETURN_URL_PREPENDTEXT_PROPERTY);
        AnchorHtmlData anchor = new AnchorHtmlData(
                getReturnHref(parameters, lookupForm, returnKeys),
                HtmlData.getTitleText(returnUrlAnchorLabel, businessObject, returnKeys, businessObjectRestrictions));
        anchor.setDisplayText(returnUrlAnchorLabel);
        return anchor;
    }

    protected String getReturnHref(Properties parameters, LookupForm lookupForm, List returnKeys) {
        if (StringUtils.isNotBlank(backLocation)) {
            String href = UrlFactory.parameterizeUrl(backLocation, parameters);
            return addToReturnHref(href, lookupForm);
        }
        return "";
    }

    protected String addToReturnHref(String href, LookupForm lookupForm) {
        String lookupAnchor = "";
        if (StringUtils.isNotEmpty(lookupForm.getAnchor())) {
            lookupAnchor = lookupForm.getAnchor();
        }
        href += "&anchor=" + lookupAnchor + "&docNum=" + (StringUtils.isEmpty(getDocNum()) ? "" : getDocNum());
        return href;
    }

    protected Properties getParameters(BusinessObject bo, Map fieldConversions, String lookupImpl, List returnKeys) {
        Properties parameters = new Properties();
        parameters.put(KNSConstants.DISPATCH_REQUEST_PARAMETER, KNSConstants.RETURN_METHOD_TO_CALL);
        if (getDocFormKey() != null) {
            parameters.put(KNSConstants.DOC_FORM_KEY, getDocFormKey());
        }
        if (lookupImpl != null) {
            parameters.put(KNSConstants.REFRESH_CALLER, lookupImpl);
        }
        if (getDocNum() != null) {
            parameters.put(KNSConstants.DOC_NUM, getDocNum());
        }

        if (getReferencesToRefresh() != null) {
            parameters.put(KNSConstants.REFERENCES_TO_REFRESH, getReferencesToRefresh());
        }

        Iterator returnKeysIt = getReturnKeys().iterator();
        while (returnKeysIt.hasNext()) {
            String fieldNm = (String) returnKeysIt.next();

            Object fieldVal = ObjectUtils.getPropertyValue(bo, fieldNm);
            if (fieldVal == null) {
                fieldVal = KNSConstants.EMPTY_STRING;
            }

            if (getBusinessObjectAuthorizationService().attributeValueNeedsToBeEncryptedOnFormsAndLinks(businessObjectClass, fieldNm)) {
                try {
                    fieldVal = getEncryptionService().encrypt(fieldVal) + EncryptionService.ENCRYPTION_POST_PREFIX;
                } catch (GeneralSecurityException e) {
                    LOG.error("Exception while trying to encrypted value for inquiry framework.", e);
                    throw new RuntimeException(e);
                }

            }

            //need to format date in url
            if (fieldVal instanceof Date) {
                DateFormatter dateFormatter = new DateFormatter();
                fieldVal = dateFormatter.format(fieldVal);
            }

            if (fieldConversions.containsKey(fieldNm)) {
                fieldNm = (String) fieldConversions.get(fieldNm);
            }

            parameters.put(fieldNm, fieldVal.toString());
        }

        return parameters;
    }

    /**
     * @return a List of the names of fields which are marked in data dictionary as return fields.
     */
    public List getReturnKeys() {
        List returnKeys;
        if (fieldConversions != null && !fieldConversions.isEmpty()) {
            returnKeys = new ArrayList(fieldConversions.keySet());
        } else {
            returnKeys = getBusinessObjectMetaDataService().listPrimaryKeyFieldNames(getBusinessObjectClass());
        }

        return returnKeys;
    }

    /**
     * Gets the docFormKey attribute.
     *
     * @return Returns the docFormKey.
     */
    public String getDocFormKey() {
        return docFormKey;
    }

    /**
     * Sets the docFormKey attribute value.
     *
     * @param docFormKey The docFormKey to set.
     */
    public void setDocFormKey(String docFormKey) {
        this.docFormKey = docFormKey;
    }

    /**
     * @see org.kuali.core.lookup.LookupableHelperService#setFieldConversions(java.util.Map)
     */
    public void setFieldConversions(Map fieldConversions) {
        this.fieldConversions = fieldConversions;
    }

    /**
     * Gets the lookupService attribute.
     *
     * @return Returns the lookupService.
     */
    protected LookupService getLookupService() {
        return lookupService != null ? lookupService : KNSServiceLocatorWeb.getLookupService();
    }

    /**
     * Sets the lookupService attribute value.
     *
     * @param lookupService The lookupService to set.
     */
    public void setLookupService(LookupService lookupService) {
        this.lookupService = lookupService;
    }

    /**
     * Uses the DD to determine which is the default sort order.
     *
     * @return property names that will be used to sort on by default
     */
    public List getDefaultSortColumns() {
        return getBusinessObjectDictionaryService().getLookupDefaultSortFieldNames(getBusinessObjectClass());
    }

    /**
     * Checks that any required search fields have value.
     *
     * @see org.kuali.core.lookup.LookupableHelperService#validateSearchParameters(java.util.Map)
     */
    public void validateSearchParameters(Map fieldValues) {
        List<String> lookupFieldAttributeList = null;
        if (getBusinessObjectMetaDataService().isLookupable(getBusinessObjectClass())) {
            lookupFieldAttributeList = getBusinessObjectMetaDataService().getLookupableFieldNames(getBusinessObjectClass());
        }
        if (lookupFieldAttributeList == null) {
            throw new RuntimeException("Lookup not defined for business object " + getBusinessObjectClass());
        }
        for (Iterator iter = lookupFieldAttributeList.iterator(); iter.hasNext();) {
            String attributeName = (String) iter.next();
            if (fieldValues.containsKey(attributeName)) {
                // get label of attribute for message
                String attributeLabel = getDataDictionaryService().getAttributeLabel(getBusinessObjectClass(), attributeName);

                String attributeValue = (String) fieldValues.get(attributeName);

                // check for required if field does not have value
                if (StringUtils.isBlank(attributeValue)) {
                    if ((getBusinessObjectDictionaryService().getLookupAttributeRequired(getBusinessObjectClass(), attributeName)).booleanValue()) {
                        GlobalVariables.getMessageMap().putError(attributeName, RiceKeyConstants.ERROR_REQUIRED, attributeLabel);
                    }
                }
                validateSearchParameterWildcardAndOperators(attributeName, attributeValue);
            }
        }

        if (GlobalVariables.getMessageMap().hasErrors()) {
            throw new ValidationException("errors in search criteria");
        }
    }

    protected void validateSearchParameterWildcardAndOperators(String attributeName, String attributeValue) {
        if (StringUtils.isBlank(attributeValue))
            return;

        // make sure a wildcard/operator is in the value
        boolean found = false;
        for (int i = 0; i < KNSConstants.QUERY_CHARACTERS.length; i++) {
            String queryCharacter = KNSConstants.QUERY_CHARACTERS[i];

            if (attributeValue.contains(queryCharacter)) {
                found = true;
            }
        }
        if (!found)
            return;

        String attributeLabel = getDataDictionaryService().getAttributeLabel(getBusinessObjectClass(), attributeName);
        if (getBusinessObjectDictionaryService().isLookupFieldTreatWildcardsAndOperatorsAsLiteral(businessObjectClass, attributeName)) {
            BusinessObject example = null;
            try {
                example = (BusinessObject) businessObjectClass.newInstance();
            } catch (Exception e) {
                LOG.error("Exception caught instantiating " + businessObjectClass.getName(), e);
                throw new RuntimeException("Cannot instantiate " + businessObjectClass.getName(), e);
            }

            Class propertyType = ObjectUtils.getPropertyType(example, attributeName, getPersistenceStructureService());
            if (TypeUtils.isIntegralClass(propertyType) || TypeUtils.isDecimalClass(propertyType) || TypeUtils.isTemporalClass(propertyType)) {
                GlobalVariables.getMessageMap().putError(attributeName, RiceKeyConstants.ERROR_WILDCARDS_AND_OPERATORS_NOT_ALLOWED_ON_FIELD, attributeLabel);
            }
            if (TypeUtils.isStringClass(propertyType)) {
                GlobalVariables.getMessageMap().putInfo(attributeName, RiceKeyConstants.INFO_WILDCARDS_AND_OPERATORS_TREATED_LITERALLY, attributeLabel);
            }
        } else {
            if (getBusinessObjectAuthorizationService().attributeValueNeedsToBeEncryptedOnFormsAndLinks(businessObjectClass, attributeName)) {
                if (!attributeValue.endsWith(EncryptionService.ENCRYPTION_POST_PREFIX)) {
                    // encrypted values usually come from the DB, so we don't need to filter for wildcards

                    // wildcards are not allowed on restricted fields, because they are typically encrypted, and wildcard searches cannot be performed without
                    // decrypting every row, which is currently not supported by KNS

                    GlobalVariables.getMessageMap().putError(attributeName, RiceKeyConstants.ERROR_SECURE_FIELD, attributeLabel);
                }
            }
        }
    }

    /**
     * Constructs the list of rows for the search fields. All properties for the field objects come
     * from the DataDictionary. To be called by setBusinessObject
     */
    protected void setRows() {
        List<String> lookupFieldAttributeList = null;
        if (getBusinessObjectMetaDataService().isLookupable(getBusinessObjectClass())) {
            lookupFieldAttributeList = getBusinessObjectMetaDataService().getLookupableFieldNames(
                    getBusinessObjectClass());
        }
        if (lookupFieldAttributeList == null) {
            throw new RuntimeException("Lookup not defined for business object " + getBusinessObjectClass());
        }

        // construct field object for each search attribute
        List fields = new ArrayList();
        try {
            fields = FieldUtils.createAndPopulateFieldsForLookup(lookupFieldAttributeList, getReadOnlyFieldsList(),
                    getBusinessObjectClass());
        } catch (InstantiationException e) {
            throw new RuntimeException("Unable to create instance of business object class" + e.getMessage());
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Unable to create instance of business object class" + e.getMessage());
        }

        int numCols = getBusinessObjectDictionaryService().getLookupNumberOfColumns(this.getBusinessObjectClass());

        this.rows = FieldUtils.wrapFields(fields, numCols);
    }

    public List<Row> getRows() {
        return rows;
    }

    public abstract List<? extends BusinessObject> getSearchResults(Map<String, String> fieldValues);

    /**
     * This implementation of this method throws an UnsupportedOperationException, since not every implementation
     * may actually want to use this operation.  Subclasses desiring other behaviors
     * will need to override this.
     *
     * @see org.kuali.core.lookup.LookupableHelperService#getSearchResultsUnbounded(java.util.Map)
     */
    public List<? extends BusinessObject> getSearchResultsUnbounded(Map<String, String> fieldValues) {
        throw new UnsupportedOperationException("Lookupable helper services do not always support getSearchResultsUnbounded");
    }

    /**
     * Performs the lookup and returns a collection of lookup items
     *
     * @param lookupForm
     * @param resultTable
     * @param bounded
     * @return
     */
    public Collection performLookup(LookupForm lookupForm, Collection resultTable, boolean bounded) {
        Map lookupFormFields = lookupForm.getFieldsForLookup();

        setBackLocation((String) lookupForm.getFieldsForLookup().get(KNSConstants.BACK_LOCATION));
        setDocFormKey((String) lookupForm.getFieldsForLookup().get(KNSConstants.DOC_FORM_KEY));
        Collection displayList;

        preprocessDateFields(lookupFormFields);

        Map fieldsForLookup = new HashMap(lookupForm.getFieldsForLookup());
        // call search method to get results
        if (bounded) {
            displayList = getSearchResults(lookupForm.getFieldsForLookup());
        } else {
            displayList = getSearchResultsUnbounded(lookupForm.getFieldsForLookup());
        }

        boolean hasReturnableRow = false;

        List returnKeys = getReturnKeys();
        List pkNames = getBusinessObjectMetaDataService().listPrimaryKeyFieldNames(getBusinessObjectClass());
        Person user = GlobalVariables.getUserSession().getPerson();

        // iterate through result list and wrap rows with return url and action
        // urls
        for (Iterator iter = displayList.iterator(); iter.hasNext();) {
            BusinessObject element = (BusinessObject) iter.next();

            final String lookupId = KNSServiceLocatorWeb.getLookupResultsService().getLookupId(element);
            if (lookupId != null) {
                lookupForm.setLookupObjectId(lookupId);
            }

            BusinessObjectRestrictions businessObjectRestrictions = getBusinessObjectAuthorizationService()
                    .getLookupResultRestrictions(element, user);

            HtmlData returnUrl = getReturnUrl(element, lookupForm, returnKeys, businessObjectRestrictions);
            String actionUrls = getActionUrls(element, pkNames, businessObjectRestrictions);
            // Fix for JIRA - KFSMI-2417
            if ("".equals(actionUrls)) {
                actionUrls = ACTION_URLS_EMPTY;
            }

            List<Column> columns = getColumns();
            for (Iterator iterator = columns.iterator(); iterator.hasNext();) {
                Column col = (Column) iterator.next();

                String propValue = ObjectUtils.getFormattedPropertyValue(element, col.getPropertyName(), col.getFormatter());
                Class propClass = getPropertyClass(element, col.getPropertyName());

                col.setComparator(CellComparatorHelper.getAppropriateComparatorForPropertyClass(propClass));
                col.setValueComparator(CellComparatorHelper.getAppropriateValueComparatorForPropertyClass(propClass));

                String propValueBeforePotientalMasking = propValue;
                propValue = maskValueIfNecessary(element.getClass(), col.getPropertyName(), propValue,
                        businessObjectRestrictions);
                col.setPropertyValue(propValue);

                // if property value is masked, don't display additional or alternate properties, or allow totals
                if (StringUtils.equals(propValueBeforePotientalMasking, propValue)) {
                    if (StringUtils.isNotBlank(col.getAlternateDisplayPropertyName())) {
                        String alternatePropertyValue = ObjectUtils.getFormattedPropertyValue(element, col
                                .getAlternateDisplayPropertyName(), null);
                        col.setPropertyValue(alternatePropertyValue);
                    }

                    if (StringUtils.isNotBlank(col.getAdditionalDisplayPropertyName())) {
                        String additionalPropertyValue = ObjectUtils.getFormattedPropertyValue(element, col
                                .getAdditionalDisplayPropertyName(), null);
                        col.setPropertyValue(col.getPropertyValue() + " *-* " + additionalPropertyValue);
                    }
                } else {
                    col.setTotal(false);
                }

                if (col.isTotal()) {
                    Object unformattedPropValue = ObjectUtils.getPropertyValue(element, col.getPropertyName());
                    col.setUnformattedPropertyValue(unformattedPropValue);
                }

                if (StringUtils.isNotBlank(propValue)) {
                    col.setColumnAnchor(getInquiryUrl(element, col.getPropertyName()));
                }
            }

            ResultRow row = new ResultRow(columns, returnUrl.constructCompleteHtmlTag(), actionUrls);
            row.setRowId(returnUrl.getName());
            row.setReturnUrlHtmlData(returnUrl);

            // because of concerns of the BO being cached in session on the
            // ResultRow,
            // let's only attach it when needed (currently in the case of
            // export)
            if (getBusinessObjectDictionaryService().isExportable(getBusinessObjectClass())) {
                row.setBusinessObject(element);
            }

            if (lookupId != null) {
                row.setObjectId(lookupId);
            }

            boolean rowReturnable = isResultReturnable(element);
            row.setRowReturnable(rowReturnable);
            if (rowReturnable) {
                hasReturnableRow = true;
            }
            resultTable.add(row);
        }

        lookupForm.setHasReturnableRow(hasReturnableRow);

        return displayList;
    }

    /**
     * Gets the Class for the property in the given BusinessObject instance, if
     * property is not accessible then runtime exception is thrown
     *
     * @param element      BusinessObject instance that contains property
     * @param propertyName Name of property in BusinessObject to get class for
     * @return Type for property as Class
     */
    protected Class getPropertyClass(BusinessObject element, String propertyName) {
        Class propClass = null;

        try {
            propClass = ObjectUtils.getPropertyType(element, propertyName, getPersistenceStructureService());

        } catch (Exception e) {
            throw new RuntimeException("Cannot access PropertyType for property " + "'" + propertyName + "' "
                    + " on an instance of '" + element.getClass().getName() + "'.", e);
        }

        return propClass;
    }

    /**
     * changes from/to dates into the range operators the lookupable dao expects ("..",">" etc)
     * this method modifies the passed in map and returns a list containing only the modified fields
     *
     * @param lookupFormFields
     */
    protected Map<String, String> preprocessDateFields(Map lookupFormFields) {
        Map<String, String> fieldsToUpdate = new HashMap<String, String>();
        Set<String> fieldsForLookup = lookupFormFields.keySet();
        for (String propName : fieldsForLookup) {
            if (propName.startsWith(KNSConstants.LOOKUP_RANGE_LOWER_BOUND_PROPERTY_PREFIX)) {
                String fromDateValue = (String) lookupFormFields.get(propName);
                String dateFieldName = StringUtils.remove(propName, KNSConstants.LOOKUP_RANGE_LOWER_BOUND_PROPERTY_PREFIX);
                String dateValue = (String) lookupFormFields.get(dateFieldName);
                String newPropValue = dateValue;//maybe clean above with ObjectUtils.clean(propertyValue)
                if (StringUtils.isNotEmpty(fromDateValue) && StringUtils.isNotEmpty(dateValue)) {
                    newPropValue = fromDateValue + KNSConstants.BETWEEN_OPERATOR + dateValue;
                } else if (StringUtils.isNotEmpty(fromDateValue) && StringUtils.isEmpty(dateValue)) {
                    newPropValue = ">=" + fromDateValue;
                } else if (StringUtils.isNotEmpty(dateValue) && StringUtils.isEmpty(fromDateValue)) {
                    newPropValue = "<=" + dateValue;
                } //could optionally continue on else here

                fieldsToUpdate.put(dateFieldName, newPropValue);
            }
        }
        //update lookup values from found date values to update
        Set<String> keysToUpdate = fieldsToUpdate.keySet();
        for (String updateKey : keysToUpdate) {
            lookupFormFields.put(updateKey, fieldsToUpdate.get(updateKey));
        }
        return fieldsToUpdate;
    }

    protected String maskValueIfNecessary(Class businessObjectClass, String propertyName, String propertyValue, BusinessObjectRestrictions businessObjectRestrictions) {
        String maskedPropertyValue = propertyValue;
        if (businessObjectRestrictions != null) {
            FieldRestriction fieldRestriction = businessObjectRestrictions.getFieldRestriction(propertyName);
            if (fieldRestriction != null && (fieldRestriction.isMasked() || fieldRestriction.isPartiallyMasked())) {
                maskedPropertyValue = fieldRestriction.getMaskFormatter().maskValue(propertyValue);
            }
        }
        return maskedPropertyValue;
    }


    protected void setReferencesToRefresh(String referencesToRefresh) {
        this.referencesToRefresh = referencesToRefresh;
    }

    public String getReferencesToRefresh() {
        return referencesToRefresh;
    }

    protected SequenceAccessorService getSequenceAccessorService() {
        return sequenceAccessorService != null ? sequenceAccessorService : KNSServiceLocator.getSequenceAccessorService();
    }

    public void setSequenceAccessorService(SequenceAccessorService sequenceAccessorService) {
        this.sequenceAccessorService = sequenceAccessorService;
    }

    public BusinessObjectService getBusinessObjectService() {
        return businessObjectService != null ? businessObjectService : KNSServiceLocator.getBusinessObjectService();
    }

    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

    protected LookupResultsService getLookupResultsService() {
        return lookupResultsService != null ? lookupResultsService : KNSServiceLocatorWeb.getLookupResultsService();
    }

    public void setLookupResultsService(LookupResultsService lookupResultsService) {
        this.lookupResultsService = lookupResultsService;
    }

    /**
     * @return false always, subclasses should override to do something smarter
     * @see org.kuali.core.lookup.LookupableHelperService#isSearchUsingOnlyPrimaryKeyValues()
     */
    public boolean isSearchUsingOnlyPrimaryKeyValues() {
        // by default, this implementation returns false, as lookups may not necessarily support this
        return false;
    }

    /**
     * Returns "N/A"
     *
     * @return "N/A"
     * @see org.kuali.core.lookup.LookupableHelperService#getPrimaryKeyFieldLabels()
     */
    public String getPrimaryKeyFieldLabels() {
        return KNSConstants.NOT_AVAILABLE_STRING;
    }

    /**
     * @see org.kuali.core.lookup.LookupableHelperService#isResultReturnable(org.kuali.core.bo.BusinessObject)
     */
    public boolean isResultReturnable(BusinessObject object) {
        return true;
    }

    /**
     * This method does the logic for the clear action.
     *
     * @see org.kuali.rice.kns.lookup.LookupableHelperService#performClear()
     */
    public void performClear(LookupForm lookupForm) {
        for (Iterator iter = this.getRows().iterator(); iter.hasNext();) {
            Row row = (Row) iter.next();
            for (Iterator iterator = row.getFields().iterator(); iterator.hasNext();) {
                Field field = (Field) iterator.next();
                if (field.isSecure()) {
                    field.setSecure(false);
                    field.setDisplayMaskValue(null);
                    field.setEncryptedValue(null);
                }

                if (!field.getFieldType().equals(Field.RADIO)) {
                    field.setPropertyValue(field.getDefaultValue());
                }
            }
        }
    }

    /**
     * @see org.kuali.rice.kns.lookup.LookupableHelperService#shouldDisplayHeaderNonMaintActions()
     */
    public boolean shouldDisplayHeaderNonMaintActions() {
        return true;
    }

    /**
     * @see org.kuali.rice.kns.lookup.LookupableHelperService#shouldDisplayLookupCriteria()
     */
    public boolean shouldDisplayLookupCriteria() {
        return true;
    }

    /**
     * @see org.kuali.rice.kns.lookup.LookupableHelperService#getSupplementalMenuBar()
     */
    public String getSupplementalMenuBar() {
        return new String();
    }

    /**
     * @see org.kuali.rice.kns.lookup.LookupableHelperService#getTitle()
     */
    public String getTitle() {
        return getBusinessObjectDictionaryService().getLookupTitle(getBusinessObjectClass());
    }

    /**
     * @see org.kuali.rice.kns.lookup.LookupableHelperService#performCustomAction(boolean)
     */
    public boolean performCustomAction(boolean ignoreErrors) {
        return false;
    }

    /**
     * @see org.kuali.rice.kns.lookup.Lookupable#getExtraField()
     */
    public Field getExtraField() {
        return null;
    }

    public boolean allowsNewOrCopyAction(String documentTypeName) {
        throw new UnsupportedOperationException("Function not supported.");
    }

    /**
     * Functional requirements state that users are able to perform searches using criteria values that they are not allowed to view.
     *
     * @see org.kuali.rice.kns.lookup.LookupableHelperService#applyFieldAuthorizationsFromNestedLookups(org.kuali.rice.kns.web.ui.Field)
     */
    public void applyFieldAuthorizationsFromNestedLookups(Field field) {
        BusinessObjectAuthorizationService boAuthzService = this.getBusinessObjectAuthorizationService();
        if (!Field.MULTI_VALUE_FIELD_TYPES.contains(field.getFieldType())) {
            if (field.getPropertyValue() != null && field.getPropertyValue().endsWith(EncryptionService.ENCRYPTION_POST_PREFIX)) {
                if (boAuthzService.attributeValueNeedsToBeEncryptedOnFormsAndLinks(businessObjectClass, field.getPropertyName())) {
                    AttributeSecurity attributeSecurity = getDataDictionaryService().getAttributeSecurity(businessObjectClass.getName(), field.getPropertyName());
                    Person user = GlobalVariables.getUserSession().getPerson();
                    String decryptedValue;
                    try {
                        String cipherText = StringUtils.removeEnd(field.getPropertyValue(), EncryptionService.ENCRYPTION_POST_PREFIX);
                        decryptedValue = getEncryptionService().decrypt(cipherText);
                    } catch (GeneralSecurityException e) {
                        throw new RuntimeException("Error decrypting value for business object " + businessObjectClass + " attribute " + field.getPropertyName(), e);
                    }
                    if (attributeSecurity.isMask() && !boAuthzService.canFullyUnmaskField(user,
                            businessObjectClass, field.getPropertyName(), null)) {
                        MaskFormatter maskFormatter = attributeSecurity.getMaskFormatter();
                        field.setEncryptedValue(field.getPropertyValue());
                        field.setDisplayMaskValue(maskFormatter.maskValue(decryptedValue));
                        field.setSecure(true);
                    } else if (attributeSecurity.isPartialMask() && !boAuthzService.canPartiallyUnmaskField(user,
                            businessObjectClass, field.getPropertyName(), null)) {
                        MaskFormatter maskFormatter = attributeSecurity.getPartialMaskFormatter();
                        field.setEncryptedValue(field.getPropertyValue());
                        field.setDisplayMaskValue(maskFormatter.maskValue(decryptedValue));
                        field.setSecure(true);
                    } else {
                        field.setPropertyValue(LookupUtils.forceUppercase(businessObjectClass, field.getPropertyName(), decryptedValue));
                    }
                } else {
                    throw new RuntimeException("Field " + field.getPersonNameAttributeName() + " was encrypted on " + businessObjectClass.getName() +
                            " lookup was encrypted when it should not have been encrypted according to the data dictionary.");
                }
            }
        } else {
            if (boAuthzService.attributeValueNeedsToBeEncryptedOnFormsAndLinks(businessObjectClass, field.getPropertyName())) {
                LOG.error("Cannot handle multiple value field types that have field authorizations, please implement custom lookupable helper service");
                throw new RuntimeException("Cannot handle multiple value field types that have field authorizations.");
            }
        }
    }

    /**
     * Calls methods that can be overridden by child lookupables to implement conditional logic for setting
     * read-only, required, and hidden attributes. Called in the last part of the lookup lifecycle so the
     * fields values that will be sent will be correctly reflected in the rows (like after a clear).
     *
     * @see #getConditionallyReadOnlyPropertyNames()
     * @see #getConditionallyRequiredPropertyNames()
     * @see #getConditionallyHiddenPropertyNames()
     * @see org.kuali.rice.kns.lookup.LookupableHelperService#applyConditionalLogicForFieldDisplay()
     */
    public void applyConditionalLogicForFieldDisplay() {
        Set<String> readOnlyFields = getConditionallyReadOnlyPropertyNames();
        Set<String> requiredFields = getConditionallyRequiredPropertyNames();
        Set<String> hiddenFields = getConditionallyHiddenPropertyNames();

        for (Iterator iter = this.getRows().iterator(); iter.hasNext();) {
            Row row = (Row) iter.next();
            for (Iterator iterator = row.getFields().iterator(); iterator.hasNext();) {
                Field field = (Field) iterator.next();

                if (readOnlyFields != null && readOnlyFields.contains(field.getPropertyName())) {
                    field.setReadOnly(true);
                }

                if (requiredFields != null && requiredFields.contains(field.getPropertyName())) {
                    field.setFieldRequired(true);
                }

                if (hiddenFields != null && hiddenFields.contains(field.getPropertyName())) {
                    field.setFieldType(Field.HIDDEN);
                }
            }
        }
    }

    /**
     * @return Set of property names that should be set as read only based on the current search
     *         contents, note request parms containing search field values can be retrieved with
     *         {@link #getParameters()}
     */
    public Set<String> getConditionallyReadOnlyPropertyNames() {
        return new HashSet<String>();
    }

    /**
     * @return Set of property names that should be set as required based on the current search
     *         contents, note request parms containing search field values can be retrieved with
     *         {@link #getParameters()}
     */
    public Set<String> getConditionallyRequiredPropertyNames() {
        return new HashSet<String>();
    }

    /**
     * @return Set of property names that should be set as hidden based on the current search
     *         contents, note request parms containing search field values can be retrieved with
     *         {@link #getParameters()}
     */
    public Set<String> getConditionallyHiddenPropertyNames() {
        return new HashSet<String>();
    }

    /**
     * Helper method to get the value for a property out of the row-field graph. If property is
     * multi-value then the values will be joined by a semi-colon.
     *
     * @param propertyName - name of property to retrieve value for
     * @return current property value as a String
     */
    protected String getCurrentSearchFieldValue(String propertyName) {
        String currentValue = null;

        boolean fieldFound = false;
        for (Iterator iter = this.getRows().iterator(); iter.hasNext();) {
            Row row = (Row) iter.next();
            for (Iterator iterator = row.getFields().iterator(); iterator.hasNext();) {
                Field field = (Field) iterator.next();

                if (StringUtils.equalsIgnoreCase(propertyName, field.getPropertyName())) {
                    if (Field.MULTI_VALUE_FIELD_TYPES.contains(field.getFieldType())) {
                        currentValue = StringUtils.join(field.getPropertyValues(), ";");
                    } else {
                        currentValue = field.getPropertyValue();
                    }
                    fieldFound = true;
                }

                if (fieldFound) {
                    break;
                }
            }

            if (fieldFound) {
                break;
            }
        }

        return currentValue;
    }
}

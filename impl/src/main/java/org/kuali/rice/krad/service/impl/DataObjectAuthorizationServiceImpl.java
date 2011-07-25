/*
 * Copyright 2007 The Kuali Foundation
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
package org.kuali.rice.krad.service.impl;

import org.kuali.rice.kim.bo.Person;
import org.kuali.rice.krad.datadictionary.AttributeSecurity;
import org.kuali.rice.krad.document.authorization.DocumentAuthorizer;
import org.kuali.rice.krad.document.authorization.DocumentPresentationController;
import org.kuali.rice.krad.document.authorization.MaintenanceDocumentAuthorizer;
import org.kuali.rice.krad.document.authorization.MaintenanceDocumentPresentationController;
import org.kuali.rice.krad.service.DataDictionaryService;
import org.kuali.rice.krad.service.DataObjectAuthorizationService;
import org.kuali.rice.krad.service.DocumentHelperService;
import org.kuali.rice.krad.service.KRADServiceLocatorWeb;

/**
 * Implementation of <code>DataObjectAuthorizationService</code> that uses the
 * configured <code>AttributeSecurity</code> for a field to determine authorization
 * checks that need to be performed
 *
 * @author Kuali Rice Team (rice.collab@kuali.org)
 */
public class DataObjectAuthorizationServiceImpl implements DataObjectAuthorizationService {

    private DataDictionaryService dataDictionaryService;
    private DocumentHelperService documentHelperService;

    /**
     * @see org.kuali.rice.krad.service.impl.DataObjectAuthorizationServiceImpl#attributeValueNeedsToBeEncryptedOnFormsAndLinks
     */
    @Override
    public boolean attributeValueNeedsToBeEncryptedOnFormsAndLinks(Class<?> dataObjectClass, String attributeName) {
        AttributeSecurity attributeSecurity =
                getDataDictionaryService().getAttributeSecurity(dataObjectClass.getName(), attributeName);

        return attributeSecurity != null && attributeSecurity.hasRestrictionThatRemovesValueFromUI();
    }

    /**
     * @see org.kuali.rice.krad.service.impl.DataObjectAuthorizationServiceImpl#canCreate
     */
    @Override
    public boolean canCreate(Class<?> dataObjectClass, Person user, String docTypeName) {
        DocumentPresentationController documentPresentationController =
                getDocumentHelperService().getDocumentPresentationController(docTypeName);
        boolean canCreate =
                ((MaintenanceDocumentPresentationController) documentPresentationController).canCreate(dataObjectClass);
        if (canCreate) {
            DocumentAuthorizer documentAuthorizer = getDocumentHelperService().getDocumentAuthorizer(docTypeName);
            canCreate = ((MaintenanceDocumentAuthorizer) documentAuthorizer).canCreate(dataObjectClass, user);
        }
        return canCreate;
    }

    /**
     * @see org.kuali.rice.krad.service.impl.DataObjectAuthorizationServiceImpl#canMaintain
     */
    @Override
    public boolean canMaintain(Object dataObject, Person user, String docTypeName) {
        return ((MaintenanceDocumentAuthorizer) getDocumentHelperService().getDocumentAuthorizer(docTypeName))
                .canMaintain(dataObject, user);
    }

    protected DataDictionaryService getDataDictionaryService() {
        if (dataDictionaryService == null) {
            this.dataDictionaryService = KRADServiceLocatorWeb.getDataDictionaryService();
        }
        return dataDictionaryService;
    }

    public void setDataDictionaryService(DataDictionaryService dataDictionaryService) {
        this.dataDictionaryService = dataDictionaryService;
    }

    protected DocumentHelperService getDocumentHelperService() {
        if (documentHelperService == null) {
            documentHelperService = KRADServiceLocatorWeb.getDocumentHelperService();
        }
        return documentHelperService;
    }

    public void setDocumentHelperService(DocumentHelperService documentHelperService) {
        this.documentHelperService = documentHelperService;
    }
}

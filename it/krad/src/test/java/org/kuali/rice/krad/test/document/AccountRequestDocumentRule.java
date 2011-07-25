/*
 * Copyright 2007-2008 The Kuali Foundation
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
package org.kuali.rice.krad.test.document;

import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.rules.TransactionalDocumentRuleBase;
import org.kuali.rice.krad.service.KRADServiceLocatorWeb;
import org.kuali.rice.krad.util.GlobalVariables;

public class AccountRequestDocumentRule extends TransactionalDocumentRuleBase {

    @Override
    protected boolean processCustomSaveDocumentBusinessRules(Document document) {
        if (!(document instanceof AccountRequestDocument)) {
            return false;
        }

        boolean valid = true;

        GlobalVariables.getMessageMap().addToErrorPath("document");

        KRADServiceLocatorWeb.getDictionaryValidationService().validateDocument(document);

        GlobalVariables.getMessageMap().removeFromErrorPath("document");

        return valid;
    }
}

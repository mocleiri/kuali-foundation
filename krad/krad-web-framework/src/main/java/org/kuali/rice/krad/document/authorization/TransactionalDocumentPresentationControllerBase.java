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
package org.kuali.rice.krad.document.authorization;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.rice.krad.document.Document;

import java.util.HashSet;
import java.util.Set;

/**
 * Base class for all TransactionalDocumentPresentationControllers.
 */
public class TransactionalDocumentPresentationControllerBase extends DocumentPresentationControllerBase implements TransactionalDocumentPresentationController {
    private static Log LOG = LogFactory.getLog(TransactionalDocumentPresentationControllerBase.class);
    
    /**
     * 
     * @see DocumentPresentationController#getEditMode(org.kuali.rice.krad.document.Document)
     */
    public Set<String> getEditModes(Document document){
    	Set<String> editModes = new HashSet();
    	return editModes;
    }
}

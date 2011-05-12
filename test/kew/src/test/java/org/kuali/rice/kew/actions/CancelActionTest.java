/*
 * Copyright 2005-2007 The Kuali Foundation
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
package org.kuali.rice.kew.actions;

import org.junit.Test;
import org.kuali.rice.kew.actionitem.ActionItem;
import org.kuali.rice.kew.actions.BlanketApproveTest.NotifySetup;

import org.kuali.rice.kew.service.KEWServiceLocator;
import org.kuali.rice.kew.service.WorkflowDocument;
import org.kuali.rice.kew.test.KEWTestCase;

import java.util.Collection;
import java.util.List;

import static org.junit.Assert.*;


public class CancelActionTest extends KEWTestCase {

    protected void loadTestData() throws Exception {
        loadXmlFile("ActionsConfig.xml");
    }

    @Test public void testCancel() throws Exception {
        WorkflowDocument document =WorkflowDocument.createDocument(getPrincipalIdForName("ewestfal"), NotifySetup.DOCUMENT_TYPE_NAME);
        document.routeDocument("");
        
        document = WorkflowDocument.loadDocument(getPrincipalIdForName("jhopf"), document.getDocumentId());
        document.approve("");
        
        document = WorkflowDocument.loadDocument(getPrincipalIdForName("ewestfal"), document.getDocumentId());
        document.approve("");//ewestfal had force action rule
        
        document = WorkflowDocument.loadDocument(getPrincipalIdForName("rkirkend"), document.getDocumentId());
        document.approve("");
        
        //this be the role delegate of jitrue
        document = WorkflowDocument.loadDocument(getPrincipalIdForName("natjohns"), document.getDocumentId());
        document.approve("");
        
        document = WorkflowDocument.loadDocument(getPrincipalIdForName("bmcgough"), document.getDocumentId());
        document.cancel("");
        
        assertTrue("Document should be disapproved", document.stateIsCanceled());

        //verify that the document is truly dead - no more action requests or action items.
        
        List requests = KEWServiceLocator.getActionRequestService().findPendingByDoc(document.getDocumentId());
        assertEquals("Should not have any active requests", 0, requests.size());
        
        Collection<ActionItem> actionItems = KEWServiceLocator.getActionListService().findByDocumentId(document.getDocumentId());
        assertEquals("Should not have any action items", 0, actionItems.size());
        
        
    }

    @Test public void testInitiatorOnlyCancel() throws Exception {
        WorkflowDocument document = WorkflowDocument.createDocument(getPrincipalIdForName("ewestfal"), NotifySetup.DOCUMENT_TYPE_NAME);
        
        document = WorkflowDocument.loadDocument(getPrincipalIdForName("user1"), document.getDocumentId());
        try {
            document.cancel("");
            fail("Document should not be allowed to be cancelled due to initiator check.");
        } catch (Exception e) {
            
        }
    }
}

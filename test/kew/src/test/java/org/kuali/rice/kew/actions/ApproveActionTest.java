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

import org.kuali.rice.kew.service.WorkflowDocument;
import org.kuali.rice.kew.test.KEWTestCase;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ApproveActionTest extends KEWTestCase {

    protected void loadTestData() throws Exception {
        loadXmlFile("ActionsConfig.xml");
    }
    
    @Test public void testPreapprovals() throws Exception {
    	WorkflowDocument doc = WorkflowDocument.createDocument(getPrincipalIdForName("rkirkend"), "PreApprovalTest");
    	doc.routeDocument("");
    	
    	//rock some preapprovals and other actions... 
    	doc = WorkflowDocument.loadDocument(getPrincipalIdForName("ewestfal"), doc.getDocumentId());
    	doc.approve("");
    	
    	doc = WorkflowDocument.loadDocument(getPrincipalIdForName("user2"), doc.getDocumentId());
    	doc.acknowledge("");
    	
    	doc = WorkflowDocument.loadDocument(getPrincipalIdForName("user3"), doc.getDocumentId());
    	doc.complete("");
    	
    	//approve as the person the doc is routed to so we can move the documen on and hopefully to final
    	doc = WorkflowDocument.loadDocument(getPrincipalIdForName("user1"), doc.getDocumentId());
    	doc.approve("");
    	
    	doc = WorkflowDocument.loadDocument(getPrincipalIdForName("user1"), doc.getDocumentId());
    	assertTrue("the document should be final", doc.stateIsFinal());
    }

    @Test public void testInitiatorRole() throws Exception {
        WorkflowDocument doc = WorkflowDocument.createDocument(getPrincipalIdForName("rkirkend"), "InitiatorRoleApprovalTest");
        doc.routeDocument("");
        //rock some preapprovals and other actions... 
        doc = WorkflowDocument.loadDocument(getPrincipalIdForName("ewestfal"), doc.getDocumentId());
        doc.approve("");
        
        
        doc = WorkflowDocument.loadDocument(getPrincipalIdForName("user2"), doc.getDocumentId());
        doc.acknowledge("");
        
        doc = WorkflowDocument.loadDocument(getPrincipalIdForName("user3"), doc.getDocumentId());
        doc.complete("");

        assertFalse("the document should NOT be final", doc.stateIsFinal());

        //approve as the person the doc is routed (initiator) to so we can move the document on and hopefully to final
        doc = WorkflowDocument.loadDocument(getPrincipalIdForName("rkirkend"), doc.getDocumentId());
        doc.approve("");
        
        assertTrue("the document should be final", doc.stateIsFinal());
    }
}

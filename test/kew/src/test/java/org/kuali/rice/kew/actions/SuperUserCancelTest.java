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
import org.kuali.rice.kew.actions.BlanketApproveTest.NotifySetup;

import org.kuali.rice.kew.service.WorkflowDocument;
import org.kuali.rice.kew.test.KEWTestCase;

import static org.junit.Assert.*;

/**
 * Test SuperUserCancel through WorkflowDocument
 */
public class SuperUserCancelTest extends KEWTestCase {

    protected void loadTestData() throws Exception {
	loadXmlFile("ActionsConfig.xml");
    }

    @Test
    public void testSuperUserCancel() throws Exception {
	WorkflowDocument document = WorkflowDocument.createDocument(getPrincipalIdForName("ewestfal"), NotifySetup.DOCUMENT_TYPE_NAME);
	document.routeDocument("");

	document = WorkflowDocument.loadDocument(getPrincipalIdForName("jhopf"), document.getDocumentId());
	assertTrue("WorkflowDocument should indicate jhopf as SuperUser", document.isSuperUser());
	document.superUserCancel("");
	assertTrue("Document should be final after Super User Cancel", document.stateIsCanceled());
    }

    @Test
    public void testSuperUserInitiatorCancel() throws Exception {
	WorkflowDocument document = WorkflowDocument.createDocument(getPrincipalIdForName("ewestfal"), NotifySetup.DOCUMENT_TYPE_NAME);
	assertTrue("WorkflowDocument should indicate ewestfal as SuperUser", document.isSuperUser());
	document.superUserCancel("");
	assertTrue("Document should be final after Super User Cancel", document.stateIsCanceled());
    }

    @Test
    public void testSuperUserNonInitiatorCancel() throws Exception {
	WorkflowDocument document = WorkflowDocument.createDocument(getPrincipalIdForName("delyea"), NotifySetup.DOCUMENT_TYPE_NAME);
	document = WorkflowDocument.loadDocument(getPrincipalIdForName("ewestfal"), document.getDocumentId());
	assertTrue("WorkflowDocument should indicate ewestfal as SuperUser", document.isSuperUser());
	document.superUserCancel("");
	assertTrue("Document should be final after Super User Cancel", document.stateIsCanceled());
    }

    @Test
    public void testSuperUserCancelInvalidUser() throws Exception {
	WorkflowDocument document = WorkflowDocument.createDocument(getPrincipalIdForName("ewestfal"), NotifySetup.DOCUMENT_TYPE_NAME);
	document.routeDocument("");

	document = WorkflowDocument.loadDocument(getPrincipalIdForName("quickstart"), document.getDocumentId());
	try {
	    assertFalse("WorkflowDocument should not indicate quickstart as SuperUser", document.isSuperUser());
	    document.superUserCancel("");
	    fail("invalid user attempted to SuperUserApprove");
	} catch (Exception e) {
	}

    }

}

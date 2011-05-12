/*
 * Copyright 2005-2008 The Kuali Foundation
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
package org.kuali.rice.kew.engine;

import org.junit.Test;

import org.kuali.rice.kew.service.WorkflowDocument;
import org.kuali.rice.kew.test.KEWTestCase;
import org.kuali.rice.kew.util.KEWConstants;

import static org.junit.Assert.*;

/**
 * Tests a new document being spawned from the post processing of an existing document
 * 
 * @author Kuali Rice Team (rice.collab@kuali.org)
 *
 */
public class PostProcessorSpawnedDocumentTest extends KEWTestCase {
	
	private static final String DOCUMENT_TYPE_THAT_SPAWNS = "SpawnNewDocumentType";

    protected void loadTestData() throws Exception {
        loadXmlFile("PostProcessorSpawnedDocConfig.xml");
    }

    @Test public void testSpawnDocument() throws Exception {
    	WorkflowDocument document = WorkflowDocument.createDocument(getPrincipalIdForName("jitrue"), DOCUMENT_TYPE_THAT_SPAWNS);
    	document.saveRoutingData();
    	assertNotNull(document.getDocumentId());
    	assertTrue("Document should be initiatied", document.stateIsInitiated());
    	document.routeDocument("Route");

        // should have generated a request to "bmcgough"
    	document = WorkflowDocument.loadDocument(getPrincipalIdForName("bmcgough"), document.getDocumentId());
        assertTrue("Document should be enroute", document.stateIsEnroute());
        assertEquals("Document should be enroute.", KEWConstants.ROUTE_HEADER_ENROUTE_CD, document.getRouteHeader().getDocRouteStatus());
        assertTrue(document.isApprovalRequested());
        document.approve("Test approve by bmcgough");
        
        String originalDocumentId = document.getDocumentId();
    	Long originalDocumentIdLong = Long.parseLong(originalDocumentId.trim());
        Long spawnedDocumentIdLong = (originalDocumentIdLong + 1);
        String spawnedDocumentId = spawnedDocumentIdLong.toString();
        
    	// get spawned document (should be next document id)
    	document = WorkflowDocument.loadDocument(getPrincipalIdForName("jhopf"), spawnedDocumentId);
        assertEquals("Document should be final.", KEWConstants.ROUTE_HEADER_FINAL_CD, document.getRouteHeader().getDocRouteStatus());

    	// get original document
        document = WorkflowDocument.loadDocument(getPrincipalIdForName("ewestfal"), originalDocumentId);
        assertEquals("Document should be final.", KEWConstants.ROUTE_HEADER_FINAL_CD, document.getRouteHeader().getDocRouteStatus());
    }
}

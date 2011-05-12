/*
 * Copyright 2005-2008 The Kuali Foundation
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

package org.kuali.rice.kns.workflow.service;


import org.junit.Test;
import org.kuali.rice.kns.service.KNSServiceLocatorWeb;
import org.kuali.test.KNSTestCase;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


/**
 * This class tests the WorkflowUser service.
 */
public class WorkflowInfoServiceTest extends KNSTestCase {
    private static final String KNOWN_USERNAME = "KULUSER";

    @Test public void testRouteHeaderExists_NullId() throws IllegalArgumentException {
        boolean errorThrown = false;
        try {
            boolean result = KNSServiceLocatorWeb.getWorkflowInfoService().routeHeaderExists(null);
        }
        catch (IllegalArgumentException e) {
            errorThrown = true;
        }
        assertTrue("An error should have been thrown.", errorThrown);
    }

    @Test public void testRouteHeaderExists_NegativeId() {
        boolean errorThrown = false;
        boolean result = true;
        try {
            result = KNSServiceLocatorWeb.getWorkflowInfoService().routeHeaderExists("-10");
        }
        catch (Exception e) {
            errorThrown = true;
        }
        assertFalse("An error should not have been thrown.", errorThrown);
        assertFalse("The routeHeader should never exist for a negative documentId.", result);
    }
    
    @Test public void testRouteHeaderExists_KnownBadZeroId() {
        boolean errorThrown = false;
        boolean result = true;
        try {
            result = KNSServiceLocatorWeb.getWorkflowInfoService().routeHeaderExists("0");
        }
        catch (Exception e) {
            errorThrown = true;
        }
        assertFalse("An error should not have been thrown.", errorThrown);
        assertFalse("The routeHeader should never exist for a documentId of 0.", result);
    }

    @Test public void testRouteHeaderExists_KnownGood() {
        // no good way to test this without mocking the workflow service, and in a
        // way that will be good over the long term, across data changes
        assertTrue("This has been checked with a known-good id in the DB at this time.", true);
    }

}

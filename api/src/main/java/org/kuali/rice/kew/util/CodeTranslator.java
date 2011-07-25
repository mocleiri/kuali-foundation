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
package org.kuali.rice.kew.util;

import java.util.HashMap;
import java.util.Map;

import org.kuali.rice.kew.api.action.ActionRequestPolicy;
import org.kuali.rice.kew.api.action.ActionRequestStatus;


/**
 * Utility class to translate the various codes into labels and vice versa.
 *
 * @author Kuali Rice Team (rice.collab@kuali.org)
 */
public class CodeTranslator {
    public static final Map<String, String> arLabels = getArLabels();
    public static final Map<String, String> atLabels = getAtLabels();
    public static final Map<String, String> arStatusLabels = getArStatusLabels();
    public static final Map<String, String> routeStatusLabels = getRouteStatusLabels();
    public static final Map<Boolean, String> activeIndicatorLabels = getActiveIndicatorLabels();
    public static final Map<String, String> activationPolicyLabels = getActivationPolicyLabels();
    public static final Map<String, String> policyLabels = getPolicyLabels();
    public static final Map<String, String> approvePolicyLabels = getApprovePolicyLabels();

    private static Map<String, String> getAtLabels() {
    	Map<String, String> newAtLabels = new HashMap<String, String>();
        newAtLabels.put(KEWConstants.ACTION_TAKEN_ACKNOWLEDGED_CD, KEWConstants.ACTION_TAKEN_ACKNOWLEDGED);
        newAtLabels.put(KEWConstants.ACTION_TAKEN_ADHOC_CD, KEWConstants.ACTION_TAKEN_ADHOC);
        newAtLabels.put(KEWConstants.ACTION_TAKEN_ADHOC_REVOKED_CD, KEWConstants.ACTION_TAKEN_ADHOC_REVOKED);
        newAtLabels.put(KEWConstants.ACTION_TAKEN_APPROVED_CD, KEWConstants.ACTION_TAKEN_APPROVED);
        newAtLabels.put(KEWConstants.ACTION_TAKEN_BLANKET_APPROVE_CD, KEWConstants.ACTION_TAKEN_BLANKET_APPROVE);
        newAtLabels.put(KEWConstants.ACTION_TAKEN_CANCELED_CD, KEWConstants.ACTION_TAKEN_CANCELED);
        newAtLabels.put(KEWConstants.ACTION_TAKEN_COMPLETED_CD, KEWConstants.ACTION_TAKEN_COMPLETED);
        newAtLabels.put(KEWConstants.ACTION_TAKEN_ROUTED_CD, KEWConstants.ACTION_TAKEN_ROUTED);
        newAtLabels.put(KEWConstants.ACTION_TAKEN_DENIED_CD, KEWConstants.ACTION_TAKEN_DENIED);
        newAtLabels.put(KEWConstants.ACTION_TAKEN_FYI_CD, KEWConstants.ACTION_TAKEN_FYI);
        newAtLabels.put(KEWConstants.ACTION_TAKEN_SAVED_CD, KEWConstants.ACTION_TAKEN_SAVED);
        newAtLabels.put(KEWConstants.ACTION_TAKEN_RETURNED_TO_PREVIOUS_CD, KEWConstants.ACTION_TAKEN_RETURNED_TO_PREVIOUS);
        newAtLabels.put(KEWConstants.ACTION_TAKEN_LOG_DOCUMENT_ACTION_CD, KEWConstants.ACTION_TAKEN_LOG_DOCUMENT_ACTION);
        newAtLabels.put(KEWConstants.ACTION_TAKEN_MOVE_CD, KEWConstants.ACTION_TAKEN_MOVE);
        newAtLabels.put(KEWConstants.ACTION_TAKEN_SU_APPROVED_CD, KEWConstants.ACTION_TAKEN_SU_APPROVED);
        newAtLabels.put(KEWConstants.ACTION_TAKEN_SU_CANCELED_CD, KEWConstants.ACTION_TAKEN_SU_CANCELED);
        newAtLabels.put(KEWConstants.ACTION_TAKEN_SU_DISAPPROVED_CD, KEWConstants.ACTION_TAKEN_SU_DISAPPROVED);
        newAtLabels.put(KEWConstants.ACTION_TAKEN_SU_ROUTE_LEVEL_APPROVED_CD, KEWConstants.ACTION_TAKEN_SU_ROUTE_LEVEL_APPROVED);
        newAtLabels.put(KEWConstants.ACTION_TAKEN_SU_ACTION_REQUEST_APPROVED_CD, KEWConstants.ACTION_TAKEN_SU_ACTION_REQUEST_APPROVED);
        newAtLabels.put(KEWConstants.ACTION_TAKEN_SU_RETURNED_TO_PREVIOUS_CD, KEWConstants.ACTION_TAKEN_SU_RETURNED_TO_PREVIOUS);
        newAtLabels.put(KEWConstants.ACTION_TAKEN_SU_ACTION_REQUEST_ACKNOWLEDGED_CD, KEWConstants.ACTION_TAKEN_SU_ACTION_REQUEST_ACKNOWLEDGED);
        newAtLabels.put(KEWConstants.ACTION_TAKEN_SU_ACTION_REQUEST_FYI_CD, KEWConstants.ACTION_TAKEN_SU_ACTION_REQUEST_FYI);
        newAtLabels.put(KEWConstants.ACTION_TAKEN_SU_ACTION_REQUEST_COMPLETED_CD, KEWConstants.ACTION_TAKEN_SU_ACTION_REQUEST_COMPLETED);
        newAtLabels.put(KEWConstants.ACTION_TAKEN_TAKE_WORKGROUP_AUTHORITY_CD, KEWConstants.ACTION_TAKEN_TAKE_WORKGROUP_AUTHORITY);
        newAtLabels.put(KEWConstants.ACTION_TAKEN_RELEASE_WORKGROUP_AUTHORITY_CD, KEWConstants.ACTION_TAKEN_RELEASE_WORKGROUP_AUTHORITY);
        return newAtLabels;
    }

    private static Map<String, String> getArLabels() {
    	Map<String, String> newArLabels = new HashMap<String, String>();
        newArLabels.put(KEWConstants.ACTION_REQUEST_ACKNOWLEDGE_REQ, KEWConstants.ACTION_REQUEST_ACKNOWLEDGE_REQ_LABEL);
        newArLabels.put(KEWConstants.ACTION_REQUEST_APPROVE_REQ, KEWConstants.ACTION_REQUEST_APPROVE_REQ_LABEL);
        newArLabels.put(KEWConstants.ACTION_REQUEST_COMPLETE_REQ, KEWConstants.ACTION_REQUEST_COMPLETE_REQ_LABEL);
        newArLabels.put(KEWConstants.ACTION_REQUEST_FYI_REQ, KEWConstants.ACTION_REQUEST_FYI_REQ_LABEL);
        return newArLabels;
    }

    private static Map<String, String> getArStatusLabels() {
        Map<String, String> newArStatusLabels = new HashMap<String, String>();
        newArStatusLabels.put(ActionRequestStatus.ACTIVATED.getCode(), KEWConstants.ACTIVE_LABEL);
        newArStatusLabels.put(ActionRequestStatus.INITIALIZED.getCode(), ActionRequestStatus.INITIALIZED.getLabel());
        newArStatusLabels.put(ActionRequestStatus.DONE.getCode(), ActionRequestStatus.DONE.getLabel());
        return newArStatusLabels;
    }

    private static Map<String, String> getRouteStatusLabels() {
    	Map<String, String> newRouteStatusLabels = new HashMap<String, String>();
        newRouteStatusLabels.put(KEWConstants.ROUTE_HEADER_CANCEL_CD, KEWConstants.ROUTE_HEADER_CANCEL_LABEL);
        newRouteStatusLabels.put(KEWConstants.ROUTE_HEADER_DISAPPROVED_CD, KEWConstants.ROUTE_HEADER_DISAPPROVED_LABEL);
        newRouteStatusLabels.put(KEWConstants.ROUTE_HEADER_ENROUTE_CD, KEWConstants.ROUTE_HEADER_ENROUTE_LABEL);
        newRouteStatusLabels.put(KEWConstants.ROUTE_HEADER_EXCEPTION_CD, KEWConstants.ROUTE_HEADER_EXCEPTION_LABEL);
        newRouteStatusLabels.put(KEWConstants.ROUTE_HEADER_FINAL_CD, KEWConstants.ROUTE_HEADER_FINAL_LABEL);
        newRouteStatusLabels.put(KEWConstants.ROUTE_HEADER_INITIATED_CD, KEWConstants.ROUTE_HEADER_INITIATED_LABEL);
        newRouteStatusLabels.put(KEWConstants.ROUTE_HEADER_PROCESSED_CD, KEWConstants.ROUTE_HEADER_PROCESSED_LABEL);
        newRouteStatusLabels.put(KEWConstants.ROUTE_HEADER_SAVED_CD, KEWConstants.ROUTE_HEADER_SAVED_LABEL);
        return newRouteStatusLabels;
    }

    private static Map<Boolean, String> getActiveIndicatorLabels() {
    	Map<Boolean, String> newActiveIndicatorLabels = new HashMap<Boolean, String>();
        newActiveIndicatorLabels.put(Boolean.TRUE, KEWConstants.ACTIVE_LABEL_LOWER);
        newActiveIndicatorLabels.put(Boolean.FALSE, KEWConstants.INACTIVE_LABEL_LOWER);
        return newActiveIndicatorLabels;
    }

    private static Map<String, String> getPolicyLabels() {
    	Map<String, String> newDocTypeActiveIndicatorLabels = new HashMap<String, String>();
        newDocTypeActiveIndicatorLabels.put(KEWConstants.TRUE_CD, KEWConstants.YES_LABEL);
        newDocTypeActiveIndicatorLabels.put(KEWConstants.FALSE_CD, KEWConstants.NO_LABEL);
        newDocTypeActiveIndicatorLabels.put(KEWConstants.INHERITED_CD, KEWConstants.INHERITED_LABEL);
        return newDocTypeActiveIndicatorLabels;
    }

    private static Map<String, String> getActivationPolicyLabels() {
    	Map<String, String> newActivationPolicyLabels = new HashMap<String, String>();
        newActivationPolicyLabels.put(KEWConstants.ROUTE_LEVEL_PARALLEL, KEWConstants.ROUTE_LEVEL_PARALLEL_LABEL);
        newActivationPolicyLabels.put(KEWConstants.ROUTE_LEVEL_SEQUENCE, KEWConstants.ROUTE_LEVEL_SEQUENCE_LABEL);
        return newActivationPolicyLabels;
    }

    private static Map<String, String> getApprovePolicyLabels() {
        Map<String, String> approvePolicyLabels = new HashMap<String, String>();
        approvePolicyLabels.put(ActionRequestPolicy.ALL.getCode(), ActionRequestPolicy.ALL.getLabel());
        approvePolicyLabels.put(ActionRequestPolicy.FIRST.getCode(), ActionRequestPolicy.FIRST.getLabel());
        return approvePolicyLabels;
    }

    /**
     * Given an actionRequest code return the appropriate label.
     *
     * @param actionRequestCode
     *            The actionRequestCode to be translated.
     * @return action request label
     */
    static public String getActionRequestLabel(String actionRequestCode) {
        return (String) arLabels.get(actionRequestCode);
    }

    /**
     * Given an action taken code return the appropriate label for it.
     *
     * @param actionTakenCode
     *            action taken code to use to find the label.
     * @return action taken label
     */
    static public String getActionTakenLabel(String actionTakenCode) {
        return (String) atLabels.get(actionTakenCode);
    }

    /**
     * Return the label for the given request status level.
     *
     * @param status
     *            code of the request status
     * @return label for the corresponding code.
     */
    static public String getActionRequestStatusLabel(String status) {
        return (String) arStatusLabels.get(status);
    }

    static public String getRouteStatusLabel(String status) {
        return (String) routeStatusLabels.get(status);
    }

    static public String getActiveIndicatorLabel(Boolean indicator) {
        return (String) activeIndicatorLabels.get(indicator);
    }

    static public String getActivationPolicyLabel(String code) {
        return (String) activationPolicyLabels.get(code);
    }

    static public String getPolicyLabel(String code) {
        return (String) policyLabels.get(code);
    }

}

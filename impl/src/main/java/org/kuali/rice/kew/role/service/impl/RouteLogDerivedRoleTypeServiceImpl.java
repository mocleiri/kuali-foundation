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
package org.kuali.rice.kew.role.service.impl;

import org.apache.commons.lang.StringUtils;
import org.kuali.rice.kew.api.KewApiServiceLocator;
import org.kuali.rice.kew.api.document.WorkflowDocumentService;
import org.kuali.rice.kew.exception.WorkflowException;
import org.kuali.rice.kew.service.WorkflowInfo;
import org.kuali.rice.kim.api.role.Role;
import org.kuali.rice.kim.api.role.RoleMembership;
import org.kuali.rice.kim.framework.type.KimDelegationTypeService;
import org.kuali.rice.kim.framework.type.KimRoleTypeService;
import org.kuali.rice.kim.service.support.impl.KimDerivedRoleTypeServiceBase;
import org.kuali.rice.kim.util.KimConstants;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author Kuali Rice Team (rice.collab@kuali.org)
 *
 */
public class RouteLogDerivedRoleTypeServiceImpl extends KimDerivedRoleTypeServiceBase implements KimRoleTypeService, KimDelegationTypeService {
    public static final String INITIATOR_ROLE_NAME = "Initiator";
    public static final String INITIATOR_OR_REVIEWER_ROLE_NAME = "Initiator or Reviewer";
    public static final String ROUTER_ROLE_NAME = "Router";

	protected WorkflowInfo workflowInfo = new WorkflowInfo();
    
	{
		//KFSMI-4938 - document number needs to be optional for this role type, 
		//since this is also used for inquiries
		//requiredAttributes.add(KimAttributes.DOCUMENT_NUMBER);
		checkRequiredAttributes = true;
	}
	
	/**
	 *	- qualifier is document number
	 *	- the roles that will be of this type are KR-WKFLW Initiator and KR-WKFLW Initiator or Reviewer, KR-WKFLW Router
	 *	- only the initiator of the document in question gets the KR-WKFLW Initiator role
	 *	- user who routed the document according to the route log should get the KR-WKFLW Router role
	 *	- users who are authorized by the route log, 
	 *		i.e. initiators, people who have taken action, people with a pending action request, 
	 *		or people who will receive an action request for the document in question get the KR-WKFLW Initiator or Reviewer Role 
	 *
	 */
	@Override
    public List<RoleMembership> getRoleMembersFromApplicationRole(String namespaceCode, String roleName, Map<String, String> qualification) {
		validateRequiredAttributesAgainstReceived(qualification);
		List<RoleMembership> members = new ArrayList<RoleMembership>();
		if(qualification!=null && !qualification.isEmpty()){
			String documentId = qualification.get(KimConstants.AttributeConstants.DOCUMENT_NUMBER);
			if (StringUtils.isNotBlank(documentId)) {
				try{
                    WorkflowDocumentService workflowDocumentService = KewApiServiceLocator.getWorkflowDocumentService();
					if (INITIATOR_ROLE_NAME.equals(roleName)) {
					    String principalId = KewApiServiceLocator.getWorkflowDocumentService().getDocumentInitiatorPrincipalId(
                                documentId);
                        RoleMembership roleMembership = RoleMembership.Builder.create(null,null,principalId, Role.PRINCIPAL_MEMBER_TYPE,null).build();
	                    members.add(roleMembership);
					} else if(INITIATOR_OR_REVIEWER_ROLE_NAME.equals(roleName)) {
					    List<String> ids = workflowInfo.getPrincipalIdsInRouteLog(documentId, true);
					    for ( String id : ids ) {
					    	if ( StringUtils.isNotBlank(id) ) {
                                RoleMembership roleMembership = RoleMembership.Builder.create(null,null,id, Role.PRINCIPAL_MEMBER_TYPE,null).build();
					    		members.add(roleMembership );
					    	}
					    }
					} else if(ROUTER_ROLE_NAME.equals(roleName)) {
                        String principalId = workflowDocumentService.getDocumentRoutedByPrincipalId(documentId);
                        RoleMembership roleMembership = RoleMembership.Builder.create(null,null,principalId, Role.PRINCIPAL_MEMBER_TYPE,null).build();
	                    members.add(roleMembership);
					}
				} catch(WorkflowException wex){
					throw new RuntimeException(
					"Error in getting principal Ids in route log for document id: "+ documentId +" :"+wex.getLocalizedMessage(),wex);
				}
			}
		}

		return members;
	}

	@Override
	public boolean hasApplicationRole(
			String principalId, List<String> groupIds, String namespaceCode, String roleName, Map<String, String> qualification){
		validateRequiredAttributesAgainstReceived(qualification);
        boolean isUserInRouteLog = false;
		if(qualification!=null && !qualification.isEmpty()){
			String documentId = qualification.get(KimConstants.AttributeConstants.DOCUMENT_NUMBER);
            WorkflowDocumentService workflowDocumentService = KewApiServiceLocator.getWorkflowDocumentService();
			try {
				if (INITIATOR_ROLE_NAME.equals(roleName)){
					isUserInRouteLog = principalId.equals(workflowDocumentService.getDocumentInitiatorPrincipalId(documentId));
				} else if(INITIATOR_OR_REVIEWER_ROLE_NAME.equals(roleName)){
					isUserInRouteLog = workflowInfo.isUserAuthenticatedByRouteLog(documentId, principalId, true);
				} else if(ROUTER_ROLE_NAME.equals(roleName)){
					isUserInRouteLog = principalId.equals(workflowDocumentService.getDocumentRoutedByPrincipalId(documentId));
				}
			} catch (WorkflowException wex) {
				throw new RuntimeException("Error in determining whether the principal Id: " + principalId + " is in route log " +
						"for document id: " + documentId + " :"+wex.getLocalizedMessage(),wex);
			}
		}
		return isUserInRouteLog;
	}

	/**
	 * Returns false, as the Route Log changes often enough that role membership is highly volatile
	 * 
	 * @see org.kuali.rice.kim.service.support.impl.KimRoleTypeServiceBase#shouldCacheRoleMembershipResults(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean shouldCacheRoleMembershipResults(String namespaceCode,
			String roleName) {
		return false;
	}
}

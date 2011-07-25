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
package org.kuali.rice.krad.authorization;

import org.apache.commons.lang.StringUtils;
import org.kuali.rice.kim.api.role.Role;
import org.kuali.rice.kim.api.role.RoleMembership;
import org.kuali.rice.kim.api.services.KimApiServiceLocator;
import org.kuali.rice.kim.bo.role.dto.PermissionAssigneeInfo;
import org.kuali.rice.kim.service.PermissionService;
import org.kuali.rice.kim.service.support.impl.KimDerivedRoleTypeServiceBase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This is a description of what this class does - wliang don't forget to fill this in.
 *
 * @author Kuali Rice Team (rice.collab@kuali.org)
 */
public class PermissionDerivedRoleTypeServiceImpl extends KimDerivedRoleTypeServiceBase {

	private static PermissionService permissionService;
	private String permissionTemplateNamespace;
	private String permissionTemplateName;
	/**
	 * @return the permissionTemplateNamespace
	 */
	public String getPermissionTemplateNamespace() {
		return this.permissionTemplateNamespace;
	}
	/**
	 * @param permissionTemplateNamespace the permissionTemplateNamespace to set
	 */
	public void setPermissionTemplateNamespace(String permissionTemplateNamespace) {
		this.permissionTemplateNamespace = permissionTemplateNamespace;
	}
	/**
	 * @return the permissionTemplateName
	 */
	public String getPermissionTemplateName() {
		return this.permissionTemplateName;
	}
	/**
	 * @param permissionTemplateName the permissionTemplateName to set
	 */
	public void setPermissionTemplateName(String permissionTemplateName) {
		this.permissionTemplateName = permissionTemplateName;
	}
	
	protected List<PermissionAssigneeInfo> getPermissionAssignees(Map<String, String> qualification) {
		return getPermissionService().getPermissionAssigneesForTemplateName(permissionTemplateNamespace, permissionTemplateName, new HashMap<String, String>(qualification),  new HashMap<String, String>(qualification));
	}

    @Override
    public List<RoleMembership> getRoleMembersFromApplicationRole(String namespaceCode, String roleName, Map<String, String> qualification) {
        List<PermissionAssigneeInfo> permissionAssignees = getPermissionAssignees(qualification);
        List<RoleMembership> members = new ArrayList<RoleMembership>();
        for (PermissionAssigneeInfo permissionAssigneeInfo : permissionAssignees) {
            if (StringUtils.isNotBlank(permissionAssigneeInfo.getPrincipalId())) {
                members.add(RoleMembership.Builder.create(null/*roleId*/, null, permissionAssigneeInfo.getPrincipalId(), Role.PRINCIPAL_MEMBER_TYPE, null).build());
            } else if (StringUtils.isNotBlank(permissionAssigneeInfo.getGroupId())) {
                members.add(RoleMembership.Builder.create(null/*roleId*/, null, permissionAssigneeInfo.getGroupId(), Role.GROUP_MEMBER_TYPE, null).build());
            }
        }
        return members;
    }


    @Override
    public boolean hasApplicationRole(
            String principalId, List<String> groupIds, String namespaceCode, String roleName, Map<String, String> qualification){
        // FIXME: dangerous - data changes could cause an infinite loop - should add thread-local to trap state and abort
        return getPermissionService().isAuthorizedByTemplateName(principalId, permissionTemplateNamespace, permissionTemplateName, new HashMap<String, String>(qualification), new HashMap<String, String>(qualification));
    }

    /**
     * @return the documentService
     */
    protected PermissionService getPermissionService() {
        if (permissionService == null) {
            permissionService = KimApiServiceLocator.getPermissionService();
        }
        return permissionService;
    }

}

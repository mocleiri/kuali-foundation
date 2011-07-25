/*
 * Copyright 2007-2009 The Kuali Foundation
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
package org.kuali.rice.kew.service.impl;

import org.apache.commons.lang.StringUtils;
import org.kuali.rice.kim.api.permission.Permission;
import org.kuali.rice.kim.impl.permission.PermissionBo;
import org.kuali.rice.kim.service.support.KimPermissionTypeService;
import org.kuali.rice.kim.util.KimConstants;
import org.kuali.rice.krad.service.impl.DocumentTypePermissionTypeServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * This is a description of what this class does - jonathan don't forget to fill
 * this in.
 * 
 * @author Kuali Rice Team (rice.collab@kuali.org)
 * 
 */
public class AdhocReviewPermissionTypeServiceImpl extends DocumentTypePermissionTypeServiceImpl implements KimPermissionTypeService{
	
	{
		requiredAttributes.add( KimConstants.AttributeConstants.ACTION_REQUEST_CD );
	}

	@Override
	public List<Permission> performPermissionMatches(
			Map<String, String> requestedDetails,
			List<Permission> permissionsList) {
        List<Permission> matchingPermissions = new ArrayList<Permission>();
		if (requestedDetails == null) {
			return matchingPermissions; // empty list
		}
		// loop over the permissions, checking the non-document-related ones
		for (Permission kpi : permissionsList) {
            PermissionBo bo = PermissionBo.from(kpi);
			if (!bo.getDetails().containsKey(KimConstants.AttributeConstants.ACTION_REQUEST_CD)
			  || StringUtils.equals(bo.getDetails().
				 get(KimConstants.AttributeConstants.ACTION_REQUEST_CD), requestedDetails
					.get(KimConstants.AttributeConstants.ACTION_REQUEST_CD))) {
				matchingPermissions.add(kpi);
			}
		}
		// now, filter the list to just those for the current document
		matchingPermissions = super.performPermissionMatches(requestedDetails,matchingPermissions);
		return matchingPermissions;
	}
}

/*
 * Copyright 2008 The Kuali Foundation
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
package org.kuali.rice.ken.service.impl;

import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.kuali.rice.ken.service.NotificationRecipientService;
import org.kuali.rice.kim.api.services.IdentityManagementService;
import org.kuali.rice.kim.api.group.Group;
import org.kuali.rice.kim.api.services.KimApiServiceLocator;
import org.kuali.rice.kim.util.KimConstants.KimGroupMemberTypes;

/**
 * NotificationRecipientService implementation
 * This implementation relies on KIM user and group management
 * @author Kuali Rice Team (rice.collab@kuali.org)
 */
public class NotificationRecipientServiceKimImpl implements NotificationRecipientService
{
    private static final Logger LOG =
        Logger.getLogger(NotificationRecipientServiceKimImpl.class);

    protected IdentityManagementService getIdentityManagementService()
    {
        return KimApiServiceLocator.getIdentityManagementService();
    }

    /**
     * Uses the IdentityManagementService of KIM to get the members of a group.
     *
     * @param groupRecipientId the String name of the recipient group
     * @return a String array of all direct (child) principals and descendent principals
     * @see org.kuali.rice.ken.service.NotificationRecipientService#getGroupMembers(java.lang.String)
     */
    public String[] getGroupMembers(String groupRecipientId)
    {
        Group group = getIdentityManagementService().getGroup(groupRecipientId);

        List<String> ids = getIdentityManagementService().getGroupMemberPrincipalIds(group.getId());

        String[] array = new String[ids.size()];
        return ids.toArray(array);
    }

    /**
     * This method retrieves the display name for a user.
     * @param userId
     * @return String
     */
    public String getUserDisplayName(String userId)
    {
        //Gary's handling user conversion
        return null;
    }

    /**
     *
     * @see org.kuali.rice.ken.service.NotificationRecipientService#isRecipientValid(java.lang.String, java.lang.String)
     */
     public boolean isRecipientValid(String recipientId, String recipientType)
     {
         boolean b = false;

         if( recipientType.equals(KimGroupMemberTypes.GROUP_MEMBER_TYPE))
         {
             b = isGroupRecipientValid( recipientId );
         }
         else if( recipientType.equals(KimGroupMemberTypes.PRINCIPAL_MEMBER_TYPE) )
         {
             b = isUserRecipientValid( recipientId );
         }
         else
         {
             if( LOG.isEnabledFor(Level.ERROR) )
             {
                 LOG.error("Recipient Type is neither of two acceptable values");
             }
         }
         return b;
     }

    /**
     * This overridden method ...
     *
     * @see org.kuali.rice.ken.service.NotificationRecipientService#isGroupRecipientValid(java.lang.String)
     */
    public boolean isGroupRecipientValid(String groupRecipientId)
    {
        return (KimApiServiceLocator.getIdentityManagementService().getGroup( groupRecipientId ) != null);
    }

    /**
     * This overridden method ...
     *
     * @see org.kuali.rice.ken.service.NotificationRecipientService#isUserRecipientValid(java.lang.String)
     */
    public boolean isUserRecipientValid(String principalName)
    {
        return (KimApiServiceLocator.getIdentityManagementService()
                .getPrincipalByPrincipalName(principalName) != null);
    }

}

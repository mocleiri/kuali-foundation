/*
 * Copyright 2006-2011 The Kuali Foundation
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

package org.kuali.rice.kim.api.permission;


import org.kuali.rice.core.api.mo.GloballyUnique;
import org.kuali.rice.core.api.mo.Versioned;
import org.kuali.rice.kim.api.attribute.KimAttributeDataContract;

public interface PermissionAttributeContract extends KimAttributeDataContract, Versioned, GloballyUnique {
	
    /**
     * The Permission ID referenced by the Permission Attribute.
     * 
     * @return permissionId
     */
    String getPermissionId();
    
}

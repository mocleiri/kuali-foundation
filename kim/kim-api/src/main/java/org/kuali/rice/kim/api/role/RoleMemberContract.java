/*
 * Copyright 2006-2011 The Kuali Foundation
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

package org.kuali.rice.kim.api.role;

import org.kuali.rice.core.api.mo.common.active.InactivatableFromTo;

import java.util.List;
import java.util.Map;

public interface RoleMemberContract extends InactivatableFromTo {
    String getMemberId();

    String getMemberTypeCode();

    String getRoleMemberId();

    String getRoleId();

    Map<String, String> getAttributes();

    List<? extends RoleResponsibilityActionContract> getRoleRspActions();
}

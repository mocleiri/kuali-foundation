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

import org.kuali.rice.core.api.mo.Versioned;

/**
 * Contract for a Role. Role is a basic abstraction over a role assignable to a principal within KIM.
 */
public interface RoleContract extends Versioned {

    /**
     * A unique identifier for this role.
     *
     * @return String identifier for the role.
     */
    String getId();

    /**
     * A namespace for this role.  A namespace for a role identifies the system/module to which this role applies.
     *
     * @return Namespace for the role.
     */
    String getNamespaceCode();

    /**
     * The human readable name for this role.
     *
     * @return Human readable role name.
     */
    String getName();

    /**
     * A full textual description of this role.  This String should provide a verbose description of the role, what
     * it is meant to provide to principals assigned to it, and what permissions it implies.
     *
     * @return Description of the role.
     */
    String getDescription();

    /**
     * Provides the associated KimType identifier for this role.  This controls what additional attributes
     * are available.
     *
     * @return KimType Id
     */
    String getKimTypeId();


    /**
     * Value representing whether or not a role is active or not.
     *
     * @return if a country is active
     * @see org.kuali.rice.kns.bo.Inactivateable
     */
    boolean isActive();

    /**
     * This the object version number for Role.
     *
     * @return the version number of the Role
     */
    Long getVersionNumber();
}

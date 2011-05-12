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
package org.kuali.rice.kim.bo.entity;

/**
 * an external identifier for a KIM entity 
 * 
 * @author Kuali Rice Team (rice.collab@kuali.org)
 *
 */
public interface KimEntityExternalIdentifier {

    /**
     * Gets this {@link KimEntityExternalIdentifier}'s id.
     * @return the id for this {@link KimEntityExternalIdentifier}, or null if none has been assigned.
     */
	String getEntityExternalIdentifierId();
	
	/**
     * Gets this {@link KimEntityExternalIdentifier}'s type code.
     * @return the type code for this {@link KimEntityExternalIdentifier}, or null if none has been assigned.
     */
	String getExternalIdentifierTypeCode();
	
	/**
     * Gets this {@link KimEntityExternalIdentifier}'s external id.
     * @return the external id for this {@link KimEntityExternalIdentifier}, or null if none has been assigned.
     */
	String getExternalId();
}

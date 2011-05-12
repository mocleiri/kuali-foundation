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
package org.kuali.rice.kim.client.acegi;

import org.acegisecurity.AuthenticationException;

/**
 * An indicator used to determine that the session expired
 * 
 * @author Kuali Rice Team (rice.collab@kuali.org)
 *
 */

public class KualiDistributedSessionExpiredException extends AuthenticationException {
   private static final long serialVersionUID = -7235709782721246169L;

//~ Constructors ===================================================================================================

   public KualiDistributedSessionExpiredException(String msg) {
       super(msg);
   }
}

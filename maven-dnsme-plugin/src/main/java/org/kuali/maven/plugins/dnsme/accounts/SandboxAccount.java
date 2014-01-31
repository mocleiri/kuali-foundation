/**
 * Copyright 2004-2014 The Kuali Foundation
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
package org.kuali.maven.plugins.dnsme.accounts;

import org.kuali.maven.plugins.dnsme.beans.Account;

public class SandboxAccount extends Account {
    public SandboxAccount() {
        super();
        setUsername("kuali");
        setApiKey("549de3da-8eae-4350-b20e-3d5c31f7117e");
        setSecretKey("58dca3a2-650e-4919-bcfb-d6e6e61f4fc2");
    }
}

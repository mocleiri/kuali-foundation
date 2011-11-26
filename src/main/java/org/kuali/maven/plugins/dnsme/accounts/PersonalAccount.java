/**
 * Copyright 2004-2011 The Kuali Foundation
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

public class PersonalAccount extends Account {
    public PersonalAccount() {
        super();
        setApiKey("bad6fd4f-1ca1-4e5d-880e-585afe7d070e");
        setSecretKey("856ce7ab-8a2b-4a81-b1ea-b1e9f2b04618");
    }
}

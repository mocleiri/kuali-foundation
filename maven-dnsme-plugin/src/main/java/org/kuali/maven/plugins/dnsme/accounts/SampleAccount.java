/**
 * Copyright 2004-2013 The Kuali Foundation
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

/**
 * Config described in the DNSME docs at http://cp.dnsmadeeasy.com/enterprisedns/api.html
 */
public class SampleAccount extends Account {
    public SampleAccount() {
        super();
        setApiKey("1c1a3c91-4770-4ce7-96f4-54c0eb0e457a");
        setSecretKey("c9b5625f-9834-4ff8-baba-4ed5f32cae55");
    }
}

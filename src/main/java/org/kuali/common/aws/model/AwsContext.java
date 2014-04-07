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
package org.kuali.common.aws.model;

import org.kuali.common.util.Assert;

public final class AwsContext {

	public AwsContext(AwsAccount account, AwsAuth auth) {
		Assert.noNulls(account, auth);
		this.account = account;
		this.auth = auth;
	}

	private final AwsAccount account;
	private final AwsAuth auth;

	public AwsAccount getAccount() {
		return account;
	}

	public AwsAuth getAuth() {
		return auth;
	}

}

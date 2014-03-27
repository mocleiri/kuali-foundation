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
package org.kuali.common.aws;

import static org.kuali.common.util.base.Precondition.checkNotBlank;

import com.amazonaws.auth.AWSCredentials;

public enum Credentials implements AWSCredentials {

	FOUNDATION("AKIAJFD5IM7IPVVUEBNA", "enc--uXNCzc6efcKz1zvp4t5Fj4wyR9oGw2GZ2VOB3SXZaoXaV1BA1Gao2d2vWXnjqUA1oKzg+0s9NAM="), //
	STUDENT("AKIAJZ72UQ5ZCVEDMAPQ", "enc--fXH7NKtbCDnn17aoTOPL707itdwlz6VKyHJaKCHAuBk//XGTyGef+2CeM89GqpIPZYk4ewKia2Y="), //
	RICE("AKIAIZFPMJVCNOYYAZ2Q", "enc--iDHNEii2oWjbwqJMaaT4f5SKhiNHyhWhcd9NIHTPIByNJlUg72B4czVFeIf/rNYJvp/gFXQkTj0="), //
	OLE("AKIAI453FI76LUZ7T7CA", "enc--2UP9ztdMy32DdN62ZMjD+K0jMYCrKkYzI2xies0asEU4cc7sWOF47a2CDu314ojHoSrz/CtFaAs=");

	private final String accessKey;
	private final String secretKey;

	private Credentials(String accessKey, String secretKey) {
		this.accessKey = checkNotBlank(accessKey, "accessKey");
		this.secretKey = checkNotBlank(secretKey, "secretKey");
	}

	@Override
	public String getAWSAccessKeyId() {
		return accessKey;
	}

	@Override
	public String getAWSSecretKey() {
		return secretKey;
	}

}

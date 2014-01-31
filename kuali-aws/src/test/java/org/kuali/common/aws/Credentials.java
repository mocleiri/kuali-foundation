package org.kuali.common.aws;

import static com.google.common.base.Preconditions.checkArgument;

import org.apache.commons.lang3.StringUtils;

import com.amazonaws.auth.AWSCredentials;

public enum Credentials implements AWSCredentials {

	FOUNDATION("AKIAJFD5IM7IPVVUEBNA", "enc--uXNCzc6efcKz1zvp4t5Fj4wyR9oGw2GZ2VOB3SXZaoXaV1BA1Gao2d2vWXnjqUA1oKzg+0s9NAM="), //
	STUDENT("AKIAJZ72UQ5ZCVEDMAPQ", "enc--fXH7NKtbCDnn17aoTOPL707itdwlz6VKyHJaKCHAuBk//XGTyGef+2CeM89GqpIPZYk4ewKia2Y="), //
	RICE("AKIAIZFPMJVCNOYYAZ2Q", "enc--iDHNEii2oWjbwqJMaaT4f5SKhiNHyhWhcd9NIHTPIByNJlUg72B4czVFeIf/rNYJvp/gFXQkTj0="), //
	OLE("AKIAI453FI76LUZ7T7CA", "enc--2UP9ztdMy32DdN62ZMjD+K0jMYCrKkYzI2xies0asEU4cc7sWOF47a2CDu314ojHoSrz/CtFaAs=");

	private final String accessKey;
	private final String secretKey;

	private Credentials(String accessKey, String secretKey) {
		checkArgument(!StringUtils.isBlank(accessKey), "'accessKey' cannot be blank");
		checkArgument(!StringUtils.isBlank(secretKey), "'secretKey' cannot be blank");
		this.accessKey = accessKey;
		this.secretKey = secretKey;
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

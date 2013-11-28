package org.kuali.common.devops.aws;

import org.kuali.common.util.Assert;

public enum Credentials {

	FOUNDATION("AKIAJFD5IM7IPVVUEBNA", "ENC(uXNCzc6efcKz1zvp4t5Fj4wyR9oGw2GZ2VOB3SXZaoXaV1BA1Gao2d2vWXnjqUA1oKzg+0s9NAM=)"), //
	STUDENT("AKIAJZ72UQ5ZCVEDMAPQ", "ENC(fXH7NKtbCDnn17aoTOPL707itdwlz6VKyHJaKCHAuBk//XGTyGef+2CeM89GqpIPZYk4ewKia2Y=)"), //
	RICE("AKIAIZFPMJVCNOYYAZ2Q", "ENC(iDHNEii2oWjbwqJMaaT4f5SKhiNHyhWhcd9NIHTPIByNJlUg72B4czVFeIf/rNYJvp/gFXQkTj0=)"), //
	OLE("AKIAI453FI76LUZ7T7CA", "ENC(2UP9ztdMy32DdN62ZMjD+K0jMYCrKkYzI2xies0asEU4cc7sWOF47a2CDu314ojHoSrz/CtFaAs=)");

	private final String accessKey;
	private final String encryptedSecretKey;

	private Credentials(String accessKey, String encryptedSecretKey) {
		Assert.noBlanks(accessKey, encryptedSecretKey);
		this.accessKey = accessKey;
		this.encryptedSecretKey = encryptedSecretKey;
	}

	public String getAccessKey() {
		return accessKey;
	}

	public String getEncryptedSecretKey() {
		return encryptedSecretKey;
	}

}

package org.kuali.common.devops.aws;

import java.util.Map;

import org.kuali.common.util.Assert;

import com.amazonaws.auth.AWSCredentials;
import com.google.common.collect.Maps;

public enum Credentials implements AWSCredentials {

	FOUNDATION("AKIAJFD5IM7IPVVUEBNA", "ENC(uXNCzc6efcKz1zvp4t5Fj4wyR9oGw2GZ2VOB3SXZaoXaV1BA1Gao2d2vWXnjqUA1oKzg+0s9NAM=)"), //
	OLE("AKIAI453FI76LUZ7T7CA", "ENC(2UP9ztdMy32DdN62ZMjD+K0jMYCrKkYzI2xies0asEU4cc7sWOF47a2CDu314ojHoSrz/CtFaAs=)"), //
	RICE("AKIAIZFPMJVCNOYYAZ2Q", "ENC(iDHNEii2oWjbwqJMaaT4f5SKhiNHyhWhcd9NIHTPIByNJlUg72B4czVFeIf/rNYJvp/gFXQkTj0=)"), //
	STUDENT("AKIAJZ72UQ5ZCVEDMAPQ", "ENC(fXH7NKtbCDnn17aoTOPL707itdwlz6VKyHJaKCHAuBk//XGTyGef+2CeM89GqpIPZYk4ewKia2Y=)"); //

	private final String accessKey;
	private final String secretKey;

	private Credentials(String accessKey, String secretKey) {
		Assert.noBlanks(accessKey, secretKey);
		Assert.encrypted(secretKey);
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

	public static final Map<String, AWSCredentials> asMap() {
		Map<String, AWSCredentials> map = Maps.newTreeMap();
		for (Credentials c : values()) {
			map.put(c.name().toLowerCase(), c);
		}
		return map;
	}

}

package org.kuali.common.devops.aws;

import com.amazonaws.auth.AWSCredentials;

public enum Credentials implements AWSCredentials {

	FOUNDATION("AKIAJFD5IM7IPVVUEBNA", "ENC(uXNCzc6efcKz1zvp4t5Fj4wyR9oGw2GZ2VOB3SXZaoXaV1BA1Gao2d2vWXnjqUA1oKzg+0s9NAM=)"), //
	KS("AKIAJZ72UQ5ZCVEDMAPQ", "ENC(fXH7NKtbCDnn17aoTOPL707itdwlz6VKyHJaKCHAuBk//XGTyGef+2CeM89GqpIPZYk4ewKia2Y=)"), //
	RICE("AKIAJFD5IM7IPVVUEBNA", "ENC(uXNCzc6efcKz1zvp4t5Fj4wyR9oGw2GZ2VOB3SXZaoXaV1BA1Gao2d2vWXnjqUA1oKzg+0s9NAM=)"), //
	OLE("AKIAJFD5IM7IPVVUEBNA", "ENC(uXNCzc6efcKz1zvp4t5Fj4wyR9oGw2GZ2VOB3SXZaoXaV1BA1Gao2d2vWXnjqUA1oKzg+0s9NAM=)");

	private final String accessKey;
	private final String secretKey;

	private Credentials(String accessKey, String secretKey) {
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

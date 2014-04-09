package org.kuali.common.util.enc;

public interface Encryptor {

	String encrypt(String text);

	String decrypt(String text);

}

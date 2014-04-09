package org.kuali.common.util.encrypt;

public interface Encryptor {

	String encrypt(String text);

	String decrypt(String text);

}

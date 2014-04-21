package org.kuali.common.util.encrypt;

public interface Encryptor {

	String encrypt(String plaintext);

	String decrypt(String encrypted);

}

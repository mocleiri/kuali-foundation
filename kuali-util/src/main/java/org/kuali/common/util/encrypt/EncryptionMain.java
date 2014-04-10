package org.kuali.common.util.encrypt;

public final class EncryptionMain {

	public static void main(String[] args) {
		if (args == null || args.length == 0) {
			usage();
			System.exit(1);
		}
		Encryptor encryptor = Encryption.buildDefaultEncryptor();
		String encrypted = encryptor.encrypt(args[0]);
		System.out.println(encrypted);
	}

	private static void usage() {
		System.out.println("EncryptionMain: text");
	}
}

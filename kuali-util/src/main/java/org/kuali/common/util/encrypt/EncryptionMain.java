package org.kuali.common.util.encrypt;

import static com.google.common.base.Optional.absent;
import static org.apache.commons.io.FileUtils.readFileToString;
import static org.kuali.common.util.base.Exceptions.illegalState;

import java.io.File;
import java.io.IOException;

import org.kuali.common.util.file.CanonicalFile;

import com.google.common.base.Optional;

public final class EncryptionMain {

	public static void main(String[] args) {
		if (args == null || args.length == 0) {
			usage();
			System.exit(1);
		}
		boolean encrypt = matches(args, "-e", "--encrypt");
		boolean decrypt = matches(args, "-d", "--decrypt");
		String text = getText(args);
		if (encrypt) {
			System.out.println(Encryption.buildDefaultEncryptor().encrypt(text));
		}
		if (decrypt) {
			System.out.println(Encryption.buildDefaultEncryptor().decrypt(text));
		}
	}

	private static String getText(String[] args) {
		Optional<File> file = getFile(args);
		if (file.isPresent()) {
			try {
				return readFileToString(file.get());
			} catch (IOException e) {
				throw illegalState(e);
			}
		} else {
			return args[args.length - 1];
		}
	}

	private static Optional<File> getFile(String[] args) {
		for (String arg : args) {
			if (arg.equals("-f") || arg.equals("--file")) {
				return Optional.<File> of(new CanonicalFile(arg));
			}
		}
		return absent();
	}

	private static boolean matches(String[] args, String option, String longoption) {
		for (String arg : args) {
			if (arg.equals(option) || arg.equals(longoption)) {
				return true;
			}
		}
		return false;
	}

	private static void usage() {
		System.out.println("Usage: EncryptionMain --encrypt --decrypt [--file=filename] text");
	}
}

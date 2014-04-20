package org.kuali.common.util.encrypt;

import static com.google.common.base.Optional.absent;
import static com.google.common.base.Preconditions.checkState;
import static org.apache.commons.io.FileUtils.readFileToString;
import static org.kuali.common.util.base.Exceptions.illegalState;
import static org.kuali.common.util.encrypt.Encryption.getDefaultEncryptor;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.kuali.common.util.file.CanonicalFile;

import com.google.common.base.Optional;
import com.google.common.base.Splitter;

public final class EncryptionMain {

	private static final String FS = File.separator;
	private static final String USER_HOME_TOKEN = "~" + FS;
	private static final String USER_HOME_REPLACEMENT = System.getProperty("user.home") + FS;

	public static void main(String[] args) {
		if (args == null || args.length == 0) {
			usage();
			System.exit(1);
		}
		boolean encrypt = matches(args, "-e", "--encrypt");
		boolean decrypt = matches(args, "-d", "--decrypt");
		if (!encrypt && !decrypt) {
			usage();
			System.exit(1);
		}
		String text = getText(args);
		if (encrypt) {
			System.out.println(getDefaultEncryptor().encrypt(text));
		}
		if (decrypt) {
			System.out.println(getDefaultEncryptor().decrypt(text));
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
			if (arg.startsWith("--file")) {
				List<String> tokens = Splitter.on('=').splitToList(arg);
				checkState(tokens.size() == 2, "expected 2 tokens from [%s], but got %s instead", arg, tokens.size());
				String filename = tokens.get(1).replace(USER_HOME_TOKEN, USER_HOME_REPLACEMENT);
				return Optional.<File> of(new CanonicalFile(filename));
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

package org.kuali.common.util.encrypt.openssl;

import static com.google.common.base.Preconditions.checkArgument;
import static org.codehaus.plexus.util.Base64.decodeBase64;
import static org.kuali.common.util.Ascii.isDigit;
import static org.kuali.common.util.Ascii.isLetter;
import static org.kuali.common.util.Encodings.ASCII;
import static org.kuali.common.util.base.Precondition.checkNotBlank;
import static org.kuali.common.util.base.Precondition.checkNotNull;
import static org.kuali.common.util.encrypt.openssl.OpenSSLContext.buildDefaultOpenSSLContext;

import org.kuali.common.util.Str;
import org.kuali.common.util.encrypt.Encryptor;

public final class OpenSSLEncryptor implements Encryptor {

	// Not exposed via a getter
	private final char[] password;
	private final OpenSSLContext context;

	public OpenSSLEncryptor(String password) {
		this(buildDefaultOpenSSLContext(), password);
	}

	public OpenSSLEncryptor(OpenSSLContext context, String password) {
		this.password = checkNotBlank(password, "password").toCharArray();
		this.context = checkNotNull(context, "context");
	}

	@Override
	public String encrypt(String text) {
		checkNotBlank(text, "text");
		return null;
	}

	@Override
	public String decrypt(String text) {
		checkNotBlank(text, "text");
		return null;
	}

	protected OpenSSLEncryptedContext getEncryptedContext(String text) {
		byte[] bytes = decodeBase64(Str.getBytes(checkBase64(text), ASCII));
		return null;
	}

	protected static String checkBase64(String text) {
		checkNotBlank(text, "text");
		for (char c : text.toCharArray()) {
			checkArgument(isBase64(c), "'%s' is not a base 64 character", c);
		}
		return text;
	}

	protected static boolean isBase64(char c) {
		if (isLetter(c) || isDigit(c)) {
			return true;
		} else {
			return c == '/' || c == '+' || c == '=';
		}
	}

	public OpenSSLContext getContext() {
		return context;
	}

}
package org.kuali.common.util.secure;

import java.io.IOException;
import java.io.InputStream;

public class JschUtils {

	// b is 0 for success,
	// 1 for error,
	// 2 for fatal error,
	// -1
	public static final ScpAck getScpAck(InputStream in) throws IOException {
		ScpAck ack = new ScpAck();
		int code = in.read();
		ack.setCode(code);
		switch (code) {
		case 0: // success
		case -1: // ????
			return ack;
		case 1: // error
		case 2: // fatal error
			String errorMessage = getScpErrorMessage(in);
			ack.setErrorMessage(errorMessage);
			return ack;
		default:
			throw new IllegalStateException("SCP acknowledgement code [" + code + "] + is unknown");
		}
	}

	public static final void validate(ScpAck ack) {
		if (ack.getCode() != 0) {
			throw new IllegalStateException("Non-zero SCP acknowledgement code " + ack.getCode() + " [" + ack.getErrorMessage() + "]");
		}
	}

	public static final String getScpErrorMessage(InputStream in) throws IOException {
		StringBuffer sb = new StringBuffer();
		int c;
		do {
			c = in.read();
			sb.append((char) c);
		} while (c != '\n');
		return sb.toString();
	}

}

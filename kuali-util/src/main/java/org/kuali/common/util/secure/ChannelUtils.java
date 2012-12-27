package org.kuali.common.util.secure;

public class ChannelUtils {

	public static final void closeQuietly(SecureChannel channel) {
		if (channel != null) {
			channel.close();
		}
	}

}

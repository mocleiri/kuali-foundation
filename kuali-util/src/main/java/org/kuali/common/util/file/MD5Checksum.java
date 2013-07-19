package org.kuali.common.util.file;

import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.io.IOUtils;
import org.kuali.common.util.LocationUtils;

public class MD5Checksum {

	public static byte[] createMD5Checksum(String location) {

		InputStream in = null;
		try {

			// Open an input stream
			in = LocationUtils.getInputStream(location);

			byte[] buffer = new byte[1024];
			MessageDigest complete = MessageDigest.getInstance("MD5");
			int numRead;
			do {
				numRead = in.read(buffer);
				if (numRead > 0) {
					complete.update(buffer, 0, numRead);
				}
			} while (numRead != -1);
			IOUtils.closeQuietly(in);
			return complete.digest();
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalStateException("Unexpected message digest error", e);
		} catch (IOException e) {
			throw new IllegalStateException("Unexpected IO error", e);
		} finally {
			IOUtils.closeQuietly(in);
		}
	}

	public static String getMD5Checksum(String location) {
		byte[] b = createMD5Checksum(location);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < b.length; i++) {
			sb.append(Integer.toString((b[i] & 0xff) + 0x100, 16).substring(1));
		}
		return sb.toString();
	}

}
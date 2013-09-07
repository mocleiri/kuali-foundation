package org.kuali.common.util.location.service;

import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.io.IOUtils;
import org.kuali.common.util.LocationUtils;

public class DefaultLocationService implements LocationService {

	@Override
	public String getSHA1Checksum(String location) {
		return null;
	}

	@Override
	public String getMD5Checksum(String location) {
		return null;
	}

	@Override
	public String getChecksum(String location, String algorithm) {
		byte[] bytes = getChecksumBytes(location, algorithm);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < bytes.length; i++) {
			sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
		}
		return sb.toString();
	}

	public byte[] getChecksumBytes(String location, String algorithm) {
		InputStream in = null;
		try {

			// Open an input stream
			in = LocationUtils.getInputStream(location);

			byte[] buffer = new byte[1024];
			MessageDigest complete = MessageDigest.getInstance(algorithm);
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

}

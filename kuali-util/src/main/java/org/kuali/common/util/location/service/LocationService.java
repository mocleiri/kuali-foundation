package org.kuali.common.util.location.service;


public interface LocationService {

	String getChecksum(String location, String algorithm);

	String getSHA1Checksum(String location);

	String getMD5Checksum(String location);

}

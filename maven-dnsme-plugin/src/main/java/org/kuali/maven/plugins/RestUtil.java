package org.kuali.maven.plugins;

import java.security.GeneralSecurityException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class RestUtil {
    // Sat, 12 Feb 2011 20:59:04 GMT
    public static final String DEFAULT_DATE_FORMAT = "EEE, dd MMM yyyy HH:mm:ss z";
    public static final String DEFAULT_TIME_ZONE = "GMT";
    public static final String DEFAULT_ALGORITHM = "HmacSHA1";

    String format;
    String algorithm;
    SimpleDateFormat sdf;
    TimeZone timezone;

    public RestUtil() {
        super();
        setFormat(DEFAULT_DATE_FORMAT);
        setTimezone(TimeZone.getTimeZone(DEFAULT_TIME_ZONE));
        setAlgorithm(DEFAULT_ALGORITHM);
    }

    public synchronized void setFormat(String format) {
        this.format = format;
        sdf = new SimpleDateFormat(format);
    }

    public synchronized void setTimezone(TimeZone timezone) {
        this.timezone = timezone;
        sdf.setTimeZone(timezone);
    }

    public synchronized String getHTTPDate(Date date) {
        return sdf.format(date);
    }

    public synchronized String getHash(String key, String data) throws GeneralSecurityException {
        Mac mac = Mac.getInstance(algorithm);
        SecretKey secretKey = new SecretKeySpec(key.getBytes(), algorithm);
        mac.init(secretKey);
        byte[] finalBytes = mac.doFinal(data.getBytes());
        return getHexString(finalBytes);
    }

    public String getHexString(byte[] b) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < b.length; i++) {
            int intValue1 = b[i] & 0xff;
            int intValue2 = intValue1 + 0x100;
            String s = Integer.toString(intValue2, 16);
            String one = s.substring(1);
            sb.append(one);
        }
        return sb.toString();
    }

    public String getFormat() {
        return format;
    }

    public TimeZone getTimezone() {
        return timezone;
    }

    public String getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }
}

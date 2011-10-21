package org.kuali.maven.plugins;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class Api {
    // Sat, 12 Feb 2011 20:59:04 GMT
    String format = "EEE, dd MMM yyyy HH:mm:ss z";
    SimpleDateFormat sdf = new SimpleDateFormat(format);
    TimeZone gmt = TimeZone.getTimeZone("GMT");
    String algorithm = "HmacSHA1";
    Mac mac;
    Config config;

    public Api(Config config) {
        super();
        init(config);
    }

    protected void init(Config config) {
        try {
            this.config = config;
            sdf.setTimeZone(gmt);
            mac = Mac.getInstance(algorithm);
            SecretKey sk = new SecretKeySpec(config.getSecretKey().getBytes(), algorithm);
            mac.init(sk);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String getHTTPDate(Date date) {
        return sdf.format(date);
    }

    public String getHMACSHA1Hash(String s) {
        byte[] finalBytes = mac.doFinal(s.getBytes());
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
}

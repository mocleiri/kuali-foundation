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

    // String apiKey = "454f2836-81c0-4379-b8dd-2cc6495131b5";
    // String secretKey = "5fc3245f-78b5-4c92-9053-be0b5c64a680";
    String apiKey = "549de3da-8eae-4350-b20e-3d5c31f7117e";
    String secretKey = "58dca3a2-650e-4919-bcfb-d6e6e61f4fc2";
    String algorithm = "HmacSHA1";
    Mac mac;

    public Api() {
        super();
        init();
    }

    protected void init() {
        try {
            sdf.setTimeZone(gmt);
            mac = Mac.getInstance(algorithm);
            SecretKey sk = new SecretKeySpec(secretKey.getBytes(), algorithm);
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
            int intValue = (b[i] & 0xff) + 0x100;
            String s = Integer.toString(intValue, 16);
            String one = s.substring(1);
            sb.append(one);
        }
        return sb.toString();
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }
}

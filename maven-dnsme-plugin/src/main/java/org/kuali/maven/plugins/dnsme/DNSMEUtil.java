package org.kuali.maven.plugins.dnsme;

import java.security.GeneralSecurityException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.kuali.maven.plugins.dnsme.beans.Account;

public class DNSMEUtil {
    // Sat, 12 Feb 2011 20:59:04 GMT
    public static final String DEFAULT_DATE_FORMAT = "EEE, dd MMM yyyy HH:mm:ss z";
    public static final String DEFAULT_TIME_ZONE = "GMT";
    public static final String DEFAULT_ALGORITHM = "HmacSHA1";
    public static final String API_KEY_HEADER = "x-dnsme-apiKey";
    public static final String DATE_HEADER = "x-dnsme-requestDate";
    public static final String HMAC_HEADER = "x-dnsme-hmac";

    String format;
    String algorithm;
    SimpleDateFormat sdf;
    TimeZone timeZone;

    public DNSMEUtil() {
        super();
        setFormat(DEFAULT_DATE_FORMAT);
        setTimeZone(TimeZone.getTimeZone(DEFAULT_TIME_ZONE));
        setAlgorithm(DEFAULT_ALGORITHM);
    }

    public synchronized void setFormat(String format) {
        this.format = format;
        sdf = new SimpleDateFormat(format);
    }

    public synchronized void setTimeZone(TimeZone timezone) {
        this.timeZone = timezone;
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

    public List<Header> getHeaders(Account account) throws GeneralSecurityException {
        String requestDate = getHTTPDate(new Date());
        String hash = getHash(account.getSecretKey(), requestDate);
        List<Header> headers = new ArrayList<Header>();
        headers.add(new Header(API_KEY_HEADER, account.getApiKey()));
        headers.add(new Header(DATE_HEADER, requestDate));
        headers.add(new Header(HMAC_HEADER, hash));
        return headers;
    }

    public HttpMethod getMethod(Account account, String url) throws GeneralSecurityException {
        HttpMethod method = new GetMethod(url);
        List<Header> headers = getHeaders(account);
        for (Header header : headers) {
            method.addRequestHeader(header);
        }
        return method;
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

    public TimeZone getTimeZone() {
        return timeZone;
    }

    public String getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }
}

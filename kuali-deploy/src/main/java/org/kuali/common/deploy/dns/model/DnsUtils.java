package org.kuali.common.deploy.dns.model;

import org.kuali.common.util.Assert;

public class DnsUtils {

	public static String getHostname(String prefix, String subdomain, String domain) {
		Assert.noBlanks(prefix, subdomain, domain);
		return prefix + "." + subdomain + "." + domain;
	}

}

package org.kuali.common.deploy.dns.model;

import org.kuali.common.util.Assert;

public class DnsUtils {

	/**
	 * <pre>
	 *  env1, ks, kuali.org -> env1.ks.kuali.org
	 * </pre>
	 */
	public static String getHostname(String prefix, String subdomain, String domain) {
		Assert.noBlanks(prefix, subdomain, domain);
		return prefix + "." + subdomain + "." + domain;
	}

}

package org.kuali.common.dns.api;

import java.util.List;

import org.kuali.common.dns.model.DnsRecord;
import org.kuali.common.dns.model.DnsRecordSearchCriteria;

/**
 * <p>
 * This service provides DNS operations on a given domain name, eg <code>kuali.org</code>.
 * </p>
 * <p>
 * Note that this is not intended to be a full fledged DNS service. The API provided here is for convenience as a simple way to automate tasks related to spinning up and shutting
 * down Kuali environments.
 * </p>
 */
public interface DnsService {

	/**
	 * The name of the domain this service performs operations on eg <code>kuali.org</code>
	 */
	String getDomainName();

	/**
	 * Create a CNAME record from <code>aliasFQDN</code> to <code>fqdn</code>.
	 * 
	 * @param aliasFQDN
	 *            The fully qualified domain name being pointed to <code>fqdn</code>
	 * @param fqdn
	 *            The fully qualified domain name <code>aliasFQDN</code> points to after this method returns.
	 * @param timeToLiveInSeconds
	 *            The amount of time DNS servers are allowed to cache the new CNAME record. A TTL value of 300 means if the CNAME record is changed, DNS servers around the world
	 *            may still be showing the old value for up to 5 minutes.
	 * 
	 * @throws IllegalArgumentException
	 *             <ul>
	 *             <li>If either <code>aliasFQDN</code> or <code>fqdn</code> are blank or are not syntactically valid DNS names
	 *             <li>If <code>aliasFQDN</code> does not end with this domain.</li>
	 *             <li>If <code>timeToLiveInSeconds</code> is negative.</li>
	 *             </ul>
	 */
	DnsRecord cnameCreate(String aliasFQDN, String fqdn, int timeToLiveInSeconds);

	/**
	 * Return true if a DNS CNAME record for <code>fqdn</code> exists, false otherwise.
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>fqdn</code> is blank, is not a syntactically valid DNS name, or does not end with this domain name.
	 */
	boolean cnameExists(String fqdn);

	/**
	 * Delete the DNS CNAME record for <code>fqdn</code>, if one exists.
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>fqdn</code> is blank, is not a syntactically valid DNS name, or does not end with this domain name.
	 */
	void cnameDelete(String fqdn);

	/**
	 * Return the DNS CNAME record for <code>fqdn</code>.
	 * 
	 * @throws IllegalArgumentException
	 *             <ul>
	 *             <li>If <code>fqdn</code> is blank, is not a syntactically valid DNS name, or does not end with this domain name.</li>
	 *             <li>If there is no corresponding DNS record for <code>fqdn</code></li>
	 *             </ul>
	 */
	DnsRecord cnameGet(String fqdn);

	/**
	 * Return a list of all of the DNS records for this domain.
	 */
	List<DnsRecord> getRecords();

	/**
	 * Return a list of any DNS records matching the search criteria for this domain.
	 */
	List<DnsRecord> getRecords(DnsRecordSearchCriteria searchCriteria);

}

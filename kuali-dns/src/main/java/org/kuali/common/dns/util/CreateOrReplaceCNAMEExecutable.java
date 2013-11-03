package org.kuali.common.dns.util;

import org.kuali.common.dns.api.DnsService;
import org.kuali.common.util.Assert;
import org.kuali.common.util.execute.Executable;

public class CreateOrReplaceCNAMEExecutable implements Executable {

	public static final int DEFAULT_TTL = 60;
	public static final boolean DEFAULT_SKIP = false;

	public CreateOrReplaceCNAMEExecutable(DnsService service, String aliasFQDN, String fqdn) {
		this(service, aliasFQDN, fqdn, DEFAULT_TTL, DEFAULT_SKIP);
	}

	public CreateOrReplaceCNAMEExecutable(DnsService service, String aliasFQDN, String fqdn, int timeToLiveInSeconds) {
		this(service, aliasFQDN, fqdn, timeToLiveInSeconds, DEFAULT_SKIP);
	}

	public CreateOrReplaceCNAMEExecutable(DnsService service, String aliasFQDN, String fqdn, int timeToLiveInSeconds, boolean skip) {
		Assert.noNulls(service);
		Assert.noBlanks(aliasFQDN, fqdn);
		Assert.noNegatives(timeToLiveInSeconds);
		this.service = service;
		this.aliasFQDN = aliasFQDN;
		this.fqdn = fqdn;
		this.timeToLiveInSeconds = timeToLiveInSeconds;
		this.skip = skip;
	}

	private final DnsService service;
	private final String aliasFQDN;
	private final String fqdn;
	private final int timeToLiveInSeconds;
	private final boolean skip;

	@Override
	public void execute() {
		if (skip) {
			return;
		}
		if (service.cnameExists(aliasFQDN)) {
			service.cnameDelete(aliasFQDN);
		}
		service.cnameCreate(aliasFQDN, fqdn, timeToLiveInSeconds);
	}

	public DnsService getService() {
		return service;
	}

	public String getAliasFQDN() {
		return aliasFQDN;
	}

	public String getFqdn() {
		return fqdn;
	}

	public int getTimeToLiveInSeconds() {
		return timeToLiveInSeconds;
	}

	public boolean isSkip() {
		return skip;
	}

}

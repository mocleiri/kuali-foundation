package org.kuali.common.dns.util;

import org.kuali.common.dns.api.DnsService;
import org.kuali.common.util.Assert;
import org.kuali.common.util.execute.Executable;

public class CreateOrReplaceCNAMEExecutable implements Executable {

	public static final int DEFAULT_TTL = 60;
	public static final boolean DEFAULT_SKIP = false;

	public CreateOrReplaceCNAMEExecutable(DnsService service, String aliasFQDN, String canonicalFQDN) {
		this(service, aliasFQDN, canonicalFQDN, DEFAULT_TTL, DEFAULT_SKIP);
	}

	public CreateOrReplaceCNAMEExecutable(DnsService service, String aliasFQDN, String canonicalFQDN, int timeToLiveInSeconds) {
		this(service, aliasFQDN, canonicalFQDN, timeToLiveInSeconds, DEFAULT_SKIP);
	}

	public CreateOrReplaceCNAMEExecutable(DnsService service, String aliasFQDN, String canonicalFQDN, int timeToLiveInSeconds, boolean skip) {
		Assert.noNulls(service);
		Assert.noBlanks(aliasFQDN, canonicalFQDN);
		Assert.noNegatives(timeToLiveInSeconds);
		this.service = service;
		this.aliasFQDN = aliasFQDN;
		this.canonicalFQDN = canonicalFQDN;
		this.timeToLiveInSeconds = timeToLiveInSeconds;
		this.skip = skip;
	}

	private final DnsService service;
	private final String aliasFQDN;
	private final String canonicalFQDN;
	private final int timeToLiveInSeconds;
	private final boolean skip;

	@Override
	public void execute() {
		if (skip) {
			return;
		}
		service.deleteCNAMERecord(aliasFQDN);
		service.createCNAMERecord(aliasFQDN, canonicalFQDN, timeToLiveInSeconds);
	}

	public DnsService getService() {
		return service;
	}

	public String getAliasFQDN() {
		return aliasFQDN;
	}

	public String getCanonicalFQDN() {
		return canonicalFQDN;
	}

	public int getTimeToLiveInSeconds() {
		return timeToLiveInSeconds;
	}

	public boolean isSkip() {
		return skip;
	}

}

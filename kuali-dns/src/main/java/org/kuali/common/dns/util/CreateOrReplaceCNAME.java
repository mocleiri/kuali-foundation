package org.kuali.common.dns.util;

import org.kuali.common.dns.api.DnsService;
import org.kuali.common.dns.model.DnsRecord;
import org.kuali.common.util.Assert;
import org.kuali.common.util.execute.Executable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Optional;

public class CreateOrReplaceCNAME implements Executable {

	private static final Logger logger = LoggerFactory.getLogger(CreateOrReplaceCNAME.class);

	public static final int DEFAULT_TTL = 60;
	public static final boolean DEFAULT_SKIP = false;

	public CreateOrReplaceCNAME(DnsService service, String aliasFQDN, String canonicalFQDN) {
		this(service, aliasFQDN, canonicalFQDN, DEFAULT_TTL, DEFAULT_SKIP);
	}

	public CreateOrReplaceCNAME(DnsService service, String aliasFQDN, String canonicalFQDN, int timeToLiveInSeconds) {
		this(service, aliasFQDN, canonicalFQDN, timeToLiveInSeconds, DEFAULT_SKIP);
	}

	public CreateOrReplaceCNAME(DnsService service, String aliasFQDN, String canonicalFQDN, int timeToLiveInSeconds, boolean skip) {
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

		// Might be skipping execution all together
		if (skip) {
			return;
		}

		// Extract the DNS record corresponding to the alias FQDN
		Optional<DnsRecord> record = service.getCNAMERecord(aliasFQDN);

		// If this is true, we are good to go, DNS is already setup
		if (existsAndMatches(record)) {
			return;
		}

		// Otherwise, check to see if there is an existing record
		if (record.isPresent()) {

			// There is an existing record but it doesn't match, we must delete it
			logger.info("deleting DNS CNAME record for [{}]", aliasFQDN);
			service.deleteCNAMERecord(aliasFQDN);
		}

		// There may have never been a DNS record OR we just deleted one
		// Either way, need to create a new one
		logger.info("creating DNS CNAME record for [{}] -> [{}]", aliasFQDN, canonicalFQDN);
		service.createCNAMERecord(aliasFQDN, canonicalFQDN, timeToLiveInSeconds);
	}

	protected boolean existsAndMatches(Optional<DnsRecord> record) {
		if (!record.isPresent()) {
			return false;
		}
		String existingValue = record.get().getValue();
		String cnameRecordValue = service.getCNAMERecordValueFromFQDN(canonicalFQDN);
		return cnameRecordValue.equals(existingValue);
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

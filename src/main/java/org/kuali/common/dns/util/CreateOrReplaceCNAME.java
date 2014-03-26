package org.kuali.common.dns.util;

import static java.lang.String.format;
import static org.kuali.common.util.base.Precondition.checkNotNull;
import static org.kuali.common.util.log.Loggers.newLogger;

import org.kuali.common.dns.api.DnsService;
import org.kuali.common.dns.model.CNAMEContext;
import org.kuali.common.dns.model.DnsRecord;
import org.kuali.common.util.execute.Executable;
import org.slf4j.Logger;

import com.google.common.base.Optional;

public final class CreateOrReplaceCNAME implements Executable {

	private static final Logger logger = newLogger();

	public CreateOrReplaceCNAME(DnsService service, CNAMEContext context) {
		this(service, context, false);
	}

	public CreateOrReplaceCNAME(DnsService service, CNAMEContext context, boolean skip) {
		this.service = checkNotNull(service, "service");
		this.context = checkNotNull(context, "context");
		this.skip = skip;
	}

	private final DnsService service;
	private final CNAMEContext context;
	private final boolean skip;

	@Override
	public void execute() {

		// Might be skipping execution all together
		if (skip) {
			return;
		}

		// Extract the DNS record corresponding to the alias FQDN
		Optional<DnsRecord> record = service.getCNAMERecord(context.getAliasFQDN());

		// If this is true, we are good to go, DNS is already setup correctly
		if (existsAndMatches(record)) {
			return;
		}

		// Otherwise, check to see if there is an existing record
		if (record.isPresent()) {

			// There is an existing record but it doesn't match, we must delete it
			logger.info(format("deleting cname record for [%s]", context.getAliasFQDN()));
			service.deleteCNAMERecord(context.getAliasFQDN());
		}

		// There may have never been a DNS record OR we just deleted one
		// Either way, need to create a new one
		logger.info(format("creating cname record for [%s] -> [%s]", context.getAliasFQDN(), context.getCanonicalFQDN()));
		service.createCNAMERecord(context.getAliasFQDN(), context.getCanonicalFQDN(), context.getTimeToLiveInSeconds());
	}

	protected boolean existsAndMatches(Optional<DnsRecord> record) {
		if (!record.isPresent()) {
			return false;
		}
		String existingValue = record.get().getValue();
		String cnameRecordValue = service.getCNAMERecordValueFromFQDN(context.getCanonicalFQDN());
		return cnameRecordValue.equals(existingValue);
	}

	public DnsService getService() {
		return service;
	}

	public boolean isSkip() {
		return skip;
	}

	public CNAMEContext getContext() {
		return context;
	}

}

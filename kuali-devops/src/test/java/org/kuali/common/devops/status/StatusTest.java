package org.kuali.common.devops.status;

import static com.google.common.base.Preconditions.checkState;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.codehaus.plexus.util.StringUtils;
import org.junit.Test;
import org.kuali.common.aws.Credentials;
import org.kuali.common.aws.ec2.api.EC2Service;
import org.kuali.common.aws.ec2.impl.DefaultEC2Service;
import org.kuali.common.aws.ec2.model.EC2ServiceContext;
import org.kuali.common.devops.util.Auth;
import org.kuali.common.devops.util.Fqdns;
import org.kuali.common.devops.util.Instances;
import org.kuali.common.dns.api.DnsService;
import org.kuali.common.dns.dnsme.DNSMadeEasyDnsService;
import org.kuali.common.dns.dnsme.URLS;
import org.kuali.common.dns.dnsme.model.DNSMadeEasyServiceContext;
import org.kuali.common.dns.model.DnsRecord;
import org.kuali.common.dns.model.DnsRecordSearchCriteria;
import org.kuali.common.dns.model.DnsRecordType;
import org.kuali.common.util.log.LoggerUtils;
import org.kuali.common.util.wait.DefaultWaitService;
import org.kuali.common.util.wait.WaitService;
import org.slf4j.Logger;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.Tag;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class StatusTest {

	private static final Logger logger = LoggerUtils.make();
	private static final String DOMAIN = "kuali.org";

	@Test
	public void test() {
		try {
			Map<String, String> fqdns = Fqdns.getMap();
			Map<String, List<Instance>> instances = Instances.getMap();
			for (String key : fqdns.keySet()) {
				logger.info(String.format("[%s]=[%s]", key, fqdns.get(key)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected String getProjectName(String accessKey) {
		for (Credentials credentials : Credentials.values()) {
			if (accessKey.equals(credentials.getAWSAccessKeyId())) {
				return credentials.name().toLowerCase();
			}
		}
		throw new IllegalArgumentException(String.format("Unable to locate a name for [%s]", accessKey));
	}

	protected List<Instance> filter(List<Instance> instances) {
		List<Instance> filtered = Lists.newArrayList();
		for (Instance instance : instances) {
			boolean env = isDeployEnvironment(instance);
			if (env) {
				filtered.add(instance);
			}
		}
		return filtered;
	}

	protected static Tag getRequiredTag(Instance instance, String key) {
		Optional<Tag> optional = getTag(instance, key);
		checkState(optional.isPresent(), "Required tag [%s] is not present for instance [%s]", key, instance.getInstanceId());
		return optional.get();
	}

	protected static Optional<Tag> getTag(Instance instance, String key) {
		List<Tag> tags = instance.getTags();
		for (Tag tag : tags) {
			if (key.equals(tag.getKey())) {
				return Optional.of(tag);
			}
		}
		return Optional.absent();
	}

	protected boolean isDeployEnvironment(Instance instance) {
		Optional<Tag> tag = getTag(instance, "Name");
		if (!tag.isPresent()) {
			return false;
		}
		String value = tag.get().getValue();
		return value.startsWith("env");
	}

	protected Map<String, List<Instance>> getMap() {
		List<AWSCredentials> creds = Auth.getCredentials();
		logger.info(String.format("Using %s sets of AWS credentials", creds.size()));
		WaitService ws = new DefaultWaitService();
		Map<String, List<Instance>> instances = Maps.newHashMap();
		for (AWSCredentials credentials : creds) {
			EC2ServiceContext context = EC2ServiceContext.create(credentials);
			EC2Service service = new DefaultEC2Service(context, ws);
			List<Instance> list = Lists.newArrayList(service.getInstances());
			Iterator<Instance> itr = list.iterator();
			while (itr.hasNext()) {
				Instance i = itr.next();
				if (!service.isOnline(i.getInstanceId())) {
					itr.remove();
				}
			}
			String projectName = getProjectName(credentials.getAWSAccessKeyId());
			instances.put(projectName, list);
			logger.debug(String.format("Located %s instances for %s", list.size(), projectName));
		}
		for (String key : instances.keySet()) {
			List<Instance> list = instances.get(key);
			List<Instance> filtered = filter(list);
			logger.info(String.format("%s -> %s environments", StringUtils.rightPad(key, 12), filtered.size()));
			instances.put(key, filtered);
		}
		return instances;
	}

	/**
	 * The keys are the jacked up Amazon FQDN's, the values are friendly alias FQDN's from DNSME.
	 */
	protected Map<String, String> getFQDNs() {
		DNSMadeEasyServiceContext context = new DNSMadeEasyServiceContext(Auth.getDnsmeCredentials(), URLS.PRODUCTION, DOMAIN);
		DnsService dns = new DNSMadeEasyDnsService(context);
		DnsRecordSearchCriteria criteria = new DnsRecordSearchCriteria(DnsRecordType.CNAME);
		List<DnsRecord> records = dns.getRecords(criteria);
		logger.info(String.format("Located %s dns records for [%s]", records.size(), DOMAIN));
		Map<String, String> map = Maps.newTreeMap();
		for (DnsRecord record : records) {
			String key = record.getValue();
			if (key.endsWith(".")) {
				key = key.substring(0, key.length() - 1);
			}
			String value = record.getName() + "." + DOMAIN;
			map.put(key, value);
		}
		return ImmutableMap.copyOf(map);
	}

}

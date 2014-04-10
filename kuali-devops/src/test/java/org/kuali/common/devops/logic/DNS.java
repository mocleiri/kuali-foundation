package org.kuali.common.devops.logic;

import static org.kuali.common.dns.model.DnsRecordSearchCriteria.newDnsRecordSearchCriteria;
import static org.kuali.common.dns.model.DnsRecordType.CNAME;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.kuali.common.dns.api.DnsService;
import org.kuali.common.dns.dnsme.DNSMadeEasyDnsService;
import org.kuali.common.dns.dnsme.URLS;
import org.kuali.common.dns.dnsme.model.DNSMadeEasyServiceContext;
import org.kuali.common.dns.model.DnsRecord;
import org.kuali.common.dns.model.DnsRecordSearchCriteria;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.file.CanonicalFile;
import org.kuali.common.util.log.LoggerUtils;
import org.kuali.common.util.property.ImmutableProperties;
import org.slf4j.Logger;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.ImmutableBiMap;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.google.common.collect.Multiset;
import com.google.common.collect.Sets;

public class DNS {

	private static final Logger logger = LoggerUtils.make();
	private static final String DOMAIN = "kuali.org";
	private static final File CACHE = new CanonicalFile("./target/cache/dns/dnsme/cnames.properties");

	/**
	 * Return a mapping of aliases to canonical name records. There can be multiple aliases pointing to the same canonical name record.
	 * 
	 * <pre>
	 * alias                  canonical
	 * env1.rice.kuali.org -> ec2-174-129-109-246.compute-1.amazonaws.com
	 * </pre>
	 */
	public static Map<String, String> getAliasMap(boolean refresh) {
		if (refresh || !CACHE.exists()) {
			Map<String, String> records = queryProvider();
			store(records);
			return records;
		} else {
			return load();
		}
	}

	/**
	 * Return a mapping of canonical name records to aliases. The only canonical name records present in this map are those that contain one (and only one) alias.
	 * 
	 * This allows you to deterministically find the correct alias if you have the canonical name record.
	 * 
	 * <pre>
	 * canonical                                      alias
	 * ec2-174-129-109-246.compute-1.amazonaws.com -> env1.rice.kuali.org
	 * </pre>
	 */
	public static BiMap<String, String> getCanonicalMap(boolean refresh) {
		Map<String, String> map = Maps.newHashMap(getAliasMap(refresh));
		removeAllKeysWithDuplicateValues(map);
		BiMap<String, String> aliases = HashBiMap.create(map);
		BiMap<String, String> canonical = aliases.inverse();
		return ImmutableBiMap.copyOf(canonical);
	}

	protected static <T> void removeAllKeysWithDuplicateValues(Map<?, T> map) {
		Set<T> duplicates = getDuplicateValues(map);
		Set<?> keys = Sets.newHashSet(map.keySet());
		for (Object key : keys) {
			T value = map.get(key);
			if (duplicates.contains(value)) {
				map.remove(key);
			}
		}
	}

	protected static <T> Set<T> getDuplicateValues(Map<?, T> map) {
		Multiset<T> multi = HashMultiset.create();
		multi.addAll(map.values());
		Set<T> duplicates = Sets.newHashSet();
		Set<T> elements = multi.elementSet();
		for (T element : elements) {
			if (multi.count(element) > 1) {
				duplicates.add(element);
			}
		}
		return duplicates;
	}

	protected static Map<String, String> queryProvider() {
		DNSMadeEasyServiceContext context = new DNSMadeEasyServiceContext(Auth.getDNSMECredentials(), URLS.PRODUCTION, DOMAIN);
		DnsService dns = new DNSMadeEasyDnsService(context);
		DnsRecordSearchCriteria criteria = newDnsRecordSearchCriteria(CNAME);
		List<DnsRecord> records = dns.getRecords(criteria);
		logger.info(String.format("Located %s dns records for [%s]", records.size(), DOMAIN));
		Map<String, String> map = Maps.newTreeMap();
		for (DnsRecord record : records) {
			String alias = record.getName() + "." + DOMAIN;
			String cname = trimTrailingDot(record.getValue());
			map.put(alias, cname);
		}
		return ImmutableMap.copyOf(map);
	}

	protected static Map<String, String> load() {
		return convert(PropertyUtils.load(CACHE));
	}

	protected static void store(Map<String, String> map) {
		PropertyUtils.store(convert(map), CACHE);
	}

	protected static Properties convert(Map<String, String> map) {
		Properties props = new Properties();
		props.putAll(map);
		return ImmutableProperties.copyOf(props);
	}

	protected static Map<String, String> convert(Properties props) {
		Map<String, String> map = Maps.newTreeMap();
		for (String key : props.stringPropertyNames()) {
			map.put(key, props.getProperty(key));
		}
		return ImmutableMap.copyOf(map);
	}

	protected static String trimTrailingDot(String key) {
		if (key.endsWith(".")) {
			return key.substring(0, key.length() - 1);
		} else {
			return key;
		}
	}

}

package org.kuali.common.devops.logic;

import static com.google.common.base.Optional.fromNullable;
import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static java.lang.String.format;
import static org.apache.commons.io.FileUtils.writeLines;
import static org.apache.commons.lang.StringUtils.trimToNull;
import static org.apache.commons.lang3.StringUtils.isBlank;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.SortedMap;

import org.apache.commons.io.FileUtils;
import org.kuali.common.aws.ec2.api.EC2Service;
import org.kuali.common.aws.ec2.impl.DefaultEC2Service;
import org.kuali.common.aws.ec2.model.EC2ServiceContext;
import org.kuali.common.devops.model.EC2Instance;
import org.kuali.common.devops.model.TableCellDescriptor;
import org.kuali.common.util.Encodings;
import org.kuali.common.util.file.CanonicalFile;
import org.kuali.common.util.log.Loggers;
import org.kuali.common.util.wait.DefaultWaitService;
import org.kuali.common.util.wait.WaitService;
import org.slf4j.Logger;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.Tag;
import com.google.common.base.Optional;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Table;

public class Instances {

	private static final File CACHE_DIR = new CanonicalFile("./target/aws/ec2");
	private static final Logger logger = Loggers.make();
	private static final String ENCODING = Encodings.UTF8;
	private static final String EC2_NAME_TAG_KEY = "Name";

	public static SortedMap<String, List<EC2Instance>> getInstances(boolean refresh) {
		SortedMap<String, List<EC2Instance>> map = Maps.newTreeMap();
		Set<String> accounts = Auth.getAwsAccounts();
		for (String account : accounts) {
			map.put(account, getInstances(account, refresh));
		}
		return map;
	}

	protected static List<EC2Instance> getInstances(String account, boolean refresh) {
		File cache = getCacheFile(account);
		if (refresh || !cache.exists()) {
			AWSCredentials creds = Auth.getAwsCredentials(account);
			List<EC2Instance> instances = queryAmazon(creds);
			store(cache, instances);
			return instances;
		} else {
			return load(cache);
		}
	}

	protected static File getCacheFile(String account) {
		return new CanonicalFile(CACHE_DIR, account + ".txt");
	}

	protected static List<EC2Instance> load(File file) {
		try {
			logger.info(format("loading -> [%s]", file));
			List<String> lines = FileUtils.readLines(file, ENCODING);
			Table<Integer, String, TableCellDescriptor<String>> table = Tables.getTableFromCSV(lines, EC2Instance.class);
			ToListFunction<Integer, String, EC2Instance> function = new ToListFunction.Builder<Integer, String, EC2Instance>().targetType(EC2Instance.class).build();
			return function.apply(table);
		} catch (IOException e) {
			throw Exceptions.illegalState(e, "unexpected io error -> [%s]", file);
		}
	}

	protected static List<EC2Instance> queryAmazon(AWSCredentials creds) {
		WaitService ws = new DefaultWaitService();
		EC2ServiceContext context = EC2ServiceContext.create(creds);
		EC2Service service = new DefaultEC2Service(context, ws);
		List<EC2Instance> instances = convert(service.getInstances());
		Collections.sort(instances);
		return instances;
	}

	protected static void store(File file, List<EC2Instance> instances) {
		Table<Integer, String, TableCellDescriptor<Object>> table = Tables.getTable(instances, EC2Instance.class);
		ToCsvFunction<Integer, String> function = new ToCsvFunction<Integer, String>();
		List<String> lines = function.apply(table);
		store(file, lines, ENCODING);
	}

	protected static void store(File file, List<String> lines, String encoding) {
		checkNotNull(file, "'file' cannot be null");
		checkNotNull(lines, "'lines' cannot be null");
		checkArgument(!isBlank(encoding), "'encoding' cannot be blank");
		try {
			logger.info(format("creating -> [%s]", file));
			writeLines(file, encoding, lines);
		} catch (IOException e) {
			throw Exceptions.illegalState(e, "unexpected io error -> [%s]", file);
		}
	}

	protected static List<EC2Instance> convert(List<Instance> instances) {
		List<EC2Instance> list = Lists.newArrayList();
		for (Instance instance : instances) {
			list.add(convert(instance));
		}
		return list;
	}

	protected static EC2Instance convert(Instance instance) {
		String id = instance.getInstanceId();
		Optional<String> name = getName(instance);
		Optional<String> publicDnsName = fromNullable(trimToNull(instance.getPublicDnsName()));
		String type = instance.getInstanceType();
		long launchTime = instance.getLaunchTime().getTime();
		String ami = instance.getImageId();
		String state = instance.getState().getName();
		return EC2Instance.builder().id(id).name(name).publicDnsName(publicDnsName).type(type).launchTime(launchTime).ami(ami).state(state).build();
	}

	protected static Optional<String> getName(Instance instance) {
		Optional<Tag> name = getTag(instance, EC2_NAME_TAG_KEY);
		if (name.isPresent()) {
			return Optional.of(name.get().getValue());
		} else {
			return Optional.absent();
		}
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

}

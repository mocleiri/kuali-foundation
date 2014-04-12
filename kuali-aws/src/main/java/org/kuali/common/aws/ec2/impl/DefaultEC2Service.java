/**
 * Copyright 2004-2014 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl2.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.common.aws.ec2.impl;

import static com.google.common.base.Optional.absent;
import static com.google.common.base.Optional.fromNullable;
import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkState;
import static com.google.common.collect.Lists.newArrayList;
import static java.lang.String.format;
import static java.util.Collections.singletonList;
import static org.kuali.common.aws.ec2.model.InstanceStateName.RUNNING;
import static org.kuali.common.aws.ec2.model.InstanceStateName.STOPPED;
import static org.kuali.common.aws.ec2.model.InstanceStateName.TERMINATED;
import static org.kuali.common.util.FormatUtils.getTime;
import static org.kuali.common.util.base.Exceptions.illegalState;
import static org.kuali.common.util.base.Precondition.checkNotBlank;
import static org.kuali.common.util.base.Precondition.checkNotNull;
import static org.kuali.common.util.base.Threads.sleep;
import static org.kuali.common.util.log.Loggers.newLogger;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.kuali.common.aws.ec2.api.EC2Service;
import org.kuali.common.aws.ec2.model.CreateAMIRequest;
import org.kuali.common.aws.ec2.model.EC2ServiceContext;
import org.kuali.common.aws.ec2.model.InstanceStateName;
import org.kuali.common.aws.ec2.model.LaunchInstanceContext;
import org.kuali.common.aws.ec2.model.Regions;
import org.kuali.common.aws.ec2.model.RootVolume;
import org.kuali.common.aws.ec2.model.security.KualiSecurityGroup;
import org.kuali.common.aws.ec2.model.security.Permission;
import org.kuali.common.aws.ec2.model.security.Protocol;
import org.kuali.common.aws.ec2.model.security.SetPermissionsResult;
import org.kuali.common.aws.ec2.model.status.InstanceStatusType;
import org.kuali.common.aws.ec2.model.status.InstanceStatusValue;
import org.kuali.common.aws.ec2.util.LaunchUtils;
import org.kuali.common.core.ssh.PublicKey;
import org.kuali.common.util.Assert;
import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.FormatUtils;
import org.kuali.common.util.SetUtils;
import org.kuali.common.util.condition.Condition;
import org.kuali.common.util.wait.DefaultWaitService;
import org.kuali.common.util.wait.WaitContext;
import org.kuali.common.util.wait.WaitResult;
import org.kuali.common.util.wait.WaitService;
import org.slf4j.Logger;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.regions.RegionUtils;
import com.amazonaws.services.ec2.AmazonEC2Client;
import com.amazonaws.services.ec2.model.AuthorizeSecurityGroupIngressRequest;
import com.amazonaws.services.ec2.model.BlockDeviceMapping;
import com.amazonaws.services.ec2.model.CopyImageRequest;
import com.amazonaws.services.ec2.model.CopyImageResult;
import com.amazonaws.services.ec2.model.CreateSecurityGroupRequest;
import com.amazonaws.services.ec2.model.CreateSnapshotRequest;
import com.amazonaws.services.ec2.model.CreateSnapshotResult;
import com.amazonaws.services.ec2.model.CreateTagsRequest;
import com.amazonaws.services.ec2.model.DeleteSnapshotRequest;
import com.amazonaws.services.ec2.model.DeregisterImageRequest;
import com.amazonaws.services.ec2.model.DescribeImagesRequest;
import com.amazonaws.services.ec2.model.DescribeImagesResult;
import com.amazonaws.services.ec2.model.DescribeInstanceStatusRequest;
import com.amazonaws.services.ec2.model.DescribeInstanceStatusResult;
import com.amazonaws.services.ec2.model.DescribeInstancesRequest;
import com.amazonaws.services.ec2.model.DescribeInstancesResult;
import com.amazonaws.services.ec2.model.DescribeKeyPairsResult;
import com.amazonaws.services.ec2.model.DescribeSecurityGroupsRequest;
import com.amazonaws.services.ec2.model.DescribeSecurityGroupsResult;
import com.amazonaws.services.ec2.model.DescribeSnapshotsRequest;
import com.amazonaws.services.ec2.model.DescribeSnapshotsResult;
import com.amazonaws.services.ec2.model.EbsBlockDevice;
import com.amazonaws.services.ec2.model.EbsInstanceBlockDevice;
import com.amazonaws.services.ec2.model.Image;
import com.amazonaws.services.ec2.model.ImportKeyPairRequest;
import com.amazonaws.services.ec2.model.ImportKeyPairResult;
import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.InstanceBlockDeviceMapping;
import com.amazonaws.services.ec2.model.InstanceStatus;
import com.amazonaws.services.ec2.model.InstanceStatusDetails;
import com.amazonaws.services.ec2.model.InstanceStatusSummary;
import com.amazonaws.services.ec2.model.IpPermission;
import com.amazonaws.services.ec2.model.KeyPairInfo;
import com.amazonaws.services.ec2.model.ModifyInstanceAttributeRequest;
import com.amazonaws.services.ec2.model.Placement;
import com.amazonaws.services.ec2.model.Region;
import com.amazonaws.services.ec2.model.RegisterImageRequest;
import com.amazonaws.services.ec2.model.RegisterImageResult;
import com.amazonaws.services.ec2.model.Reservation;
import com.amazonaws.services.ec2.model.RevokeSecurityGroupIngressRequest;
import com.amazonaws.services.ec2.model.RunInstancesRequest;
import com.amazonaws.services.ec2.model.RunInstancesResult;
import com.amazonaws.services.ec2.model.SecurityGroup;
import com.amazonaws.services.ec2.model.Snapshot;
import com.amazonaws.services.ec2.model.StartInstancesRequest;
import com.amazonaws.services.ec2.model.StopInstancesRequest;
import com.amazonaws.services.ec2.model.Tag;
import com.amazonaws.services.ec2.model.TerminateInstancesRequest;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

/**
 * This service implementation performs operations using a single set of AWS credentials on a single EC2 region.
 */
public final class DefaultEC2Service implements EC2Service {

	private static final Logger logger = newLogger();
	private static final String SNAPSHOT_COMPLETED_STATE = "completed";
	private static final String AMI_AVAILABLE_STATE = "available";

	// Don't expose the AmazonEC2Client object via a getter
	// It is mutable and therefore not inherently thread safe
	private final AmazonEC2Client client;

	private final EC2ServiceContext context;
	private final WaitService service;

	public DefaultEC2Service(AWSCredentials credentials) {
		this(credentials, new Region().withRegionName(Regions.DEFAULT_REGION.getName()));
	}

	public DefaultEC2Service(AWSCredentials credentials, String region) {
		this(credentials, new Region().withRegionName(region));
	}

	public DefaultEC2Service(AWSCredentials credentials, Region region) {
		this(EC2ServiceContext.create(credentials, region), new DefaultWaitService());
	}

	public DefaultEC2Service(EC2ServiceContext context, WaitService service) {
		this.context = checkNotNull(context, "context");
		this.service = checkNotNull(service, "service");
		this.client = LaunchUtils.getClient(context);
	}

	@Override
	public String getAccessKey() {
		return context.getCredentials().getAWSAccessKeyId();
	}

	@Override
	public String getRegion() {
		return context.getRegion();
	}

	@Override
	public String copyAmi(String region, String ami) {
		checkNotBlank(region, "region");
		checkNotBlank(ami, "ami");
		Preconditions.checkNotNull(RegionUtils.getRegion(region), "region %s is unknown", region);
		CopyImageRequest request = new CopyImageRequest();
		request.setSourceImageId(ami);
		request.setSourceRegion(region);
		CopyImageResult result = client.copyImage(request);
		return result.getImageId();
	}

	@Override
	public List<Image> getImages() {
		return client.describeImages().getImages();
	}

	@Override
	public void deleteSnapshot(String snapshotId) {
		DeleteSnapshotRequest request = new DeleteSnapshotRequest();
		request.setSnapshotId(snapshotId);
		client.deleteSnapshot(request);
	}

	@Override
	public void purgeAmi(String imageId) {
		Image image = getImage(imageId);
		List<String> snapshotIds = newArrayList();
		List<BlockDeviceMapping> mappings = image.getBlockDeviceMappings();
		for (BlockDeviceMapping mapping : mappings) {
			Optional<EbsBlockDevice> ebsBlockDevice = fromNullable(mapping.getEbs());
			if (ebsBlockDevice.isPresent()) {
				snapshotIds.add(ebsBlockDevice.get().getSnapshotId());
			}
		}
		DeregisterImageRequest request = new DeregisterImageRequest();
		request.setImageId(imageId);
		client.deregisterImage(request);
		for (String snapshotId : snapshotIds) {
			deleteSnapshot(snapshotId);
		}
	}

	@Override
	public Image createAmi(CreateAMIRequest request) {
		Instance instance = getInstance(request.getInstanceId());
		String rootVolumeId = getRootVolumeId(instance);
		Snapshot snapshot = createSnapshot(rootVolumeId, request.getDescription(), request.getTimeoutMillis());
		tag(snapshot.getSnapshotId(), request.getName());
		return createAmi(request, instance, snapshot);
	}

	protected Image createAmi(CreateAMIRequest create, Instance instance, Snapshot snapshot) {
		BlockDeviceMapping rootVolumeMapping = getRootVolumeMapping(instance, snapshot.getSnapshotId(), create.getRootVolume());
		List<BlockDeviceMapping> mappings = newArrayList();
		mappings.add(rootVolumeMapping);
		for (BlockDeviceMapping mapping : create.getAdditionalMappings()) {
			mappings.add(mapping);
		}

		RegisterImageRequest request = new RegisterImageRequest();
		request.setName(create.getName().getValue());
		request.setDescription(create.getDescription());
		request.setArchitecture(instance.getArchitecture());
		request.setRootDeviceName(instance.getRootDeviceName());
		request.setKernelId(instance.getKernelId());
		request.setBlockDeviceMappings(mappings);
		RegisterImageResult result = client.registerImage(request);
		String imageId = result.getImageId();
		waitForAmiState(imageId, AMI_AVAILABLE_STATE, create.getTimeoutMillis());
		tag(imageId, create.getName());
		return getImage(imageId);
	}

	protected BlockDeviceMapping getRootVolumeMapping(Instance instance, String snapshotId, RootVolume rootVolume) {
		InstanceBlockDeviceMapping ibdm = getRootVolumeMapping(instance);

		EbsBlockDevice ebs = new EbsBlockDevice();
		ebs.setDeleteOnTermination(rootVolume.getDeleteOnTermination().orNull());
		ebs.setSnapshotId(snapshotId);
		ebs.setVolumeSize(rootVolume.getSizeInGigabytes().orNull());

		BlockDeviceMapping bdm = new BlockDeviceMapping();
		bdm.setDeviceName(ibdm.getDeviceName());
		bdm.setEbs(ebs);
		return bdm;
	}

	protected BlockDeviceMapping convert(InstanceBlockDeviceMapping mapping, String snapshotId, int sizeInGigabytes) {
		BlockDeviceMapping converted = new BlockDeviceMapping();
		converted.setDeviceName(mapping.getDeviceName());
		converted.setEbs(convert(mapping.getEbs(), snapshotId, sizeInGigabytes));
		return converted;
	}

	protected EbsBlockDevice convert(EbsInstanceBlockDevice device, String snapshotId, int sizeInGigabytes) {
		EbsBlockDevice converted = new EbsBlockDevice();
		converted.setDeleteOnTermination(device.getDeleteOnTermination());
		converted.setSnapshotId(snapshotId);
		converted.setVolumeSize(sizeInGigabytes);
		return converted;
	}

	protected Optional<Tag> getTag(List<Tag> tags, String key) {
		for (Tag tag : tags) {
			if (tag.getKey().equals(key)) {
				return Optional.of(tag);
			}
		}
		return absent();
	}

	protected String getRootVolumeId(Instance instance) {
		checkNotNull(instance, "instance");
		InstanceBlockDeviceMapping rootMapping = getRootVolumeMapping(instance);
		return rootMapping.getEbs().getVolumeId();
	}

	protected InstanceBlockDeviceMapping getRootVolumeMapping(Instance instance) {
		checkNotNull(instance, "instance");
		String rootDeviceName = instance.getRootDeviceName();
		List<InstanceBlockDeviceMapping> mappings = instance.getBlockDeviceMappings();
		for (InstanceBlockDeviceMapping mapping : mappings) {
			if (rootDeviceName.equals(mapping.getDeviceName())) {
				return mapping;
			}
		}
		throw illegalState("Unable to locate the root volume mapping for [%s]", instance.getInstanceId());
	}

	@Override
	public Snapshot createSnapshot(String volumeId, String description, int timeoutMillis) {
		CreateSnapshotRequest request = new CreateSnapshotRequest(volumeId, description);
		CreateSnapshotResult result = client.createSnapshot(request);
		waitForSnapshotState(result.getSnapshot().getSnapshotId(), SNAPSHOT_COMPLETED_STATE, timeoutMillis);
		return result.getSnapshot();
	}

	@Override
	public Snapshot getSnapshot(String snapshotId) {
		DescribeSnapshotsRequest request = new DescribeSnapshotsRequest();
		request.setSnapshotIds(singletonList(snapshotId));
		DescribeSnapshotsResult result = client.describeSnapshots(request);
		List<Snapshot> snapshots = result.getSnapshots();
		checkState(snapshots.size() == 1, "expected 1 snapshot, but there were %s instead", snapshots.size());
		return snapshots.get(0);
	}

	protected void waitForSnapshotState(String snapshotId, String state, int timeoutMillis) {
		Condition condition = new SnapshotStateCondition(this, snapshotId, state);
		WaitContext waitContext = getWaitContext(timeoutMillis);
		Object[] args = { FormatUtils.getTime(waitContext.getTimeoutMillis()), snapshotId, state };
		logger.info(format("waiting up to %s for snapshot [%s] to reach state '%s'", args));
		WaitResult result = service.wait(waitContext, condition);
		Object[] resultArgs = { snapshotId, state, FormatUtils.getTime(result.getElapsed()) };
		logger.info(format("snapshot [%s] is now '%s' - %s", resultArgs));
	}

	protected void waitForAmiState(String imageId, String state, int timeoutMillis) {
		Condition condition = new AmiStateCondition(this, imageId, state);
		WaitContext waitContext = getWaitContext(timeoutMillis);
		Object[] args = { FormatUtils.getTime(waitContext.getTimeoutMillis()), imageId, state };
		logger.info(format("waiting up to %s for image [%s] to reach state '%s'", args));
		WaitResult result = service.wait(waitContext, condition);
		Object[] resultArgs = { imageId, state, FormatUtils.getTime(result.getElapsed()) };
		logger.info(format("ami [%s] is now '%s' - %s", resultArgs));
	}

	@Override
	public List<Image> getMyImages() {
		DescribeImagesRequest request = new DescribeImagesRequest();
		request.withOwners(AmiOwner.SELF.getValue());
		DescribeImagesResult result = client.describeImages(request);
		return result.getImages();
	}

	protected void checkSizeEquals(Collection<?> c, int size) {
		checkState(c.size() == size, "expected size of %s, was %s instead", size, c.size());
	}

	@Override
	public Image getImage(String imageId) {
		DescribeImagesRequest request = new DescribeImagesRequest();
		request.setImageIds(singletonList(imageId));
		DescribeImagesResult result = client.describeImages(request);
		List<Image> images = result.getImages();
		checkSizeEquals(images, 1);
		return images.get(0);
	}

	@Override
	public String importKey(String keyName, String publicKey) {
		checkNotBlank(keyName, "keyName");
		checkNotBlank(publicKey, "publicKey");
		ImportKeyPairRequest request = new ImportKeyPairRequest(keyName, publicKey);
		ImportKeyPairResult result = client.importKeyPair(request);
		return result.getKeyFingerprint();
	}

	@Override
	public boolean isExistingKey(String keyName) {
		checkNotBlank(keyName, "keyName");
		DescribeKeyPairsResult result = client.describeKeyPairs();
		List<KeyPairInfo> keys = result.getKeyPairs();
		Optional<KeyPairInfo> optional = getKeyPairInfo(keyName, keys);
		return optional.isPresent();
	}

	protected Optional<KeyPairInfo> getKeyPairInfo(String name, List<KeyPairInfo> list) {
		for (KeyPairInfo element : list) {
			logger.debug("fingerprint - {}, name - {}", element.getKeyFingerprint(), element.getKeyName());
			if (name.equals(element.getKeyName())) {
				return Optional.of(element);
			}
		}
		return Optional.<KeyPairInfo> absent();
	}

	@Override
	public List<String> getSecurityGroupNames() {
		DescribeSecurityGroupsResult result = client.describeSecurityGroups();
		List<String> names = newArrayList();
		for (SecurityGroup group : result.getSecurityGroups()) {
			names.add(group.getGroupName());
		}
		Collections.sort(names);
		return ImmutableList.copyOf(names);
	}

	@Override
	public void createSecurityGroup(KualiSecurityGroup group) {
		checkNotNull(group, "group");
		CreateSecurityGroupRequest request = new CreateSecurityGroupRequest();
		request.setGroupName(group.getName());
		if (group.getDescription().isPresent()) {
			request.setDescription(group.getDescription().get());
		}
		client.createSecurityGroup(request);
		setPermissions(group.getName(), group.getPermissions());
	}

	@Override
	public boolean isExistingSecurityGroup(String name) {
		checkNotBlank(name, "name");
		return getSecurityGroup(name).isPresent();
	}

	@Override
	public SetPermissionsResult setPermissions(String securityGroupName, List<Permission> permissions) {
		checkNotBlank(securityGroupName, "securityGroupName");
		checkNotNull(permissions, "permissions");
		Optional<SecurityGroup> optional = getSecurityGroup(securityGroupName);
		checkState(optional.isPresent(), "Security group [%s] does not exist", securityGroupName);
		SecurityGroup group = optional.get();
		List<IpPermission> oldPerms = group.getIpPermissions();
		List<Permission> oldPermissions = getPermissions(oldPerms);

		Set<Permission> newSet = new HashSet<Permission>(permissions);
		Set<Permission> oldSet = new HashSet<Permission>(oldPermissions);

		Set<Permission> adds = SetUtils.difference(newSet, oldSet);
		Set<Permission> deletes = SetUtils.difference(oldSet, newSet);
		Set<Permission> existing = SetUtils.intersection(newSet, oldSet);

		// Delete any permissions that are not in the list, but exist in the security group
		if (deletes.size() > 0) {
			RevokeSecurityGroupIngressRequest revoker = new RevokeSecurityGroupIngressRequest(securityGroupName, getIpPermissions(deletes));
			client.revokeSecurityGroupIngress(revoker);
		}

		// Add any permissions that are in the list but don't exist in the security group
		if (adds.size() > 0) {
			AuthorizeSecurityGroupIngressRequest authorizer = new AuthorizeSecurityGroupIngressRequest();
			authorizer.withGroupName(securityGroupName).withIpPermissions(getIpPermissions(adds));
			client.authorizeSecurityGroupIngress(authorizer);
		}

		return new SetPermissionsResult(adds, deletes, existing);
	}

	@Override
	public Optional<SecurityGroup> getSecurityGroup(String name) {
		checkNotBlank(name, "name");
		List<String> names = getSecurityGroupNames();
		if (names.contains(name)) {
			DescribeSecurityGroupsRequest request = new DescribeSecurityGroupsRequest();
			request.setGroupNames(Collections.singletonList(name));
			DescribeSecurityGroupsResult result = client.describeSecurityGroups(request);
			List<SecurityGroup> groups = result.getSecurityGroups();
			checkState(groups.size() == 1, "Expected exactly 1 security group but there were %s instead", groups.size());
			SecurityGroup group = groups.get(0);
			return Optional.of(group);
		} else {
			return Optional.<SecurityGroup> absent();
		}
	}

	protected List<IpPermission> getIpPermissions(Collection<Permission> permissions) {
		List<IpPermission> newPerms = new ArrayList<IpPermission>();
		for (Permission perm : permissions) {
			IpPermission newPerm = getIpPermission(perm);
			newPerms.add(newPerm);
		}
		return ImmutableList.copyOf(newPerms);
	}

	protected List<Permission> getPermissions(Collection<IpPermission> permissions) {
		List<Permission> newPerms = new ArrayList<Permission>();
		for (IpPermission perm : permissions) {
			Permission newPerm = getPermission(perm);
			newPerms.add(newPerm);
		}
		return ImmutableList.copyOf(newPerms);
	}

	protected Permission getPermission(IpPermission perm) {
		checkArgument(CollectionUtils.isEmpty(perm.getUserIdGroupPairs()), "User id / group pairs are not supported");
		String protocolName = perm.getIpProtocol();
		Integer fromPort = perm.getFromPort();
		Integer toPort = perm.getToPort();
		List<String> ipRanges = perm.getIpRanges();
		Assert.noNulls(fromPort, toPort, ipRanges);
		Assert.noBlanks(protocolName);
		Assert.isTrue(fromPort.equals(toPort), "port ranges are not supported");
		Protocol protocol = Protocol.valueOf(protocolName.toUpperCase());
		return Permission.builder(fromPort).withCidrNotations(ipRanges).withProtocol(protocol).build();
	}

	protected IpPermission getIpPermission(Permission perm) {
		IpPermission newPerm = new IpPermission();
		newPerm.withIpRanges(perm.getCidrNotations());
		newPerm.withIpProtocol(perm.getProtocol().getValue());
		newPerm.withFromPort(perm.getPort());
		newPerm.withToPort(perm.getPort());
		return newPerm;
	}

	@Override
	public Instance getInstance(String instanceId) {
		checkNotBlank(instanceId, "instanceId");
		List<Instance> instances = getInstances(ImmutableList.of(instanceId));
		checkState(instances.size() == 1, "Expected exactly 1 instance but there were %s instead", instances.size());
		return instances.get(0);
	}

	@Override
	public List<Instance> getInstances(List<String> instances) {
		checkNotNull(instances, "'instances' cannot be null");
		DescribeInstancesRequest request = new DescribeInstancesRequest();
		if (!instances.isEmpty()) {
			request.setInstanceIds(instances);
		}
		DescribeInstancesResult result = client.describeInstances(request);
		List<Instance> list = Lists.newArrayList();
		List<Reservation> reservations = result.getReservations();
		for (Reservation reservation : reservations) {
			list.addAll(reservation.getInstances());
		}
		return ImmutableList.copyOf(list);
	}

	@Override
	public List<Instance> getInstances() {
		return getInstances(ImmutableList.<String> of());
	}

	@Override
	public Instance launchInstance(LaunchInstanceContext context) {
		checkNotNull(context, "context");

		// Connect to AWS and ask them to create an instance
		Instance instance = issueRunInstanceRequest(context);

		// Was getting some flaky behavior from AWS without a small delay after issuing the RunInstancesRequest
		// Since it generally takes a few minutes for the instance to spin up, pausing here for 1 second should be ok
		sleep(this.context.getInitialPauseMillis());

		// Tag the instance
		tag(instance.getInstanceId(), context.getTags());

		// Wait for confirmation that the instance is online and functioning
		waitForOnlineConfirmation(instance, context);

		// Return the fully populated instance object
		return getInstance(instance.getInstanceId());
	}

	@Override
	public void allowTermination(String instanceId) {
		Assert.noBlanks(instanceId);
		preventTermination(instanceId, false);
	}

	@Override
	public void preventTermination(String instanceId) {
		Assert.noBlanks(instanceId);
		preventTermination(instanceId, true);
	}

	@Override
	public Instance startInstance(String instanceId) {
		checkNotBlank(instanceId, "instanceId");
		StartInstancesRequest request = new StartInstancesRequest();
		request.setInstanceIds(singletonList(instanceId));
		client.startInstances(request);
		WaitContext waitContext = getWaitContext(context.getTerminationTimeoutMillis());
		Object[] args = { FormatUtils.getTime(waitContext.getTimeoutMillis()), instanceId, RUNNING.getValue() };
		logger.info("Waiting up to {} for [{}] to start", args);
		Condition online = new IsOnlineCondition(this, instanceId);
		WaitResult result = service.wait(waitContext, online);
		Object[] resultArgs = { instanceId, getTime(result.getElapsed()) };
		logger.info("[{}] has been started - {}", resultArgs);
		return getInstance(instanceId);
	}

	@Override
	public void stopInstance(String instanceId) {
		checkNotBlank(instanceId, "instanceId");
		StopInstancesRequest request = new StopInstancesRequest();
		request.setInstanceIds(singletonList(instanceId));
		client.stopInstances(request);
		WaitContext waitContext = getWaitContext(context.getTerminationTimeoutMillis());
		Object[] args = { FormatUtils.getTime(waitContext.getTimeoutMillis()), instanceId, STOPPED.getValue() };
		logger.info("Waiting up to {} for [{}] to stop", args);
		Condition condition = new InstanceStateCondition(this, instanceId, STOPPED);
		WaitResult result = service.wait(waitContext, condition);
		Object[] resultArgs = { instanceId, getTime(result.getElapsed()) };
		logger.info("[{}] has been stopped - {}", resultArgs);
	}

	@Override
	public void terminateInstance(String instanceId) {
		checkNotBlank(instanceId, "instanceId");
		TerminateInstancesRequest request = new TerminateInstancesRequest();
		request.setInstanceIds(singletonList(instanceId));
		client.terminateInstances(request);
		WaitContext waitContext = getWaitContext(context.getTerminationTimeoutMillis());
		Object[] args = { FormatUtils.getTime(waitContext.getTimeoutMillis()), instanceId, TERMINATED.getValue() };
		logger.info("Waiting up to {} for [{}] to terminate", args);
		Condition condition = new InstanceStateCondition(this, instanceId, TERMINATED);
		WaitResult result = service.wait(waitContext, condition);
		Object[] resultArgs = { instanceId, getTime(result.getElapsed()) };
		logger.info("[{}] has been terminated - {}", resultArgs);
	}

	@Override
	public void tag(String resourceId, List<Tag> tags) {
		checkNotBlank(resourceId, "resourceId");
		checkNotNull(tags, "tags");
		if (CollectionUtils.isEmpty(tags)) {
			return;
		}
		List<String> resources = Collections.singletonList(resourceId);
		CreateTagsRequest request = new CreateTagsRequest(resources, tags);
		client.createTags(request);
	}

	@Override
	public void tag(String resourceId, Tag tag) {
		tag(resourceId, ImmutableList.of(tag));
	}

	@Override
	public boolean isOnline(String instanceId) {
		return new IsOnlineCondition(this, instanceId).isTrue();
	}

	@Override
	public String getStatus(String instanceId, InstanceStatusType type, String statusName) {
		List<InstanceStatus> statuses = getStatusList(instanceId);
		return getStatus(statuses, type, statusName);
	}

	protected void preventTermination(String instanceId, boolean preventTermination) {
		Assert.noBlanks(instanceId);
		ModifyInstanceAttributeRequest request = new ModifyInstanceAttributeRequest();
		request.withInstanceId(instanceId);

		// EC2 instances can normally be terminated by a single API call
		// Disabling API termination forces 2 API calls. (1 to re-enable termination, and a 2nd one to actually terminate the instance)
		request.withDisableApiTermination(preventTermination);

		client.modifyInstanceAttribute(request);
	}

	protected List<InstanceStatus> getStatusList(String instanceId) {
		DescribeInstanceStatusRequest request = new DescribeInstanceStatusRequest();
		request.setInstanceIds(Collections.singletonList(instanceId));
		DescribeInstanceStatusResult result = client.describeInstanceStatus(request);
		return result.getInstanceStatuses();
	}

	protected String getStatus(List<InstanceStatus> statuses, InstanceStatusType type, String name) {
		for (InstanceStatus status : statuses) {
			InstanceStatusSummary summary = getSummary(status, type);
			Optional<String> detail = getStatusDetail(summary, name);
			if (detail.isPresent()) {
				return detail.get();
			}
		}
		return InstanceStatusValue.UNKNOWN.getValue();
	}

	protected InstanceStatusSummary getSummary(InstanceStatus status, InstanceStatusType type) {
		switch (type) {
		case INSTANCE:
			return status.getInstanceStatus();
		case SYSTEM:
			return status.getSystemStatus();
		default:
			throw new IllegalArgumentException("[" + type + "] is unknown");
		}
	}

	protected Optional<String> getStatusDetail(InstanceStatusSummary summary, String name) {
		List<InstanceStatusDetails> details = summary.getDetails();
		for (InstanceStatusDetails detail : details) {
			if (name.equals(detail.getName())) {
				return Optional.of(detail.getStatus());
			}
		}
		return Optional.absent();
	}

	protected Instance issueRunInstanceRequest(LaunchInstanceContext context) {
		PublicKey publicKey = context.getPublicKey();
		if (!isExistingKey(publicKey.getName())) {
			logger.info("Importing key [{}]", publicKey.getName());
			importKey(publicKey.getName(), publicKey.getValue());
		}

		List<String> securityGroupNames = getSecurityGroupNames();
		for (KualiSecurityGroup securityGroup : context.getSecurityGroups()) {
			if (!securityGroupNames.contains(securityGroup.getName())) {
				logger.info("Creating security group {}", securityGroup.getName());
				createSecurityGroup(securityGroup);
			}
			if (context.isOverrideExistingSecurityGroupPermissions()) {
				SetPermissionsResult result = setPermissions(securityGroup.getName(), securityGroup.getPermissions());
				logPermissionChanges(securityGroup, result.getDeletes(), "deleted");
				logPermissionChanges(securityGroup, result.getAdds(), "added");
			}
		}

		RunInstancesRequest request = getRunInstanceRequest(context);
		RunInstancesResult result = client.runInstances(request);
		Reservation r = result.getReservation();
		List<Instance> instances = r.getInstances();
		checkState(instances.size() == 1, "Expected exactly 1 instance but there were %s instead", instances.size());
		return instances.get(0);
	}

	protected void logPermissionChanges(KualiSecurityGroup group, List<Permission> perms, String changeDescription) {
		for (Permission perm : perms) {
			String port = StringUtils.leftPad(perm.getPort() + "", 5);
			String permDescription = "port:" + port + ", protocol:" + perm.getProtocol() + ", CIDR:" + CollectionUtils.asCSV(perm.getCidrNotations());
			Object[] args = { group.getName(), StringUtils.rightPad(changeDescription, 7, " "), permDescription };
			logger.info("Security Group:[{}] - permission {} [{}]", args);
		}
	}

	protected WaitContext getWaitContext(int timeout) {
		int sleep = context.getSleepIntervalMillis();
		int pause = context.getInitialPauseMillis();
		return WaitContext.builder(timeout).sleepMillis(sleep).initialPauseMillis(pause).build();
	}

	protected void waitForOnlineConfirmation(Instance instance, LaunchInstanceContext context) {
		InstanceStateName running = InstanceStateName.RUNNING;
		WaitContext waitContext = getWaitContext(context.getTimeoutMillis());
		Object[] args = { FormatUtils.getTime(waitContext.getTimeoutMillis()), instance.getInstanceId(), running.getValue() };
		logger.info("Waiting up to {} for [{}] to come online", args);
		Condition online = new IsOnlineCondition(this, instance.getInstanceId());
		WaitResult result = service.wait(waitContext, online);
		Object[] resultArgs = { instance.getInstanceId(), FormatUtils.getTime(result.getElapsed()) };
		logger.info("[{}] is now online - {}", resultArgs);
	}

	protected List<String> getNames(List<KualiSecurityGroup> groups) {
		// Extract the names of any security groups into a list of strings
		List<String> names = new ArrayList<String>();
		for (KualiSecurityGroup group : groups) {
			names.add(group.getName());
		}
		Collections.sort(names);
		return ImmutableList.copyOf(names);
	}

	/**
	 * Return a request that spins up exactly one instance.
	 */
	protected RunInstancesRequest getRunInstanceRequest(LaunchInstanceContext context) {
		RunInstancesRequest rir = new RunInstancesRequest();
		rir.setMaxCount(1);
		rir.setMinCount(1);
		rir.setImageId(context.getAmi());
		rir.setKeyName(context.getPublicKey().getName());
		rir.setSecurityGroups(getNames(context.getSecurityGroups()));
		rir.setInstanceType(context.getType());
		rir.setDisableApiTermination(context.isPreventTermination());
		rir.setEbsOptimized(context.isEbsOptimized());
		rir.setMonitoring(context.isEnableMonitoring());

		// Update the request with an availability zone (if one has been supplied)
		if (context.getAvailabilityZone().isPresent()) {
			String zone = context.getAvailabilityZone().get();
			Placement placement = new Placement(zone);
			rir.setPlacement(placement);
		}

		// Update the request with custom root volume settings (if any have been supplied)

		// Get the list of block device mappings associated with this AMI after updating the BlockDeviceMapping for the root volume
		List<BlockDeviceMapping> mappings = getUpdatedBlockDeviceMappings(context);

		// Store the block device mappings on the request
		rir.setBlockDeviceMappings(mappings);
		return rir;
	}

	protected List<BlockDeviceMapping> getUpdatedBlockDeviceMappings(LaunchInstanceContext context) {
		// Get an Image object from Amazon for the AMI we are working with
		Image ami = getAmi(context.getAmi());

		// Update the root volume as needed
		if (context.getRootVolume().isPresent()) {
			updateRootBlockDeviceMapping(ami, context.getRootVolume().get());
		}

		// Store all of the existing block device mappings
		List<BlockDeviceMapping> mappings = newArrayList(ami.getBlockDeviceMappings());

		// Cycle through the additional mappings, updating existing mappings with new mappings as we go
		for (BlockDeviceMapping additionalMapping : context.getAdditionalMappings()) {

			// Look for a match in the existing mappings
			Optional<BlockDeviceMapping> optional = findMatch(mappings, additionalMapping);

			// Check to see if we found a match
			if (optional.isPresent()) {
				// If so, override any existing block device settings with the new settings
				BlockDeviceMapping existing = optional.get();
				existing.setDeviceName(additionalMapping.getDeviceName());
				existing.setEbs(additionalMapping.getEbs());
				existing.setNoDevice(additionalMapping.getNoDevice());
				existing.setVirtualName(additionalMapping.getVirtualName());
			} else {
				// Otherwise just add the new mapping
				mappings.add(additionalMapping);
			}
		}

		// Return the updated list
		return mappings;
	}

	protected Optional<BlockDeviceMapping> findMatch(List<BlockDeviceMapping> mappings, BlockDeviceMapping mapping) {
		for (BlockDeviceMapping element : mappings) {
			if (element.getDeviceName().equals(mapping.getDeviceName())) {
				return Optional.of(element);
			}
		}
		return absent();
	}

	protected void updateRootBlockDeviceMapping(Image ami, RootVolume rootVolume) {

		// Extract the default root block device mapping specific to this AMI
		BlockDeviceMapping mapping = getRootBlockDeviceMapping(ami);

		// Extract the block device
		EbsBlockDevice device = mapping.getEbs();

		// If a size in gigabytes has been provided, update the device with the new size
		if (rootVolume.getSizeInGigabytes().isPresent()) {
			int sizeInGigabytes = rootVolume.getSizeInGigabytes().get();
			device.setVolumeSize(sizeInGigabytes);
		}

		// If the delete on termination setting has been provided, update the device with the new setting
		if (rootVolume.getDeleteOnTermination().isPresent()) {
			boolean deleteOnTermination = rootVolume.getDeleteOnTermination().get();
			device.setDeleteOnTermination(deleteOnTermination);
		}

	}

	protected BlockDeviceMapping getRootBlockDeviceMapping(Image image) {
		String rootDeviceName = image.getRootDeviceName();
		List<BlockDeviceMapping> mappings = image.getBlockDeviceMappings();
		for (BlockDeviceMapping mapping : mappings) {
			String deviceName = mapping.getDeviceName();
			if (rootDeviceName.equals(deviceName)) {
				return mapping;
			}
		}
		throw illegalState("Could not locate the root block device mapping for AMI [%s]", image.getImageId());
	}

	public Image getAmi(String ami) {
		checkNotBlank(ami, "ami");
		DescribeImagesRequest request = new DescribeImagesRequest();
		request.setImageIds(singletonList(ami));
		DescribeImagesResult result = client.describeImages(request);
		List<Image> images = result.getImages();
		checkState(images.size() == 1, "Expected exactly 1 image but there were %s instead", images.size());
		return images.get(0);
	}

	public EC2ServiceContext getContext() {
		return context;
	}

}

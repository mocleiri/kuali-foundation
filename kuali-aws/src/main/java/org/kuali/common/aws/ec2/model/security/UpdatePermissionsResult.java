package org.kuali.common.aws.ec2.model.security;

import java.util.Collection;
import java.util.List;

import org.kuali.common.util.Assert;

import com.google.common.collect.ImmutableList;

public final class UpdatePermissionsResult {

	public UpdatePermissionsResult(Collection<Permission> adds, Collection<Permission> deletes, Collection<Permission> existing) {
		Assert.noNulls(adds, deletes, existing);
		this.adds = ImmutableList.copyOf(adds);
		this.deletes = ImmutableList.copyOf(deletes);
		this.existing = ImmutableList.copyOf(existing);
	}

	private final List<Permission> adds;
	private final List<Permission> deletes;
	private final List<Permission> existing;

	public List<Permission> getAdds() {
		return adds;
	}

	public List<Permission> getDeletes() {
		return deletes;
	}

	public List<Permission> getExisting() {
		return existing;
	}

}

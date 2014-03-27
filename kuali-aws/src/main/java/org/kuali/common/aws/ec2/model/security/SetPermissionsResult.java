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
package org.kuali.common.aws.ec2.model.security;

import java.util.Collection;
import java.util.List;

import com.google.common.collect.ImmutableList;

public final class SetPermissionsResult {

	public SetPermissionsResult(Collection<Permission> adds, Collection<Permission> deletes, Collection<Permission> existing) {
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

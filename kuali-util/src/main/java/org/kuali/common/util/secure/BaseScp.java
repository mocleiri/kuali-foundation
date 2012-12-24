/**
 * Copyright 2010-2012 The Kuali Foundation
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
package org.kuali.common.util.secure;

import java.util.List;

import org.kuali.common.util.CollectionUtils;

/**
 *
 */
public abstract class BaseScp implements Scp {

	protected abstract int executeCopy(ScpContext context, List<ScpFile> sources, ScpFile destination);

	@Override
	public int copy(ScpContext context, List<ScpFile> sources, ScpFile destination) {
		ScpUtils.validateSources(sources);
		ScpUtils.validateDestination(destination, sources);
		return executeCopy(context, sources, destination);
	}

	@Override
	public int copy(ScpContext context, ScpFile source, ScpFile destination) {
		return copy(context, CollectionUtils.toEmptyList(source), destination);
	}

	@Override
	public int copy(ScpFile source, ScpFile destination) {
		return copy(CollectionUtils.toEmptyList(source), destination);
	}

	@Override
	public int copy(List<ScpFile> sources, ScpFile destination) {
		return copy(null, sources, destination);
	}

}

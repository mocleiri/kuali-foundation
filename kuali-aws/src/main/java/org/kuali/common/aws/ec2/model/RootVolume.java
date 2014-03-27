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
package org.kuali.common.aws.ec2.model;

import static com.google.common.base.Optional.absent;
import static org.kuali.common.util.base.Precondition.checkMin;

import org.kuali.common.core.build.ValidatingBuilder;
import org.kuali.common.core.validate.annotation.IdiotProofImmutable;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.common.base.Optional;

@IdiotProofImmutable
@JsonDeserialize(builder = RootVolume.Builder.class)
public final class RootVolume {

	private final Optional<Integer> sizeInGigabytes;
	private final Optional<Boolean> deleteOnTermination;

	private RootVolume(Builder builder) {
		this.sizeInGigabytes = builder.sizeInGigabytes;
		this.deleteOnTermination = builder.deleteOnTermination;
	}

	public static RootVolume create(int sizeInGigabytes, boolean deleteOnTermination) {
		return builder().withSizeInGigabytes(sizeInGigabytes).withDeleteOnTermination(deleteOnTermination).build();
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder extends ValidatingBuilder<RootVolume> {

		private Optional<Integer> sizeInGigabytes = absent();
		private Optional<Boolean> deleteOnTermination = absent();

		@JsonSetter
		public Builder withSizeInGigabytes(Optional<Integer> sizeInGigabytes) {
			this.sizeInGigabytes = sizeInGigabytes;
			return this;
		}

		@JsonSetter
		public Builder withDeleteOnTermination(Optional<Boolean> deleteOnTermination) {
			this.deleteOnTermination = deleteOnTermination;
			return this;
		}

		public Builder withSizeInGigabytes(int sizeInGigabytes) {
			return withSizeInGigabytes(Optional.of(sizeInGigabytes));
		}

		public Builder withDeleteOnTermination(boolean deleteOnTermination) {
			return withDeleteOnTermination(Optional.of(deleteOnTermination));
		}

		@Override
		public RootVolume build() {
			RootVolume instance = validate(new RootVolume(this));
			validateInstance(instance);
			return instance;
		}

		private static void validateInstance(RootVolume instance) {
			checkMin(instance.getSizeInGigabytes(), 1, "sizeInGigabytes");
		}
	}

	public Optional<Integer> getSizeInGigabytes() {
		return sizeInGigabytes;
	}

	public Optional<Boolean> getDeleteOnTermination() {
		return deleteOnTermination;
	}

}
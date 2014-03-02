/**
 * Copyright 2010-2013 The Kuali Foundation
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
package org.kuali.common.util.maven.model;

import org.kuali.common.util.Assert;
import org.kuali.common.util.nullify.NullUtils;

import com.google.common.base.Optional;

/**
 * Simple pojo representing a Maven dependency.
 */
public final class Dependency {

	private final String groupId;
	private final String artifactId;
	private final String version;
	private final Optional<String> classifier;
	// Type is usually the same thing as "packaging" from the Artifact object, but not always.
	// For example, "test-jar" is physically packaged into a jar file but is labeled in the Maven
	// dependency list as <type>test-jar</type>
	private final String type;
	private final String scope;

	public String getGroupId() {
		return groupId;
	}

	public String getArtifactId() {
		return artifactId;
	}

	public String getVersion() {
		return version;
	}

	public Optional<String> getClassifier() {
		return classifier;
	}

	public String getType() {
		return type;
	}

	public String getScope() {
		return scope;
	}

	public static class Builder {

		public static final String DEFAULT_TYPE = Artifact.Builder.DEFAULT_TYPE;
		public static final String DEFAULT_SCOPE = "compile";

		// Required
		private final String groupId;
		private final String artifactId;
		private final String version;

		// Optional
		private Optional<String> classifier = Optional.absent();
		private String type = DEFAULT_TYPE;
		private String scope = DEFAULT_SCOPE;

		public Builder(String groupId, String artifactId, String version) {
			this.groupId = groupId;
			this.artifactId = artifactId;
			this.version = version;
		}

		public Builder classifier(String classifier) {
			this.classifier = Optional.fromNullable(NullUtils.trimToNull(classifier));
			return this;
		}

		public Builder type(String type) {
			this.type = type;
			return this;
		}

		public Builder scope(String scope) {
			this.scope = scope;
			return this;
		}

		public Dependency build() {
			Assert.noBlanks(groupId, artifactId, version, type, scope);
			Assert.noNulls(classifier);
			return new Dependency(this);
		}
	}

	private Dependency(Builder builder) {
		this.groupId = builder.groupId;
		this.artifactId = builder.artifactId;
		this.version = builder.version;
		this.classifier = builder.classifier;
		this.type = builder.type;
		this.scope = builder.scope;
	}

}

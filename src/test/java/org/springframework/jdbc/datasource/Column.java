/**
 * Copyright 2010-2014 The Kuali Foundation
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
package org.springframework.jdbc.datasource;

import javax.validation.constraints.Min;

import org.kuali.common.core.build.ValidatingBuilder;
import org.kuali.common.core.validate.annotation.IdiotProofImmutable;

import com.google.common.base.Optional;

@IdiotProofImmutable
public final class Column {

	@Min(0)
	private final int index;
	private final String name;
	private final Class<?> type;
	private final Optional<Boolean> nullable;
	private final int javaSqlType;

	private Column(Builder builder) {
		this.name = builder.name;
		this.type = builder.type;
		this.index = builder.index;
		this.nullable = builder.nullable;
		this.javaSqlType = builder.javaSqlType;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder extends ValidatingBuilder<Column> {

		private int index = -1;
		private int javaSqlType = -1;
		private String name;
		private Class<?> type;
		private Optional<Boolean> nullable;

		public Builder withJavaSqlType(int javaSqlType) {
			this.javaSqlType = javaSqlType;
			return this;
		}

		public Builder withNullable(Optional<Boolean> nullable) {
			this.nullable = nullable;
			return this;
		}

		public Builder withNullable(boolean nullable) {
			return withNullable(Optional.of(nullable));
		}

		public Builder withIndex(int index) {
			this.index = index;
			return this;
		}

		public Builder withName(String name) {
			this.name = name;
			return this;
		}

		public Builder withType(Class<?> type) {
			this.type = type;
			return this;
		}

		@Override
		public Column build() {
			return validate(new Column(this));
		}
	}

	public String getName() {
		return name;
	}

	public Class<?> getType() {
		return type;
	}

	public int getIndex() {
		return index;
	}

	public int getJavaSqlType() {
		return javaSqlType;
	}

	public Optional<Boolean> getNullable() {
		return nullable;
	}

}

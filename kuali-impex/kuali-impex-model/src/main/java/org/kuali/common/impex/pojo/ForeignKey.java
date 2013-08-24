/**
 * Copyright 2011 The Kuali Foundation Licensed under the
 * Educational Community License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may
 * obtain a copy of the License at
 *
 * http://www.osedu.org/licenses/ECL-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package org.kuali.common.impex.pojo;

import java.util.Arrays;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.kuali.common.util.Assert;
import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.ListUtils;
import org.kuali.common.util.nullify.NullUtils;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ForeignKey implements NamedElement {

	@XmlAttribute
	private final String name;

	@XmlAttribute
	private final String localTable;

	@XmlAttribute
	private final String foreignTable;

	@XmlAttribute
	private final ForeignKeyConstraintType onDelete;

	@XmlAttribute
	private final ForeignKeyConstraintType onUpdate;

	@XmlElement
	private final List<String> localColumns;

	@XmlElement
	private final List<String> foreignColumns;

	public String getForeignTable() {
		return foreignTable;
	}

	public String getLocalTable() {
		return localTable;
	}

	@Override
	public String getName() {
		return name;
	}

	public List<String> getForeignColumns() {
		return foreignColumns;
	}

	public List<String> getLocalColumns() {
		return localColumns;
	}

	public ForeignKeyConstraintType getOnDelete() {
		return onDelete;
	}

	public ForeignKeyConstraintType getOnUpdate() {
		return onUpdate;
	}

	public static class Builder {

		private final String name;
		private final String localTable;
		private final String foreignTable;

		private ForeignKeyConstraintType onDelete;
		private ForeignKeyConstraintType onUpdate;
		private List<String> localColumns;
		private List<String> foreignColumns;

		public Builder(String name, String localTable, String foreignTable) {
			this.name = name;
			this.localTable = localTable;
			this.foreignTable = foreignTable;
		}

		public Builder onDelete(ForeignKeyConstraintType onDelete) {
			this.onDelete = onDelete;
			return this;
		}

		public Builder onUpdate(ForeignKeyConstraintType onUpdate) {
			this.onUpdate = onUpdate;
			return this;
		}

		public Builder localColumns(List<String> localColumns) {
			this.localColumns = localColumns;
			return this;
		}

		public Builder foreignColumns(List<String> foreignColumns) {
			this.foreignColumns = foreignColumns;
			return this;
		}

		private Builder defaults() {
			this.onDelete = ForeignKeyConstraintType.NO_ACTION;
			this.onUpdate = ForeignKeyConstraintType.NO_ACTION;
			this.localColumns = ListUtils.newImmutableArrayList(Arrays.asList(NullUtils.NONE));
			this.foreignColumns = ListUtils.newImmutableArrayList(Arrays.asList(NullUtils.NONE));
			return this;
		}

		private Builder finish() {
			Assert.noBlanks(name, localTable, foreignTable);
			Assert.noNulls(onDelete, onUpdate, localColumns, foreignColumns);
			Assert.isFalse(CollectionUtils.isEmpty(localColumns));
			Assert.isFalse(CollectionUtils.isEmpty(foreignColumns));
			this.localColumns = ListUtils.newImmutableArrayList(localColumns);
			this.foreignColumns = ListUtils.newImmutableArrayList(foreignColumns);
			return this;
		}

		public ForeignKey build() {
			finish();
			return new ForeignKey(this);
		}
	}

	private ForeignKey() {
		this(new Builder(NullUtils.NONE, NullUtils.NONE, NullUtils.NONE).defaults().finish());
	}

	private ForeignKey(Builder builder) {
		this.name = builder.name;
		this.localTable = builder.localTable;
		this.foreignTable = builder.foreignTable;
		this.onDelete = builder.onDelete;
		this.onUpdate = builder.onUpdate;
		this.localColumns = builder.localColumns;
		this.foreignColumns = builder.foreignColumns;
	}

}

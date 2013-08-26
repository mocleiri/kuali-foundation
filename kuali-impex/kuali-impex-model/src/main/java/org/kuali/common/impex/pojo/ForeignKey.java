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

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.kuali.common.util.Assert;
import org.kuali.common.util.xml.jaxb.adapter.TrimmingCSVStringAdapter;

import com.google.common.collect.ImmutableList;

@XmlRootElement
public final class ForeignKey implements NamedElement {

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
	@XmlJavaTypeAdapter(TrimmingCSVStringAdapter.class)
	private final List<String> localColumns;

	@XmlElement
	@XmlJavaTypeAdapter(TrimmingCSVStringAdapter.class)
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
		private List<String> localColumns = ImmutableList.of();
		private List<String> foreignColumns = ImmutableList.of();

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

		private Builder initialized() {
			return this;
		}

		private Builder finish() {
			Assert.noBlanks(name, localTable, foreignTable);
			Assert.noNulls(onDelete, onUpdate, localColumns, foreignColumns);
			Assert.isTrue(localColumns.size() > 0, "No local columns");
			Assert.isTrue(foreignColumns.size() > 0, "No foreign columns");
			this.localColumns = ImmutableList.copyOf(localColumns);
			this.foreignColumns = ImmutableList.copyOf(foreignColumns);
			return this;
		}

		public ForeignKey build() {
			finish();
			return new ForeignKey(this);
		}
	}

	private ForeignKey() {
		this(new Builder(null, null, null).initialized());
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

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

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.kuali.common.util.Assert;
import org.kuali.common.util.ListUtils;
import org.kuali.common.util.nullify.NullUtils;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Schema {

	@XmlAttribute
	private final String name;

	@XmlElement
	private final List<Table> tables;

	@XmlElement
	private final List<Sequence> sequences;

	@XmlElement
	private final List<View> views;

	@XmlElement
	private final List<ForeignKey> foreignKeys;

	public String getName() {
		return name;
	}

	public List<Table> getTables() {
		return tables;
	}

	public List<Sequence> getSequences() {
		return sequences;
	}

	public List<View> getViews() {
		return views;
	}

	public List<ForeignKey> getForeignKeys() {
		return foreignKeys;
	}

	public static class Builder {

		// Required
		private final String name;

		// Optional
		private List<Table> tables;
		private List<Sequence> sequences;
		private List<View> views;
		private List<ForeignKey> foreignKeys;

		public Builder(String name) {
			this.name = name;
		}

		public Builder tables(List<Table> tables) {
			this.tables = tables;
			return this;
		}

		public Builder sequences(List<Sequence> sequences) {
			this.sequences = sequences;
			return this;
		}

		public Builder views(List<View> views) {
			this.views = views;
			return this;
		}

		public Builder foreignKeys(List<ForeignKey> foreignKeys) {
			this.foreignKeys = foreignKeys;
			return this;
		}

		private Builder finish() {
			Assert.noBlanks(name);
			Assert.noNulls(tables, sequences, views, foreignKeys);
			this.tables = ListUtils.newImmutableArrayList(tables);
			this.sequences = ListUtils.newImmutableArrayList(sequences);
			this.views = ListUtils.newImmutableArrayList(views);
			this.foreignKeys = ListUtils.newImmutableArrayList(foreignKeys);
			return this;
		}

		public Schema build() {
			finish();
			return new Schema(this);
		}

	}

	private Schema() {
		this(new Builder(NullUtils.NONE).finish());
	}

	private Schema(Builder builder) {
		this.name = builder.name;
		this.tables = builder.tables;
		this.sequences = builder.sequences;
		this.views = builder.views;
		this.foreignKeys = builder.foreignKeys;
	}

}

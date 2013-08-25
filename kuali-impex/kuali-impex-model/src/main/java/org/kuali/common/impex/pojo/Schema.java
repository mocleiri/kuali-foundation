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

import java.util.Collections;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.kuali.common.impex.pojo.adapter.MpxFlattenAdapter;
import org.kuali.common.util.Assert;
import org.kuali.common.util.nullify.NullUtils;
import org.kuali.common.util.xml.jaxb.XmlBind;
import org.kuali.common.util.xml.jaxb.adapter.ImmutableListAdapter;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlBind(classes = { Table.class, Sequence.class, View.class, ForeignKey.class })
public final class Schema {

	@XmlAttribute
	private final String name;

	@XmlAttribute
	@XmlJavaTypeAdapter(MpxFlattenAdapter.class)
	private final Optional<String> description;

	@XmlElement
	@XmlJavaTypeAdapter(ImmutableListAdapter.class)
	private final List<Table> tables;

	@XmlElement
	@XmlJavaTypeAdapter(ImmutableListAdapter.class)
	private final List<Sequence> sequences;

	@XmlElement
	@XmlJavaTypeAdapter(ImmutableListAdapter.class)
	private final List<View> views;

	@XmlElement
	@XmlJavaTypeAdapter(ImmutableListAdapter.class)
	private final List<ForeignKey> foreignKeys;

	public String getName() {
		return name;
	}

	public Optional<String> getDescription() {
		return description;
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
		private Optional<String> description = Optional.absent();
		private List<Table> tables = Collections.emptyList();
		private List<Sequence> sequences = Collections.emptyList();
		private List<View> views = Collections.emptyList();
		private List<ForeignKey> foreignKeys = Collections.emptyList();

		public Builder(String name) {
			this.name = name;
		}

		public Builder description(String description) {
			this.description = Optional.fromNullable(description);
			return this;
		}

		public Builder table(Table table) {
			return tables(ImmutableList.of(table));
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
			Assert.noNulls(description, tables, sequences, views, foreignKeys);
			this.tables = ImmutableList.copyOf(tables);
			this.sequences = ImmutableList.copyOf(sequences);
			this.views = ImmutableList.copyOf(views);
			this.foreignKeys = ImmutableList.copyOf(foreignKeys);
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
		this.description = builder.description;
	}

}

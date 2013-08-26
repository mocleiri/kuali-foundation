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
import org.kuali.common.util.xml.jaxb.XmlBind;
import org.kuali.common.util.xml.jaxb.adapter.FlattenOptionalStringAdapter;
import org.kuali.common.util.xml.jaxb.adapter.ImmutableListAdapter;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;

@XmlRootElement
@XmlBind(classes = { Table.class, Sequence.class, View.class, ForeignKey.class })
public final class Schema {

	@XmlAttribute
	private final String name;

	@XmlAttribute
	@XmlJavaTypeAdapter(FlattenOptionalStringAdapter.class)
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
		private List<Table> tables = ImmutableList.of();
		private List<Sequence> sequences = ImmutableList.of();
		private List<View> views = ImmutableList.of();
		private List<ForeignKey> foreignKeys = ImmutableList.of();

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

		public Builder sequence(Sequence sequence) {
			return sequences(ImmutableList.of(sequence));
		}

		public Builder sequences(Sequence... sequences) {
			return sequences(ImmutableList.copyOf(sequences));
		}

		public Builder sequences(List<Sequence> sequences) {
			this.sequences = sequences;
			return this;
		}

		public Builder view(View view) {
			return views(ImmutableList.of(view));
		}

		public Builder views(View... views) {
			return views(ImmutableList.copyOf(views));
		}

		public Builder views(List<View> views) {
			this.views = views;
			return this;
		}

		public Builder foreignKeys(List<ForeignKey> foreignKeys) {
			this.foreignKeys = foreignKeys;
			return this;
		}

		private Builder initialized() {
			return this;
		}

		public Schema build() {
			Assert.noBlanks(name);
			Assert.noNulls(description, tables, sequences, views, foreignKeys);
			this.tables = ImmutableList.copyOf(tables);
			this.sequences = ImmutableList.copyOf(sequences);
			this.views = ImmutableList.copyOf(views);
			this.foreignKeys = ImmutableList.copyOf(foreignKeys);
			return new Schema(this);
		}

	}

	// Necessary at the moment so JAXB can unmarshall things correctly.
	// This does open a hole that could potentially allow null to sneak into these model objects.
	// There is no "normal" way to introduce null via the Builder.
	// The assertions inside the build() method prevent it.
	// However, if someone created a Schema object using the Builder, marshalled it to disk as XML, and then edited the XML by hand
	// (removing the "name" attribute for example), then unmarshalled a Schema object from the hand edited XML, the "name" field
	// would end up being null.
	private Schema() {
		this(new Builder(null).initialized());
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

package org.kuali.common.impex.pojo;

import java.util.Collections;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.eclipse.persistence.oxm.annotations.XmlPath;
import org.kuali.common.util.Assert;
import org.kuali.common.util.nullify.NullUtils;
import org.kuali.common.util.xml.jaxb.XmlBind;
import org.kuali.common.util.xml.jaxb.adapter.FlattenOptionalStringAdapter;
import org.kuali.common.util.xml.jaxb.adapter.ImmutableListAdapter;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;

/**
 * This interface provides an implementation-independent API to access database table model information
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlBind(classes = { Column.class, UniqueConstraint.class, Index.class })
public final class Table implements NamedElement {

	@XmlAttribute
	private final String name;

	@XmlElement
	@XmlJavaTypeAdapter(ImmutableListAdapter.class)
	@XmlPath(".")
	private final List<Column> columns;

	@XmlElement
	@XmlJavaTypeAdapter(ImmutableListAdapter.class)
	@XmlPath(".")
	private final List<UniqueConstraint> uniqueConstraints;

	@XmlElement
	@XmlJavaTypeAdapter(ImmutableListAdapter.class)
	@XmlPath(".")
	private final List<Index> indexes;

	@XmlAttribute
	@XmlJavaTypeAdapter(FlattenOptionalStringAdapter.class)
	private final Optional<String> description;

	public List<Column> getColumns() {
		return columns;
	}

	public List<UniqueConstraint> getUniqueConstraints() {
		return uniqueConstraints;
	}

	@Override
	public String getName() {
		return name;
	}

	public List<Index> getIndexes() {
		return indexes;
	}

	public static class Builder {

		// Required
		private final String name;

		// Optional
		private List<Column> columns = Collections.emptyList();
		private List<UniqueConstraint> uniqueConstraints = Collections.emptyList();
		private List<Index> indexes = Collections.emptyList();
		private Optional<String> description = Optional.absent();

		public Builder(String name) {
			this.name = name;
		}

		public Builder column(Column column) {
			return columns(ImmutableList.of(column));
		}

		public Builder columns(Column... columns) {
			return columns(ImmutableList.copyOf(columns));
		}

		public Builder columns(List<Column> columns) {
			this.columns = columns;
			return this;
		}

		public Builder uniqueConstraint(UniqueConstraint uniqueConstraint) {
			return uniqueConstraints(ImmutableList.of(uniqueConstraint));
		}

		public Builder uniqueConstraints(UniqueConstraint... uniqueConstraints) {
			return uniqueConstraints(ImmutableList.copyOf(uniqueConstraints));
		}

		public Builder uniqueConstraints(List<UniqueConstraint> uniqueConstraints) {
			this.uniqueConstraints = uniqueConstraints;
			return this;
		}

		public Builder index(Index index) {
			return indexes(ImmutableList.of(index));
		}

		public Builder indexes(Index... indexes) {
			return indexes(ImmutableList.copyOf(indexes));
		}

		public Builder indexes(List<Index> indexes) {
			this.indexes = indexes;
			return this;
		}

		public Builder description(String description) {
			this.description = Optional.fromNullable(description);
			return this;
		}

		private Builder finish() {
			Assert.noBlanks(name);
			Assert.noNulls(columns, uniqueConstraints, indexes, description);
			this.columns = ImmutableList.copyOf(columns);
			this.uniqueConstraints = ImmutableList.copyOf(uniqueConstraints);
			this.indexes = ImmutableList.copyOf(indexes);
			return this;
		}

		public Table build() {
			finish();
			return new Table(this);
		}
	}

	private Table() {
		this(new Builder(NullUtils.NONE).finish());
	}

	private Table(Builder builder) {
		this.name = builder.name;
		this.columns = builder.columns;
		this.uniqueConstraints = builder.uniqueConstraints;
		this.indexes = builder.indexes;
		this.description = builder.description;
	}

}

package org.kuali.common.impex.pojo;

import java.util.Collections;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.kuali.common.util.Assert;
import org.kuali.common.util.ListUtils;
import org.kuali.common.util.nullify.NullUtils;
import org.kuali.common.util.xml.jaxb.ImmutableListAdapter;
import org.kuali.common.util.xml.jaxb.OmitOptionalStringAdapter;

import com.google.common.base.Optional;

/**
 * This interface provides an implementation-independent API to access database table model information
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Table implements NamedElement {

	@XmlElement
	private final String name;

	@XmlElement
	@XmlJavaTypeAdapter(OmitOptionalStringAdapter.class)
	private final Optional<String> description;

	@XmlElement
	@XmlJavaTypeAdapter(ImmutableListAdapter.class)
	private final List<Column> columns;

	@XmlElement
	@XmlJavaTypeAdapter(ImmutableListAdapter.class)
	private final List<UniqueConstraint> uniqueConstraints;

	@XmlElement
	@XmlJavaTypeAdapter(ImmutableListAdapter.class)
	private final List<Index> indexes;

	@SuppressWarnings("unused")
	private Table() {
		this(NullUtils.NONE, Optional.<String> absent(), Collections.<Column> emptyList(), Collections.<UniqueConstraint> emptyList(), Collections.<Index> emptyList());
	}

	public Table(String name, Optional<String> description, List<Column> columns, List<UniqueConstraint> uniqueConstraints, List<Index> indexes) {
		Assert.noBlanks(name);
		Assert.noNulls(description, columns, uniqueConstraints, indexes);
		this.name = name;
		this.description = description;
		this.columns = ListUtils.newImmutableArrayList(columns);
		this.uniqueConstraints = ListUtils.newImmutableArrayList(uniqueConstraints);
		this.indexes = ListUtils.newImmutableArrayList(indexes);
	}

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

}

package org.kuali.common.impex.model;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.kuali.common.impex.model.adapter.TableAdapter;
import org.kuali.common.util.Assert;
import org.kuali.common.util.nullify.NullUtils;

@XmlJavaTypeAdapter(TableAdapter.class)
public final class ImmutableTable implements NamedElement {
	
	public static final String DEFAULT_DESCRIPTION = NullUtils.NONE;

	private final String name;
	private final String description;

	public ImmutableTable(String name) {
		this(name, DEFAULT_DESCRIPTION);
	}

	public ImmutableTable(String name, String description) {
		Assert.noBlanks(name, description);
		this.description = description;
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

}

package org.kuali.common.impex.pojo;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.kuali.common.util.Assert;
import org.kuali.common.util.xml.jaxb.adapter.FlattenStringAdapter;

@XmlRootElement
public final class View implements NamedElement {

	@XmlAttribute
	private final String name;

	@XmlAttribute
	@XmlJavaTypeAdapter(FlattenStringAdapter.class)
	private final String query;

	@SuppressWarnings("unused")
	private View() {
		this.name = null;
		this.query = null;
	}

	public View(String name, String query) {
		Assert.noBlanks(name, query);
		this.name = name;
		this.query = query;
	}

	@Override
	public String getName() {
		return name;
	}

	public String getQuery() {
		return query;
	}

}

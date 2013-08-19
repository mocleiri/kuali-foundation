package org.kuali.common.impex.model.adapter;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.kuali.common.impex.model.ImmutableTable;

public class TableAdapter extends XmlAdapter<MutableTable, ImmutableTable> {

	@Override
	public ImmutableTable unmarshal(MutableTable table) {
		return new ImmutableTable(table.getName(), getDescription(table));
	}

	@Override
	public MutableTable marshal(ImmutableTable table) {
		return new MutableTable(table.getName(), getDescription(table));
	}

	protected String getDescription(MutableTable table) {
		return table.getDescription() == null ? ImmutableTable.DEFAULT_DESCRIPTION : table.getDescription();
	}

	protected String getDescription(ImmutableTable table) {
		return ImmutableTable.DEFAULT_DESCRIPTION.equals(table.getDescription()) ? null : table.getDescription();
	}

}

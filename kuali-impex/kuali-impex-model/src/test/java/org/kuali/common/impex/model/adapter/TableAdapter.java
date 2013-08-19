package org.kuali.common.impex.model.adapter;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.kuali.common.impex.model.ImmutableTable;
import org.kuali.common.util.nullify.NullUtils;

public class TableAdapter extends XmlAdapter<MutableTable, ImmutableTable> {

	@Override
	public ImmutableTable unmarshal(MutableTable table) {
		if (NullUtils.isNullOrNone(table.getDescription())) {
			return new ImmutableTable(table.getName());
		} else {
			return new ImmutableTable(table.getName(), table.getDescription());
		}
	}

	@Override
	public MutableTable marshal(ImmutableTable table) {
		if (NullUtils.isNullOrNone(table.getDescription())) {
			return new MutableTable(table.getName());
		} else {
			return new MutableTable(table.getName(), table.getDescription());
		}
	}

}

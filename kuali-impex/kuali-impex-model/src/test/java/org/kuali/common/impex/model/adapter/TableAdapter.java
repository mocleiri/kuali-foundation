package org.kuali.common.impex.model.adapter;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.kuali.common.impex.model.ImmutableTable;
import org.kuali.common.util.nullify.NullUtils;

public class TableAdapter extends XmlAdapter<AdaptedTable, ImmutableTable> {

	@Override
	public ImmutableTable unmarshal(AdaptedTable table) throws Exception {
		if (NullUtils.isNullOrNone(table.getDescription())) {
			return new ImmutableTable(table.getName());
		} else {
			return new ImmutableTable(table.getName(), table.getDescription());
		}
	}

	@Override
	public AdaptedTable marshal(ImmutableTable table) throws Exception {
		if (NullUtils.isNullOrNone(table.getDescription())) {
			return new AdaptedTable(table.getName());
		} else {
			return new AdaptedTable(table.getName(), table.getDescription());
		}
	}

}

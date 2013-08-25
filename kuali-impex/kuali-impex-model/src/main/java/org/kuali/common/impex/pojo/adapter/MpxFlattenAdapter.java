package org.kuali.common.impex.pojo.adapter;

import org.kuali.common.util.xml.jaxb.adapter.FlattenOptionalStringAdapter;

public class MpxFlattenAdapter extends FlattenOptionalStringAdapter {

	public MpxFlattenAdapter() {
		super("${mpx.cr}", "${mpx.lf}");
	}

}

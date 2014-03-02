package org.kuali.common.util.xml.jaxb.adapter;

import org.kuali.common.util.nullify.NullUtils;

public class OmitNullStringAdapter extends OmitStringAdapter {

	public OmitNullStringAdapter() {
		super(NullUtils.NULL);
	}

}

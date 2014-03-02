package org.kuali.common.util.xml.jaxb.adapter;

import org.kuali.common.util.nullify.NullUtils;

public class OmitNoneStringAdapter extends OmitStringAdapter {

	public OmitNoneStringAdapter() {
		super(NullUtils.NONE);
	}

}

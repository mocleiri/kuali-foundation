package org.kuali.common.util.xml.jaxb;

import org.kuali.common.util.nullify.NullUtils;

public class DropNoneStringAdapter extends OmitStringAdapter {

	public DropNoneStringAdapter() {
		super(NullUtils.NONE);
	}

}

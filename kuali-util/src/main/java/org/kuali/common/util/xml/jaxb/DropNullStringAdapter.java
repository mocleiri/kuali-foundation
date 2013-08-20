package org.kuali.common.util.xml.jaxb;

import org.kuali.common.util.nullify.NullUtils;

public class DropNullStringAdapter extends DropStringAdapter {

	public DropNullStringAdapter() {
		super(NullUtils.NULL);
	}

}

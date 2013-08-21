package org.kuali.common.util.log.log4j.jaxb;

import org.kuali.common.util.log.log4j.model.Level;
import org.kuali.common.util.xml.jaxb.DropClassAdapter;

public class DropLevelClassAdapter extends DropClassAdapter {

	public DropLevelClassAdapter() {
		super(Level.DEFAULT_CLASS);
	}

}

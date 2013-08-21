package org.kuali.common.util.log.log4j.jaxb;

import org.kuali.common.util.log.log4j.model.Level;
import org.kuali.common.util.xml.jaxb.DropClassAdapter;

public class OmitDefaultLog4JLevelClassAdapter extends DropClassAdapter {

	public OmitDefaultLog4JLevelClassAdapter() {
		super(Level.DEFAULT_CLASS);
	}

}

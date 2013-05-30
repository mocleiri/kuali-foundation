package org.kuali.common.jdbc.spring;

import org.kuali.common.util.DefaultProjectContext;

public class OleTestProjectContext extends DefaultProjectContext {

	public OleTestProjectContext() {
		super("org.kuali.ole", "olefs-webapp", "classpath:ole-fs.properties");
	}

}

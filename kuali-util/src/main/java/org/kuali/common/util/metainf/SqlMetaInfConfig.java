package org.kuali.common.util.metainf;

import org.kuali.common.util.config.KualiUtilConfigRequest;

public class SqlMetaInfConfig extends KualiUtilConfigRequest {

	public static final String CONTEXT_ID = "metainf:sql";

	public SqlMetaInfConfig() {
		super(CONTEXT_ID);
	}

}

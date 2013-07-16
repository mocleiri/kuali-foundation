package org.kuali.common.util.metainf;

import org.kuali.common.util.config.KualiUtilConfigRequest;

public class RuntimeSqlMetaInfConfig extends KualiUtilConfigRequest {

	public static final String CONTEXT_ID = "metainf:sql:runtime";

	public RuntimeSqlMetaInfConfig() {
		super(CONTEXT_ID);
	}

}

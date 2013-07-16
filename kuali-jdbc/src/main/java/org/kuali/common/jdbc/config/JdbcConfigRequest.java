package org.kuali.common.jdbc.config;

import org.kuali.common.util.KualiProjectConstants;
import org.kuali.common.util.config.KualiCommonConfigRequest;

public class JdbcConfigRequest extends KualiCommonConfigRequest {

	public JdbcConfigRequest() {
		super(KualiProjectConstants.JDBC_ARTIFACT_ID);
	}

}

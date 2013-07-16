package org.kuali.common.jdbc.config;

import java.util.Arrays;
import java.util.List;

import org.kuali.common.util.config.ConfigRequest;

public abstract class JdbcConfigConstants {

	public static final List<? extends ConfigRequest> JDBC_CONFIG = Arrays.asList(new SqlConfigRequest(), new JdbcConfigRequest());

}

package org.kuali.common.jdbc.config;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.kuali.common.util.config.ConfigRequest;
import org.kuali.common.util.config.ConfigUtils;

public abstract class JdbcConfigConstants {

	public static final List<? extends ConfigRequest> CONFIG_REQUESTS = Arrays.asList(new SqlConfigRequest(), new JdbcConfigRequest());
	public static final List<String> CONFIG_IDS = Collections.unmodifiableList(ConfigUtils.getConfigIds(CONFIG_REQUESTS));

}

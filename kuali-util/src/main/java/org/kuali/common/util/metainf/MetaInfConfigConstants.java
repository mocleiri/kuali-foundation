package org.kuali.common.util.metainf;

import java.util.List;

import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.config.KualiUtilConfig;

public class MetaInfConfigConstants {

	public static final List<String> DEFAULT_CONFIG_IDS = CollectionUtils.unmodifiableList(KualiUtilConfig.METAINF_MPX.getConfigId());

}

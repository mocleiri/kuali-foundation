package org.kuali.common.util.property;

import org.kuali.common.util.Mode;
import org.springframework.util.PropertyPlaceholderHelper;

public final class Constants {

	public static final String DEFAULT_PLACEHOLDER_PREFIX = "${";
	public static final String DEFAULT_PLACEHOLDER_SUFFIX = "}";
	public static final String DEFAULT_ENCRYPTED_SUFFIX = ".encrypted";
	public static final String DEFAULT_PATH_SUFFIX = "path";
	public static final String DEFAULT_MAJOR_VERSION_SUFFIX = "major";
	public static final String DEFAULT_MINOR_VERSION_SUFFIX = "minor";
	public static final String DEFAULT_INCREMENTAL_VERSION_SUFFIX = "incremental";
	public static final String DEFAULT_QUALIFIER_VERSION_SUFFIX = "qualifier";
	public static final String DEFAULT_TRIMMED_VERSION_SUFFIX = "trimmed";
	public static final String DEFAULT_SNAPSHOT_VERSION_SUFFIX = "snapshot";
	public static final String DEFAULT_USER_HOME_KEY = "user.home";
	public static final String DEFAULT_CODE_SUFFIX = "code";
	public static final Mode DEFAULT_PROPERTY_OVERWRITE_MODE = Mode.INFORM;
	public static final PropertyPlaceholderHelper DEFAULT_PROPERTY_PLACEHOLDER_HELPER = new PropertyPlaceholderHelper(DEFAULT_PLACEHOLDER_PREFIX, DEFAULT_PLACEHOLDER_SUFFIX);

}

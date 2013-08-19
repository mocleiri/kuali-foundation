package org.kuali.common.util.log.log4j;

import java.io.File;

import org.kuali.common.util.file.CanonicalFile;
import org.kuali.common.util.log.log4j.model.Param;
import org.kuali.common.util.log4j.model.Value;

public class ParamFactory {

	public static final String CONVERSION_PATTERN = "ConversionPattern";
	public static final String FILE = "File";
	public static final String THRESHOLD = "Threshold";

	public static Param getPatternParam(String pattern) {
		return new Param(CONVERSION_PATTERN, pattern);
	}

	public static Param getFileParam(String filename) {
		return new Param(FILE, filename);
	}

	public static Param getFileParam(File file) {
		return getFileParam(new CanonicalFile(file).getPath());
	}

	public static Param getThresholdParam(Value value) {
		return new Param(THRESHOLD, value.name());
	}

}

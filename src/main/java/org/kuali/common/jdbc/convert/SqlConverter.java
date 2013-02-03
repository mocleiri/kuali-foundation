package org.kuali.common.jdbc.convert;

import java.io.File;

public interface SqlConverter {

	ConversionResult convert(File oldFile, File newFile);

}

package org.kuali.common.util.log4j.model.param;

import org.kuali.common.util.log4j.model.Param;

/**
 * @deprecated
 */
@Deprecated
public class FileParam extends Param {

	public static final String NAME = "File";

	public FileParam(String filename) {
		super(NAME, filename);
	}

}

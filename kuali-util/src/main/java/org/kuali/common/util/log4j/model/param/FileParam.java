package org.kuali.common.util.log4j.model.param;


/**
 * @deprecated
 */
@Deprecated
public class FileParam extends org.kuali.common.util.log4j.model.Param {

	public static final String NAME = "File";

	public FileParam(String filename) {
		super(NAME, filename);
	}

}

package org.kuali.common.util.log4j.model.param;

import org.kuali.common.util.log4j.model.Log4JParam;

public class Log4JFileParam extends Log4JParam {

	public static final String NAME = "File";

	public Log4JFileParam(String filename) {
		super(NAME, filename);
	}

}

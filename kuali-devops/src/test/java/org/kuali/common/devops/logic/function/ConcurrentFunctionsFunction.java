package org.kuali.common.devops.logic.function;

import org.kuali.common.devops.logic.HttpCacher;
import org.kuali.common.devops.model.FileCache;

import com.google.common.base.Function;

public class ConcurrentFunctionsFunction implements Function<String, FileCache> {

	@Override
	public FileCache apply(String url) {
		return HttpCacher.refresh(url);
	}

}

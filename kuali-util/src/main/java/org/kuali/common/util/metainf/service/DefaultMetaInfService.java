package org.kuali.common.util.metainf.service;

import java.util.Arrays;
import java.util.List;

import org.kuali.common.util.metainf.model.MetaInfContext;

public class DefaultMetaInfService implements MetaInfService {

	@Override
	public void scanAndCreateFile(MetaInfContext context) {
		scanAndCreateFiles(Arrays.asList(context));
	}

	@Override
	public void scanAndCreateFiles(List<MetaInfContext> contexts) {
		// TODO Auto-generated method stub

	}

}

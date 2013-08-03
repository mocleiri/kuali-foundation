package org.kuali.common.util.metainf.service;

import java.util.List;

import org.kuali.common.util.metainf.model.MetaInfContext;

public interface MetaInfService {

	void scanAndCreateFile(MetaInfContext context);

	void scanAndCreateFiles(List<MetaInfContext> contexts);

}

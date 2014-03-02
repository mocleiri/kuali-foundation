package org.kuali.common.util.metainf.service;

import java.util.List;

import org.kuali.common.util.metainf.model.MetaInfContext;
import org.kuali.common.util.metainf.model.ScanResult;

public interface MetaInfService {

	ScanResult scan(MetaInfContext context);

	List<ScanResult> scan(List<MetaInfContext> contexts);

	void write(List<ScanResult> results);

	void write(ScanResult result);

}

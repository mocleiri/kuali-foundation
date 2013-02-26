package org.kuali.common.impex;

import java.util.List;

import org.kuali.common.impex.service.ImpexContext;
import org.kuali.common.impex.service.ImpexUtils;
import org.kuali.common.util.execute.Executable;

public class CopyFilesExecutable implements Executable {

	List<ImpexContext> contexts;

	@Override
	public void execute() {
		for (ImpexContext context : contexts) {
			ImpexUtils.copyFiles(context);
		}
	}

	public List<ImpexContext> getContexts() {
		return contexts;
	}

	public void setContexts(List<ImpexContext> contexts) {
		this.contexts = contexts;
	}

}

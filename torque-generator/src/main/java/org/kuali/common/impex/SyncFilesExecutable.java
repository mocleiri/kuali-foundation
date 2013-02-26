package org.kuali.common.impex;

import java.util.List;
import java.util.Properties;

import org.kuali.common.impex.service.ImpexContext;
import org.kuali.common.impex.service.ImpexUtils;
import org.kuali.common.util.execute.Executable;

public class SyncFilesExecutable implements Executable {

	List<ImpexContext> contexts;
	Properties properties;

	@Override
	public void execute() {
		for (ImpexContext context : contexts) {
			ImpexUtils.syncFiles(context);
		}
	}

	public List<ImpexContext> getContexts() {
		return contexts;
	}

	public void setContexts(List<ImpexContext> contexts) {
		this.contexts = contexts;
	}

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

}

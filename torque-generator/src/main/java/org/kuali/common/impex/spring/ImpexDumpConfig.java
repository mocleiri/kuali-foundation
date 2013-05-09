package org.kuali.common.impex.spring;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.kuali.common.impex.service.ImpexContext;
import org.kuali.common.util.execute.Executable;
import org.kuali.common.util.execute.ExecutablesExecutable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public abstract class ImpexDumpConfig {

	@Autowired
	Environment env;

	@Autowired
	Properties mavenProperties;

	protected abstract List<ImpexContext> getDumpContexts();

	@Bean
	public ImpexContext impexSourceContext() {
		return DumpUtils.getImpexSourceContext(env, mavenProperties);
	}

	@Bean(initMethod = "execute")
	public Executable dumpAndSyncExecutable() {

		List<ImpexContext> dumpContexts = getDumpContexts();

		List<Executable> executables = new ArrayList<Executable>();
		executables.add(DumpUtils.getDumpExecutable(env, impexSourceContext(), dumpContexts));
		executables.add(DumpUtils.getSyncFilesExecutable(env, dumpContexts));
		return new ExecutablesExecutable(executables);
	}

}

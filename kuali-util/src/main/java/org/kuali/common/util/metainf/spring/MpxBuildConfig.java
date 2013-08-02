package org.kuali.common.util.metainf.spring;

import java.util.Arrays;
import java.util.List;

import org.kuali.common.util.metainf.MetaInfContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ MpxCommonConfig.class })
public class MpxBuildConfig {

	@Autowired
	MpxCommonConfig mpxCommonConfig;

	@Bean
	public List<MetaInfContext> mpxMetaInfContexts() {
		MetaInfContext context = new MetaInfContext();
		context.setIncludes(Arrays.asList("**/*.mpx"));
		return Arrays.asList(context);
	}

}

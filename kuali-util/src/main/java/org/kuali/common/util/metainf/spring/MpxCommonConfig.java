package org.kuali.common.util.metainf.spring;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Configuration;

@Configuration
public class MpxCommonConfig {

	public static final List<String> INCLUDES = Arrays.asList("**/*.mpx");

}

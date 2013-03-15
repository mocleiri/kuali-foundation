package org.kuali.common.util.spring.config;

import java.io.File;

import org.kuali.common.util.execute.Executable;
import org.kuali.common.util.execute.StorePropertiesExecutable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MetaInfProjectPropertiesConfig {

	@Value(value = "${project.build.outputDirectory}/META-INF/${project.orgId.path}/${project.artifactId}.properties")
	File outputFile;

	@Value(value = "${project.encoding}")
	String encoding;

	@Bean(initMethod = "execute")
	public Executable storePropertiesExecutable() {
		StorePropertiesExecutable spe = new StorePropertiesExecutable();
		spe.setEncoding(encoding);
		spe.setOutputFile(outputFile);
		return spe;
	}
}

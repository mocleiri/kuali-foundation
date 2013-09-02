package org.kuali.common.deploy.spring;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.codehaus.plexus.util.StringUtils;
import org.kuali.common.deploy.DeployContext;
import org.kuali.common.deploy.Deployable;
import org.kuali.common.deploy.channel.spring.DefaultSecureChannelConfig;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.maven.model.Artifact;
import org.kuali.common.util.nullify.NullUtils;
import org.kuali.common.util.spring.PropertySourceUtils;
import org.kuali.common.util.spring.env.EnvironmentService;
import org.kuali.common.util.spring.service.SpringServiceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.ConfigurableEnvironment;

@Configuration
@Import({ SpringServiceConfig.class })
public class DefaultDeployContextConfig implements DeployContextConfig {

	@Autowired
	EnvironmentService env;

	@Autowired
	ConfigurableEnvironment configurableEnvironment;

	@Override
	@Bean
	public DeployContext deployContext() {
		return getDeployContext();
	}

	protected List<Deployable> getApplicationConfig() {
		Properties properties = PropertySourceUtils.getAllEnumerableProperties(configurableEnvironment);
		Properties configProperties = PropertyUtils.getProperties(properties, "kdo.config.*.local", null);
		List<String> keys = PropertyUtils.getSortedKeys(configProperties);
		List<Deployable> deployables = new ArrayList<Deployable>();
		for (String key : keys) {
			Deployable deployable = getApplicationConfig(key);
			if (deployable.isExists()) {
				deployables.add(deployable);
			}
		}
		return deployables;
	}

	protected Deployable getApplicationConfig(String localKey) {
		// kdo.config.1.local
		String[] tokens = StringUtils.split(localKey, ".");

		// Should always be 4 tokens
		if (tokens.length != 4) {
			throw new IllegalStateException("Expected 4 tokens [" + localKey + "]");
		}

		// Third token is a unique identifier for this config file
		String identifier = tokens[2];

		// Assemble property keys
		String remoteKey = "kdo.config." + identifier + ".remote";
		String filterKey = "kdo.config." + identifier + ".filter";
		String requiredKey = "kdo.config." + identifier + ".required";

		// Extract information using the property keys
		String local = env.getString(localKey);
		String remote = env.getString(remoteKey);
		boolean filter = env.getBoolean(filterKey, true); // Config files are filtered by default
		boolean required = env.getBoolean(requiredKey, true); // Config files are required by default

		// Create a new deployable from the information we gathered from the environment
		return new Deployable.Builder(local, remote).filter(filter).required(required).build();
	}

	protected Artifact getJdbcDriverArtifact() {
		String groupId = env.getString("jdbc.driver.groupId");
		String artifactId = env.getString("jdbc.driver.artifactId");
		String version = env.getString("jdbc.driver.version");
		return new Artifact.Builder(groupId, artifactId, version).build();
	}

	protected Artifact getApplicationArtifact() {
		// TODO This only works because deploy.groupId is set inside the top level kuali-pom
		// TODO This won't work on process not launched from the Maven CLI
		// TODO Come up with something better here
		String groupId = env.getString("deploy.groupId");
		String artifactId = env.getString("project.artifactId");
		String version = env.getString("project.version");
		String classifier = env.getString("project.classifier", NullUtils.NONE);
		String type = env.getString("project.packaging");
		return new Artifact.Builder(groupId, artifactId, version).classifier(classifier).type(type).build();
	}

	protected DeployContext getDeployContext() {
		String environment = env.getString("deploy.env");
		String hostname = env.getString(DefaultSecureChannelConfig.HOSTNAME_KEY);
		String username = env.getString(DefaultSecureChannelConfig.USERNAME_KEY);
		Artifact jdbcDriver = getJdbcDriverArtifact();
		Artifact application = getApplicationArtifact();
		List<Deployable> configFiles = getApplicationConfig();
		return new DeployContext(environment, username, hostname, application, jdbcDriver, configFiles);
	}

}
package org.kuali.common.deploy.monitoring.spring;

import org.kuali.common.deploy.DeployContext;
import org.kuali.common.deploy.Deployable;
import org.kuali.common.deploy.channel.spring.DefaultSecureChannelConfig;
import org.kuali.common.deploy.monitoring.AppDynamicsMonitoring;
import org.kuali.common.deploy.monitoring.MachineAgent;
import org.kuali.common.deploy.monitoring.Monitoring;
import org.kuali.common.deploy.monitoring.ServerAgent;
import org.kuali.common.deploy.spring.DefaultDeployContextConfig;
import org.kuali.common.util.secure.channel.SecureChannel;
import org.kuali.common.util.spring.PropertySourceUtils;
import org.kuali.common.util.spring.SpringUtils;
import org.kuali.common.util.spring.env.EnvironmentService;
import org.kuali.common.util.spring.service.SpringServiceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.ConfigurableEnvironment;

@Configuration
@Import({ DefaultDeployContextConfig.class, SpringServiceConfig.class, DefaultSecureChannelConfig.class })
public class AppDynamicsConfig implements MonitoringConfig {

	@Autowired
	DeployContext context;

	@Autowired
	EnvironmentService env;

	@Autowired
	ConfigurableEnvironment configurableEnvironment;

	@Autowired
	SecureChannel channel;

	@Override
	@Bean
	public Monitoring applicationMonitoring() {
		boolean enabled = env.getBoolean("monitoring.enabled", false);
		AppDynamicsMonitoring adm = new AppDynamicsMonitoring();
		adm.setUser(env.getString("tomcat.user"));
		adm.setGroup(env.getString("tomcat.group"));
		adm.setChannel(channel);
		adm.setMachineAgent(getMachineAgent());
		adm.setServerAgent(getServerAgent());
		adm.setEnabled(enabled);
		adm.setFilterProperties(PropertySourceUtils.getAllEnumerableProperties(configurableEnvironment));
		return adm;
	}

	protected Deployable getMachineAgentController() {
		String local = env.getString("appdynamics.ma.controller.local");
		String remote = env.getString("appdynamics.ma.controller");
		return new Deployable.Builder(local, remote).filter(true).build();
	}

	protected Deployable getServerAgentController() {
		String local = env.getString("appdynamics.sa.controller.local");
		String remote = env.getString("appdynamics.sa.controller");
		return new Deployable.Builder(local, remote).filter(true).build();
	}

	protected MachineAgent getMachineAgent() {
		MachineAgent agent = new MachineAgent();
		agent.setStartupCommand(env.getString("appdynamics.ma.cmd"));
		agent.setBaseDir(env.getString("appdynamics.ma.base"));
		agent.setTmpDir(env.getString("appdynamics.ma.tmp"));
		agent.setLogsDir(env.getString("appdynamics.ma.logs"));
		agent.setController(getMachineAgentController());
		agent.setLogFile(env.getString("appdynamics.ma.logFile"));
		agent.setStartupToken(env.getString("appdynamics.ma.logFile.startupToken"));
		agent.setStartupTimeoutMillis(new Long(SpringUtils.getMillis(env, "appdynamics.ma.logFile.monitor.timeout", "5m")).intValue());
		agent.setLogFileIntervalMillis(new Long(SpringUtils.getMillis(env, "appdynamics.ma.logFile.monitor.interval", "500ms")).intValue());
		agent.setLogFileEncoding(env.getString("appdynamics.ma.logFile.encoding"));
		return agent;
	}

	protected ServerAgent getServerAgent() {
		String startupOptions = env.getString("appdynamics.sa.tomcat.java.options");
		String baseDir = env.getString("appdynamics.sa.base");
		String logsDir = env.getString("appdynamics.sa.logs");
		Deployable controller = getServerAgentController();
		return new ServerAgent(startupOptions, controller, baseDir, logsDir);
	}

}
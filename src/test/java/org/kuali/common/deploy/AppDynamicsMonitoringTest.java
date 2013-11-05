package org.kuali.common.deploy;

import java.util.Arrays;
import java.util.List;

import org.jasypt.util.text.BasicTextEncryptor;
import org.junit.Test;
import org.kuali.common.deploy.monitoring.AppDynamicsMonitoring;
import org.kuali.common.deploy.monitoring.MachineAgent;

/**
 * @deprecated
 */
@Deprecated
public class AppDynamicsMonitoringTest {

	@Test
	public void test() {
		try {
			org.kuali.common.util.secure.DefaultSecureChannel channel = new org.kuali.common.util.secure.DefaultSecureChannel();
			channel.setUsername("root");
			channel.setHostname("env16.ks.kuali.org");
			channel.setStrictHostKeyChecking(false);
			channel.setUseConfigFile(false);
			channel.setPrivateKeyStrings(getPrivateKeys());

			AppDynamicsMonitoring adm = new AppDynamicsMonitoring();
			adm.setUser("tomcat");
			adm.setGroup("tomcat");
			adm.setMachineAgent(getMachineAgent());
			// adm.setChannel(channel);

			channel.open();
			adm.stop();
			adm.prepare();
			adm.start();
			channel.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected MachineAgent getMachineAgent() {
		MachineAgent agent = new MachineAgent();
		agent.setTmpDir("/usr/local/machine-agent/tmp");
		agent.setLogsDir("/usr/local/machine-agent/logs");
		agent.setStartupCommand("java -jar /usr/local/machine-agent/machineagent.jar");
		return agent;
	}

	protected List<String> getPrivateKeys() {
		BasicTextEncryptor bte = new BasicTextEncryptor();
		bte.setPassword(System.getProperty("kuali.enc.password"));
		String encryptedKey = "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";
		String decryptedKey = bte.decrypt(encryptedKey);
		return Arrays.asList(decryptedKey);
	}
}

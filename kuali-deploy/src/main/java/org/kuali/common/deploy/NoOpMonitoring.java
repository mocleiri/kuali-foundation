package org.kuali.common.deploy;

public class NoOpMonitoring implements Monitoring {

	@Override
	public void stop() {
		// nothing to do
	}

	@Override
	public void prepare() {
		// nothing to do
	}

	@Override
	public void start() {
		// nothing to do
	}

	@Override
	public String getMonitoringJavaOpts() {
		return null;
	}

}

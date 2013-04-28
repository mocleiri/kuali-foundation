package org.kuali.common.deploy;

public interface Monitoring {

	boolean isEnabled();

	void stop();

	void prepare();

	void start();

	String getJavaStartupOptions();

}

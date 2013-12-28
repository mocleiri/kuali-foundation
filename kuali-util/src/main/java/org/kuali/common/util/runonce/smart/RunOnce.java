package org.kuali.common.util.runonce.smart;

public interface RunOnce {

	/**
	 * Return an object indicating whether or not it is ok for something to RunOnce
	 */
	RunOnceIndicator getIndicator();

	/**
	 * Update state in a way that persists across JVM restarts (eg database table, properties file, etc)
	 */
	void changeState(RunOnceState state);

}

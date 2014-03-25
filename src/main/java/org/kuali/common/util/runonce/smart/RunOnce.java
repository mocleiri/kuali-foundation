package org.kuali.common.util.runonce.smart;

public interface RunOnce {

	/**
	 * Do whatever is needed for isTrue() to return the correct value
	 */
	void initialize();

	/**
	 * Indicates it is safe to run something once.
	 */
	boolean isTrue();

	/**
	 * Update state in a way that persists across JVM restarts (eg database table, properties file, etc)
	 */
	void changeState(RunOnceState state);

}

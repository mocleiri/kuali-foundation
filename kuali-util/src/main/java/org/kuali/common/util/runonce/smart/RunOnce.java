package org.kuali.common.util.runonce.smart;

public interface RunOnce {

	/**
	 * Do whatever is needed for getIndicator() to return the correct value
	 */
	void initialize();

	/**
	 * Return a boolean value indicating whether or not it is safe to run something once.
	 */
	boolean isRunOnce();

	/**
	 * Update state in a way that persists across JVM restarts (eg database table, properties file, etc)
	 */
	void changeState(RunOnceState state);

}

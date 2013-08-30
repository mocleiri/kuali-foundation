package org.kuali.common.util.runonce;

public interface RunOnceStateManager {

	/**
	 * Do whatever needs to be done in order for <code>isRunOnce()</code> to return the right value.
	 */
	void initialize();

	/**
	 * Return true if it is safe to run something once, false otherwise.
	 */
	boolean isRunOnce();

	/**
	 * Update the run once state in a way that survives a JVM restart
	 */
	void persistState(RunOnceState state);

}

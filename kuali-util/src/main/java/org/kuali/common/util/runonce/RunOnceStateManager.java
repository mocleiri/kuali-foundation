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
	 * Permanently set the run once state in such a way that it will survive a JVM re-start.
	 */
	void updateState(RunOnceState state);

}

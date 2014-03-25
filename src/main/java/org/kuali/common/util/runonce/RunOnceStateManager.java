package org.kuali.common.util.runonce;

/**
 * @deprecated Use org.kuali.common.util.runonce.smart.RunOnce instead
 */
@Deprecated
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
	 * Update state in a way that persists across JVM restarts (eg database table, properties file, etc)
	 */
	void persistState(RunOnceState state);

}

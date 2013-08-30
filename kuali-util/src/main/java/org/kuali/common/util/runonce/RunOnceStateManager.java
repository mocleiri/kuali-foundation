package org.kuali.common.util.runonce;

public interface RunOnceStateManager {

	void initialize();

	boolean isRunOnce();

	void updateState(RunOnceState state);

}

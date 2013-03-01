package org.kuali.common.deploy;

public class NoOpDatabaseHandler implements DatabaseHandler {

	@Override
	public void reset() {
		// Nothing to do
	}

}

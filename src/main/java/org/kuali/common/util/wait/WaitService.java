package org.kuali.common.util.wait;

import org.kuali.common.util.condition.Condition;

public interface WaitService {

	WaitResult wait(WaitContext context, Condition condition);

}

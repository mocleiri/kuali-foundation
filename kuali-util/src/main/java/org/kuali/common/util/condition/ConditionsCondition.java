package org.kuali.common.util.condition;

import java.util.List;

import org.kuali.common.util.Assert;

public final class ConditionsCondition implements Condition {

	public ConditionsCondition(List<Condition> conditions) {
		Assert.noNulls(conditions);
		this.conditions = conditions;
	}

	private final List<Condition> conditions;

	@Override
	public boolean isTrue() {
		for (Condition condition : conditions) {
			if (!condition.isTrue()) {
				return false;
			}
		}
		return true;
	}

	public List<Condition> getConditions() {
		return conditions;
	}

}

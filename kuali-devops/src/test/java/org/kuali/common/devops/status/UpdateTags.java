package org.kuali.common.devops.status;

import static com.google.common.base.Optional.fromNullable;
import static org.kuali.common.util.base.Exceptions.illegalState;

import org.junit.Test;
import org.kuali.common.devops.logic.Instances;

import com.google.common.base.Optional;

public class UpdateTags {

	private static final String ACCOUNT_KEY = "ec2.account";

	@Test
	public void test() {
		try {
			System.setProperty(ACCOUNT_KEY, "rice");
			Optional<String> account = fromNullable(System.getProperty(ACCOUNT_KEY));
			if (!account.isPresent()) {
				throw illegalState("System property [%s] was not provided.  Usage -D%s=[account]", ACCOUNT_KEY, ACCOUNT_KEY);
			}
			Instances.updateDescriptions(account.get());
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

}

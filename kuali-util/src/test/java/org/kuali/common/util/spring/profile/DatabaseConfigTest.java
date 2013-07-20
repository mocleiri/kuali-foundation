package org.kuali.common.util.spring.profile;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class DatabaseConfigTest {

	@Test
	public void test() {
		try {
			AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
			ctx.getEnvironment().setActiveProfiles(DatabaseConstants.ORACLE_SPRING_PROFILE_NAME);
			ctx.register(ShowDatabaseExecutableConfig.class);
			ctx.refresh();
			ctx.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

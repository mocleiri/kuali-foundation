package org.kuali.common.jdbc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.kuali.common.jdbc.context.DatabaseResetContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class DatabaseServiceTest {

	@Autowired
	DatabaseResetContext context = null;

	@Autowired
	DatabaseService service = null;

	@Test
	public void initializeDatabaseTest() {
		service.reset(context);
	}
}

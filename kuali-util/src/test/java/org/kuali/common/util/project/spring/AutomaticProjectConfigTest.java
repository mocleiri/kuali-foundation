package org.kuali.common.util.project.spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.kuali.common.util.project.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = KualiUtilProjectConfig.class)
public class AutomaticProjectConfigTest {

	@Autowired
	private Project project;

	@Test
	public void test() {
		System.out.println("hello world");
	}

}

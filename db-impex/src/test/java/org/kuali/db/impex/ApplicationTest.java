package org.kuali.db.impex;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationTest {

    @Test
    public void appTest() {
        try {
            new ClassPathXmlApplicationContext("org/kuali/db/impex/test-context.xml");
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}

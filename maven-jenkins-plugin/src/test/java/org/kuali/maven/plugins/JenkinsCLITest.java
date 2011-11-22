package org.kuali.maven.plugins;

import org.junit.Test;

public class JenkinsCLITest {

    @Test
    public void testCLI() {
        try {
            String[] args = { "-s", "http://ec2-107-20-11-86.compute-1.amazonaws.com:8081/", "help" };
            System.out.println("before");
            hudson.cli.CLI.main(args);
            System.out.println("after");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

package org.kuali.maven.mojo;

import hudson.cli.CLI;

import java.io.FileOutputStream;
import java.io.PrintStream;

/**
 * @goal cli
 */
public class JenkinsCLIMojo {

	public static void main(String[] args) {
		String[] argss = { "-s", "http://ci.fn.kuali.org", "get-job", "cm-tools-1.1-publish" };
		try {
			System.setOut(new PrintStream(new FileOutputStream("c:\\temp\\foo.xml")));
			CLI.main(argss);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
}
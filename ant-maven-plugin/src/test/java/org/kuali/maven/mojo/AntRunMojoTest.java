package org.kuali.maven.mojo;

import org.junit.Test;

public class AntRunMojoTest {

	@Test
	public void test() {
		AntRunMojo mojo = new AntRunMojo();
		String xml = mojo.getXML(new AntTaskPojo());
		System.out.println(xml);
	}

}

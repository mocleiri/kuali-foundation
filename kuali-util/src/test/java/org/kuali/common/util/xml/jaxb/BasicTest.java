package org.kuali.common.util.xml.jaxb;

import java.util.Arrays;

import org.springframework.util.Assert;

public class BasicTest {

	public static void main(String[] args) {
		try {
			String os = System.getProperty("os.name") + ", " + System.getProperty("os.version");
			String jdk = System.getProperty("java.vm.name") + ", " + System.getProperty("java.runtime.version");
			System.out.println(os);
			System.out.println(jdk);
			String encoding = "UTF-8";
			JAXBXmlService service = new JAXBXmlService.Builder().useEclipseLinkMoxyProvider(true).build();
			Sport soccer = new Sport("soccer");
			Sport football = new Sport("football");
			University original = new University("Cal Poly", Arrays.asList(soccer, football), Arrays.asList("yellow", "green"));
			String originalXml = service.toXml(original, encoding);
			System.out.println(originalXml);
			University derived = service.getObjectFromXml(originalXml, encoding, University.class);
			String derivedXml = service.toXml(derived, encoding);
			System.out.println(derivedXml);
			Assert.isTrue(originalXml.equals(derivedXml));
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
}

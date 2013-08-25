package org.kuali.common.util.xml.jaxb;

import java.util.Arrays;

public class BasicTest {

	public static void main(String[] args) {
		try {
			String os = System.getProperty("os.name") + ", " + System.getProperty("os.version");
			String jdk = System.getProperty("java.vm.name") + ", " + System.getProperty("java.runtime.version");
			System.out.println(os);
			System.out.println(jdk);
			String encoding = "UTF-8";
			JAXBXmlService service = new JAXBXmlService.Builder().useEclipseLinkMoxyProvider(true).build();
			Sport soccer = new Sport("soccer", "galaxy");
			Sport football = new Sport("football", "raiders");
			Team galaxy = new Team("galaxy", soccer.getName(), "Jeff", "Joe");
			Team raiders = new Team("raiders", football.getName(), "Jeff");
			Student jeff = new Student("Jeff", Arrays.asList(galaxy.getName(), raiders.getName()), soccer.getName(), football.getName());
			Student joe = new Student("Joe", Arrays.asList(galaxy.getName()), soccer.getName());
			University original = new University(Arrays.asList(joe, jeff), Arrays.asList(soccer, football), galaxy, raiders);
			String originalMarshalled = service.toXml(original, encoding);
			System.out.println(originalMarshalled);
			University derived = service.getObjectFromXml(originalMarshalled, encoding, University.class);
			String derivedMarshalled = service.toXml(derived, encoding);
			System.out.println(derivedMarshalled);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
}

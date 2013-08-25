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
			Sport soccer = new Sport("soccer", Arrays.asList("galaxy"));
			Sport football = new Sport("football", Arrays.asList("raiders"));
			Team galaxy = new Team("galaxy", "soccer", "Jeff", "Joe");
			Team raiders = new Team("raiders", "football", "Jeff");
			Student jeff = new Student("Jeff", Arrays.asList(galaxy.getName(), raiders.getName()), Arrays.asList(soccer.getName(), football.getName()));
			Student joe = new Student("Joe", Arrays.asList(galaxy.getName()), Arrays.asList(soccer.getName()));
			University uni = new University(Arrays.asList(joe, jeff), Arrays.asList(soccer, football), Arrays.asList(galaxy, raiders));
			String xml = service.toXml(uni, encoding);
			System.out.println(xml);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
}

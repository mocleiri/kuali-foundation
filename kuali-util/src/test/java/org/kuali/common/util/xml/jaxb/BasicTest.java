package org.kuali.common.util.xml.jaxb;

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
			Club club = new Club("Delta Tau", 15.0);
			Sport soccer = new Sport("Soccer", 125, true, new Team("Manchester United", "blue", "joe", "jeff"));
			Student student1 = new Student.Builder("joe").club(club).sport(soccer).ethnicity("caucasian").build();
			String xml1 = service.toXml(student1, encoding);
			System.out.println(xml1);
			Student derived = service.getObjectFromXml(xml1, encoding, Student.class);
			String xml2 = service.toXml(derived, "UTF-8");
			System.out.println(xml2);
			Assert.isTrue(xml1.equals(xml2), "xml is different");
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

}

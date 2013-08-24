package org.kuali.common.util.xml.jaxb;


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
			Sport soccer = new Sport("Soccer", 125);
			Student student1 = new Student.Builder("joe").club(club).sport(soccer).ethnicity("caucasian").build();
			Student student2 = new Student.Builder("joe").ethnicity("caucasian").build();
			Student student3 = new Student.Builder("joe").build();
			String xml1 = service.toXml(student1, encoding);
			System.out.println(xml1);
			String xml2 = service.toXml(student2, encoding);
			System.out.println(xml2);
			String xml3 = service.toXml(student3, encoding);
			System.out.println(xml3);
			Student derived = service.getObjectFromXml(xml1, encoding, Student.class);
			System.out.println("size=" + derived.getClubs().size());
			derived.getClubs().add(new Club("chess"));
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

}

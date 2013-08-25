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
			Sport sport = new Sport("soccer");
			Student student = new Student("joe",Arrays.asList(sport),null);
			Team team = new Team()
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
}

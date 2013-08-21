package org.kuali.common.util.xml.jaxb;

import java.util.Arrays;
import java.util.List;

import org.kuali.common.util.xml.DefaultXmlService;

public class BasicTest {

	public static void main(String[] args) {
		try {
			String encoding = "UTF-8";
			DefaultXmlService service = new DefaultXmlService();
			List<Club> clubs = Arrays.asList(new Club("soccer"), new Club("archery", 15.0));
			Student student = new Student.Builder("joe").clubs(clubs).build();
			Student student2 = new Student.Builder("joe").build();
			String xml = service.toXml(student, encoding, Club.class);
			System.out.println(xml);
			String xml2 = service.toXml(student2, encoding, Club.class);
			System.out.println(xml2);
			Student derived = service.getObjectFromXml(xml, encoding, Student.class);
			System.out.println("size=" + derived.getClubs().size());
			List<Club> list = derived.getClubs();
			list.add(new Club("bowling"));
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

}

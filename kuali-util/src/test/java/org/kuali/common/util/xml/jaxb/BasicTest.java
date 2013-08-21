package org.kuali.common.util.xml.jaxb;

import java.util.Arrays;
import java.util.List;

import org.kuali.common.util.xml.DefaultXmlService;
import org.kuali.common.util.xml.XmlService;

public class BasicTest {

	public static void main(String[] args) {
		try {
			String encoding = "UTF-8";
			XmlService service = new DefaultXmlService();
			List<Club> clubs = Arrays.asList(new Club("soccer"), new Club("archery"));
			Student student = new Student.Builder("jeff").clubs(clubs).build();
			Student student2 = new Student.Builder("jeff").build();
			String xml = service.toXml(student, encoding);
			System.out.println(xml);
			String xml2 = service.toXml(student2, encoding);
			System.out.println(xml2);
			Student derived = service.getObjectFromXml(xml, encoding, Student.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

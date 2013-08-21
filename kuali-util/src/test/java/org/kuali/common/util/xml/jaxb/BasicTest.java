package org.kuali.common.util.xml.jaxb;

import java.util.Arrays;
import java.util.List;

import org.kuali.common.util.xml.DefaultXmlService;
import org.kuali.common.util.xml.XmlService;

public class BasicTest {

	public static void main(String[] args) {
		try {
			XmlService service = new DefaultXmlService();
			List<Club> clubs = Arrays.asList(new Club("soccer"), new Club("archery"));
			Student student = new Student.Builder("jeff").clubs(clubs).build();
			String xml = service.toXml(student, "UTF-8");
			System.out.println(xml);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

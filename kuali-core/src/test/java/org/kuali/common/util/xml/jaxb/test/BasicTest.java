package org.kuali.common.util.xml.jaxb.test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.kuali.common.util.xml.jaxb.JAXBXmlService;
import org.kuali.common.util.xml.jaxb.Sport;
import org.kuali.common.util.xml.jaxb.University;
import org.kuali.common.util.xml.service.XmlService;

public class BasicTest {
	String encoding = "UTF-8";

	static {
		String os = System.getProperty("os.name") + ", " + System.getProperty("os.version");
		String jdk = System.getProperty("java.vm.name") + ", " + System.getProperty("java.runtime.version");
		System.out.println(os);
		System.out.println(jdk);
	}

	protected XmlService getService() {
		return new JAXBXmlService.Builder().useEclipseLinkMoxyProvider(true).build();
	}

	@Test
	public void testIdenticalRoundTripXML() {
		XmlService service = getService();
		List<Sport> sports = Arrays.asList(new Sport("soccer"), new Sport("football"));
		List<String> colors = Arrays.asList("yellow", "green");
		University original = new University("Cal Poly", sports, colors);
		String originalXml = service.toXml(original, encoding);
		System.out.println(originalXml);
		University derived = service.getObjectFromXml(originalXml, encoding, University.class);
		String derivedXml = service.toXml(derived, encoding);
		System.out.println(derivedXml);
		Assert.assertEquals(originalXml, derivedXml);
	}

	@Test
	public void testImmutability() {
		List<Sport> sports = Arrays.asList(new Sport("soccer"), new Sport("football"));
		List<String> colors = Arrays.asList("yellow", "green");
		University original = new University("Cal Poly", sports, colors);
		try {
			original.getColors().add("blue");
			Assert.fail("color's is mutable");
		} catch (UnsupportedOperationException e) {
			// ignore
		}
		try {
			original.getSports().add(new Sport("tennis"));
			Assert.fail("sports is mutable");
		} catch (UnsupportedOperationException e) {
			// ignore
		}
	}

	@Test
	public void testMutabilityAfterMarshalling() {
		XmlService service = getService();
		List<Sport> sports = Arrays.asList(new Sport("soccer"), new Sport("football"));
		List<String> colors = Arrays.asList("yellow", "green");
		University original = new University("Cal Poly", sports, colors);
		String originalXml = service.toXml(original, encoding);
		University derived = service.getObjectFromXml(originalXml, encoding, University.class);
		try {
			derived.getSports().add(new Sport("tennis"));
			Assert.fail("sports is mutable");
		} catch (UnsupportedOperationException e) {
			// ignore
		}
		try {
			derived.getColors().add("blue");
			Assert.fail("color's is mutable");
		} catch (UnsupportedOperationException e) {
			// ignore
		}
	}

	@Test
	public void testNullValuesAfterMarshalling() {
		XmlService service = getService();
		List<Sport> sports = Collections.emptyList();
		List<String> colors = Collections.emptyList();
		University original = new University("Cal Poly", sports, colors);
		String originalXml = service.toXml(original, encoding);
		System.out.println(originalXml);
		University derived = service.getObjectFromXml(originalXml, encoding, University.class);
		Assert.assertNotNull(derived.getColors());
		Assert.assertNotNull(derived.getSports());
		try {
			derived.getSports().add(new Sport("tennis"));
			Assert.fail("sports is mutable");
		} catch (UnsupportedOperationException e) {
			// ignore
		}
		try {
			derived.getColors().add("blue");
			Assert.fail("color's is mutable");
		} catch (UnsupportedOperationException e) {
			// ignore
		}
	}

}

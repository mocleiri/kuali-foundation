package org.kuali.rice.kim.api.identity.address

import org.junit.Test
import junit.framework.Assert
import org.kuali.rice.kim.api.test.JAXBAssert
import org.kuali.rice.kim.api.identity.Type
import org.kuali.rice.kim.api.identity.TypeContract


class EntityAddressTest {
    private static final String ID = "1";
	private static final String ENTITY_TYPE_CODE = "PERSON";
	private static final String ENTITY_ID = "190192";
    private static final String TYPE_CODE = "Home"
    private static final String TYPE_NAME = "Home-y"
    private static final String TYPE_SORT_CODE = "0"
    private static final String TYPE_ACTIVE = "true"
    private static final Long TYPE_VERSION_NUMBER = new Integer(1)
	private static final String TYPE_OBJECT_ID = UUID.randomUUID()
	private static final String LINE1 = "Line 1";
    private static final String LINE2 = "Line 2";
    private static final String LINE3 = "Line 3";
    private static final String CITY_NAME = "Super Sweet City";
	private static final String STATE_CODE = "CA";
	private static final String POSTAL_CODE = "55555"
    private static final String COUNTRY_CODE = "USA"
    private static final String SUPPRESS = "false"
    private static final String DEFAULT = "true"
    private static final String ACTIVE = "true"
    private static final Long VERSION_NUMBER = new Integer(1);
	private static final String OBJECT_ID = UUID.randomUUID();

    private static final String XML = """
    <entityAddress xmlns="http://rice.kuali.org/kim/v2_0">
        <id>${ID}</id>
        <entityTypeCode>${ENTITY_TYPE_CODE}</entityTypeCode>
        <entityId>${ENTITY_ID}</entityId>
        <addressType>
            <code>${TYPE_CODE}</code>
            <name>${TYPE_NAME}</name>
            <active>${TYPE_ACTIVE}</active>
            <sortCode>${TYPE_SORT_CODE}</sortCode>
            <versionNumber>${TYPE_VERSION_NUMBER}</versionNumber>
            <objectId>${TYPE_OBJECT_ID}</objectId>
        </addressType>
        <line1>${LINE1}</line1>
        <line2>${LINE2}</line2>
        <line3>${LINE3}</line3>
        <cityName>${CITY_NAME}</cityName>
        <stateCode>${STATE_CODE}</stateCode>
        <postalCode>${POSTAL_CODE}</postalCode>
        <countryCode>${COUNTRY_CODE}</countryCode>
        <line1Unmasked>${LINE1}</line1Unmasked>
        <line2Unmasked>${LINE2}</line2Unmasked>
        <line3Unmasked>${LINE3}</line3Unmasked>
        <cityNameUnmasked>${CITY_NAME}</cityNameUnmasked>
        <stateCodeUnmasked>${STATE_CODE}</stateCodeUnmasked>
        <postalCodeUnmasked>${POSTAL_CODE}</postalCodeUnmasked>
        <countryCodeUnmasked>${COUNTRY_CODE}</countryCodeUnmasked>
        <defaultValue>${DEFAULT}</defaultValue>
        <active>${ACTIVE}</active>
        <versionNumber>${VERSION_NUMBER}</versionNumber>
        <objectId>${OBJECT_ID}</objectId>
    </entityAddress>
    """

    @Test(expected=IllegalArgumentException.class)
    void test_Builder_fail_id_whitespace() {
        EntityAddress.Builder builder = EntityAddress.Builder.create();
        builder.setId(" ")
    }

    @Test
    void test_copy() {
        def o1 = EntityAddress.Builder.create().build();
        def o2 = EntityAddress.Builder.create(o1).build();

        Assert.assertEquals(o1, o2);
    }

    @Test
    void happy_path() {
        EntityAddress.Builder.create();
    }

    @Test
	public void test_Xml_Marshal_Unmarshal() {
		JAXBAssert.assertEqualXmlMarshalUnmarshal(this.create(), XML, EntityAddress.class)
	}
    
    private create() {
		return EntityAddress.Builder.create(new EntityAddressContract() {
			def String id = EntityAddressTest.ID
            def String entityTypeCode = EntityAddressTest.ENTITY_TYPE_CODE
            def String entityId = EntityAddressTest.ENTITY_ID
			def Type getAddressType() { Type.Builder.create(new TypeContract() {
				def String code = EntityAddressTest.TYPE_CODE
				def String name = EntityAddressTest.TYPE_NAME
				def boolean active = EntityAddressTest.TYPE_ACTIVE
                def String sortCode = EntityAddressTest.TYPE_SORT_CODE
                def Long versionNumber = EntityAddressTest.TYPE_VERSION_NUMBER
				def String objectId = EntityAddressTest.TYPE_OBJECT_ID
			}).build()
            }
            def String line1 = EntityAddressTest.LINE1
            def String line2 = EntityAddressTest.LINE2
            def String line3 = EntityAddressTest.LINE3
            def String cityName = EntityAddressTest.CITY_NAME
            def String stateCode = EntityAddressTest.STATE_CODE
            def String postalCode = EntityAddressTest.POSTAL_CODE
            def String countryCode = EntityAddressTest.COUNTRY_CODE
            def String line1Unmasked = EntityAddressTest.LINE1
            def String line2Unmasked = EntityAddressTest.LINE2
            def String line3Unmasked = EntityAddressTest.LINE3
            def String cityNameUnmasked = EntityAddressTest.CITY_NAME
            def String stateCodeUnmasked = EntityAddressTest.STATE_CODE
            def String postalCodeUnmasked = EntityAddressTest.POSTAL_CODE
            def String countryCodeUnmasked = EntityAddressTest.COUNTRY_CODE
            def boolean suppressAddress = EntityAddressTest.SUPPRESS.toBoolean()
            def boolean defaultValue = EntityAddressTest.DEFAULT.toBoolean()
            def boolean active = EntityAddressTest.ACTIVE.toBoolean()
            def Long versionNumber = EntityAddressTest.VERSION_NUMBER;
			def String objectId = EntityAddressTest.OBJECT_ID
        }).build()

	}
}

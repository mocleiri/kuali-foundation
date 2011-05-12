@org.hibernate.annotations.TypeDefs ({
	@org.hibernate.annotations.TypeDef(
			name="rice_encrypt_decrypt",
			typeClass=HibernateKualiEncryptDecryptUserType.class
	),
	@org.hibernate.annotations.TypeDef(
			name="rice_active_inactive",
			typeClass=HibernateKualiCharBooleanAIType.class
	),
	@org.hibernate.annotations.TypeDef(
			name="rice_hash",
			typeClass=HibernateKualiHashType.class
	),
	@org.hibernate.annotations.TypeDef(
			name="rice_decimal",
			typeClass=HibernateKualiDecimalFieldType.class
	),
	@org.hibernate.annotations.TypeDef(
			name="rice_decimal_percent",
			typeClass=HibernateKualiDecimalPercentFieldType.class
	),
	@org.hibernate.annotations.TypeDef(
			name="rice_decimal_percentage",
			typeClass=HibernateKualiDecimalPercentageFieldType.class
	),
	@org.hibernate.annotations.TypeDef(
			name="rice_percent",
			typeClass=HibernateKualiPercentFieldType.class
	),
	/*@org.hibernate.annotations.TypeDef(
		name="rice_percentage",
		typeClass=org.kuali.rice.kns.util.HibernateKualiPercentageFieldType.class
	),*/
	@org.hibernate.annotations.TypeDef(
			name="rice_integer",
			typeClass=HibernateKualiIntegerFieldType.class
	),
	@org.hibernate.annotations.TypeDef(
			name="rice_integer_percentage",
			typeClass=HibernateKualiIntegerPercentageFieldType.class
	)
})

package org.kuali.rice.core.framework.persistence.jpa.type;
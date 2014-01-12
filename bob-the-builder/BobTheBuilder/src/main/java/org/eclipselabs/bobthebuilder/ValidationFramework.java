package org.eclipselabs.bobthebuilder;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;
import org.eclipselabs.bobthebuilder.model.Field;

//TODO StringUtils import needs to be added as well
public enum ValidationFramework {

	GOOGLE_GUAVA("com.google.common.base.Preconditions", "Preconditions.checkNotNull", "Preconditions.checkArgument"), //
	COMMONS_LANG3("org.apache.commons.lang3.Validate", "Validate.notNull", "Validate.isTrue"), //
	COMMONS_LANG2("org.apache.commons.lang.Validate", COMMONS_LANG3.checkNotNull, COMMONS_LANG3.checkArgument);

	private static final String checkNotNullTemplateEnding = "%s, \"'%s' cannot be null\");";

	private static final String checkBlankStringTemplateEnding = "!StringUtils.isBlank(%s), \"'%s' cannot be blank\");";

	// private static final String checkNotDefaultTemplateEnding = "(%1$s > %2$s, \"%1$s must be set\");";
	private static final String checkNotDefaultCharEnding = "%s != 0, \"'%s' cannot be the null character\");";

	// private static final String checkNotEmptyCollectionTemplateEnding = "(!%1$s.isEmpty(), \"%1$s cannot be empty\");";

	public String fullClassName;

	private String checkArgument;

	private String checkNotNull;

	private ValidationFramework(String fullClassName, String checkNotNull, String checkArgument) {
		this.fullClassName = fullClassName;
		this.checkArgument = checkArgument;
		this.checkNotNull = checkNotNull;
	}

	public String getFullClassName() {
		return fullClassName;
	}

	public String getCheckArgument() {
		return checkArgument;
	}

	public String getCheckNotNull() {
		return checkNotNull;
	}

	public String getReadableName() {
		return StringUtils.lowerCase(this.name()).replace('_', ' ');
	}

	protected boolean skipValidation(Field field) {
		String signature = field.getSignature();
		if (signature.equals("boolean")) {
			return true;
		}
		if (signature.equals("byte")) {
			return true;
		}
		if (signature.equals("double")) {
			return true;
		}
		if (signature.equals("float")) {
			return true;
		}
		if (signature.equals("long")) {
			return true;
		}
		if (signature.equals("int")) {
			return true;
		}
		if (signature.equals("short")) {
			return true;
		}
		return false;
	}

	public String composeFieldValidation(Field field) {
		Validate.notNull(field, "field may not be null");
		String signature = field.getSignature();
		String fieldName = field.getName();
		String fieldReference = "instance." + fieldName;
		// Don't add a validation line for most primitive types (boolean, byte, double, short, long, float, int
		if (skipValidation(field)) {
			return "";
		} else if (signature.equals("char")) {
			return String.format(checkArgument + checkNotDefaultCharEnding, fieldReference, fieldName);
		} else if (signature.contains("String") || signature.contains("java.lang.String")) {
			return String.format(checkArgument + checkBlankStringTemplateEnding, fieldReference, fieldName);
		} else if (signature.contains("Map") || signature.contains("Set") || signature.contains("List")) {
			return ""; // String.format(checkNotNull + checkNotNullTemplateEnding, fieldName) + " " + String.format(checkArgument + checkNotEmptyCollectionTemplateEnding,
						// fieldName);
		} else {
			return String.format(checkNotNull + checkNotNullTemplateEnding, fieldReference, fieldName);
		}
	}
}

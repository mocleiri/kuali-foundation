package org.eclipselabs.bobthebuilder.model;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.eclipselabs.bobthebuilder.ValidationFramework;

public class ValidateMethod {

  private final Set<FieldAssignment> validatedFields;

  private final String source;

  // This may be null
  private ValidationFramework validationFramework;

  private ValidateMethod(Builder builder) {
    this.source = builder.source;
    this.validationFramework = builder.validationFramework;
    this.validatedFields = builder.validatedFields;
  }

  public static class Builder {

    private Set<FieldAssignment> validatedFields = new HashSet<FieldAssignment>();

    private ValidationFramework validationFramework;

    private String source;

    public Builder withValidatedFields(Set<FieldAssignment> validatedFields) {
      this.validatedFields.addAll(validatedFields);
      return this;
    }

    public Builder withValidationFramework(ValidationFramework validationFramework) {
      this.validationFramework = validationFramework;
      return this;
    }

    public ValidateMethod build() {
      validate();
      return new ValidateMethod(this);
    }

    private void validate() {
      Validate.notNull(validatedFields, "missingFields may not be null");
      Validate.isTrue(!StringUtils.isBlank(source), "source may not be blank");
      Validate.notNull(validatedFields, "validatedFields may not be null");
      Validate.noNullElements(validatedFields, "validatedFields may not contain null elements");
    }

    public Builder withSource(String source) {
      this.source = source;
      return this;
    }

  }

  public Set<FieldAssignment> getValidatedFields() {
    return validatedFields;
  }

  public ValidationFramework getValidationFramework() {
    return validationFramework;
  }

  public String getSource() {
    return source;
  }

  @Override
  public int hashCode() {
    return HashCodeBuilder.reflectionHashCode(this);
  }

  @Override
  public boolean equals(Object obj) {
    return EqualsBuilder.reflectionEquals(this, obj);
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this);
  }

}

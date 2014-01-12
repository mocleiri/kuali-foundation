package org.eclipselabs.bobthebuilder.analyzer;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.Validate;
import org.eclipse.jdt.core.IField;
import org.eclipse.jdt.core.JavaModelException;

public class WithMethodsInBuilderAnalyzer {

  WithMethodsInBuilderAnalyzer() {}

  private void validate(Set<IField> builderFields, Set<IField> missingFieldsInBuilder,
    TypeResult builderTypeAnalyzerResult, Set<IField> extraFieldsInBuilder) {
    Validate.notNull(builderFields, "builder fields may not be null");
    Validate.noNullElements(builderFields, "builder fields contains a null");
    Validate.notNull(missingFieldsInBuilder, "missing fields in builder may not be null");
    Validate.noNullElements(missingFieldsInBuilder, "missing fields in builder contains a null");
    Validate.notNull(builderTypeAnalyzerResult, "builder type analyzer result not be null");
    Validate.notNull(extraFieldsInBuilder, "extra fields in builder may not be null");
    Validate.noNullElements(extraFieldsInBuilder, "extra fields in builder contains a null");
    Validate
        .isTrue(
          (!builderTypeAnalyzerResult.isPresent() && extraFieldsInBuilder.isEmpty()
            && builderFields.isEmpty()) || builderTypeAnalyzerResult.isPresent(),
          "If the builder class is missing, " +
            "then the builder fields and the extra builder fields should be empty");
    Validate.isTrue(
      (builderFields.isEmpty() && extraFieldsInBuilder.isEmpty()) || !builderFields.isEmpty(),
      "If there are no fields in the builder, then there should not be any extra fields");
    Validate.isTrue(builderFields.containsAll(extraFieldsInBuilder),
      "All extra builder fields should be found among the builder fields");
    validateEmptyIntersection(builderFields, missingFieldsInBuilder,
      "builder fields and missing fields in builder");
    validateEmptyIntersection(extraFieldsInBuilder, missingFieldsInBuilder,
      "extra builder fields and missing fields in builder");
  }

  private void validateEmptyIntersection(Set<IField> leftSet,
    Set<IField> rightSet, String description) {
    Set<IField> intersectionMissingAndBuilderFields = new HashSet<IField>();
    intersectionMissingAndBuilderFields.addAll(leftSet);
    intersectionMissingAndBuilderFields.retainAll(rightSet);
    Validate.isTrue(intersectionMissingAndBuilderFields.isEmpty(),
      "There should not be overlap between " + description);
  }

  public Set<IField> analyze(
    Set<IField> builderFields,
    Set<IField> missingFieldsInBuilder,
    Set<IField> extraFieldsInBuilder,
    TypeResult builderTypeAnalyzerResult) throws JavaModelException {
    validate(builderFields, missingFieldsInBuilder, builderTypeAnalyzerResult, extraFieldsInBuilder);
    Set<IField> fields = new HashSet<IField>();
    fields.addAll(builderFields);
    fields.addAll(missingFieldsInBuilder);
    fields.removeAll(extraFieldsInBuilder);
    Set<IField> result = new HashSet<IField>();
    for (IField each : fields) {
      // use ioc instead
      MethodResult analyzed =
          new WithMethodInBuilderAnalyzer().analyze(
            builderTypeAnalyzerResult, each);
      if (!analyzed.isPresent()) {
        result.add(each);
      }
    }
    return Collections.unmodifiableSet(result);
  }
}

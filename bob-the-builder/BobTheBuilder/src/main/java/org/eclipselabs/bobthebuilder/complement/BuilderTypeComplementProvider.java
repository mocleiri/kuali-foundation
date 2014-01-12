package org.eclipselabs.bobthebuilder.complement;

import java.util.Set;

import javax.inject.Inject;

import org.apache.commons.lang.Validate;
import org.eclipselabs.bobthebuilder.model.BuilderTypeComplement;
import org.eclipselabs.bobthebuilder.model.Field;
import org.eclipselabs.bobthebuilder.model.MainType;
import org.eclipselabs.bobthebuilder.model.ValidateMethodComplement;
import org.eclipselabs.bobthebuilder.model.WithMethod;

public class BuilderTypeComplementProvider {

  private final BuilderFieldsComplementProvider builderFieldsComplementProvider;

  private final WithMethodsComplementProvider withMethodsComplementProvider;

  private final BuildMethodComplementProvider buildMethodComplementProvider;

  private final ValidateMethodComplementProvider validateMethodComplementProvider;

  @Inject
  public BuilderTypeComplementProvider(
      BuilderFieldsComplementProvider builderFieldsComplementProvider,
      WithMethodsComplementProvider withersComplementProvider,
      BuildMethodComplementProvider buildMethodComplementProvider,
      ValidateMethodComplementProvider validateMethodComplementProvider) {
    this.builderFieldsComplementProvider = builderFieldsComplementProvider;
    this.withMethodsComplementProvider = withersComplementProvider;
    this.buildMethodComplementProvider = buildMethodComplementProvider;
    this.validateMethodComplementProvider = validateMethodComplementProvider;
  }

  public BuilderTypeComplement complement(MainType mainType) {
    Validate.notNull(mainType, "mainType may not be null");
    BuilderTypeComplement.Builder builderTypeComplementBuilder = new BuilderTypeComplement.Builder();
    if (mainType.getBuilderType() == null) {
      builderTypeComplementBuilder.withCompleteComplement();
    }
    Set<Field> builderFieldsComplement =
        builderFieldsComplementProvider.complement(mainType);
    builderTypeComplementBuilder.withBuilderFieldsComplement(builderFieldsComplement);
    Set<WithMethod> withMethodsComplement = withMethodsComplementProvider.complement(mainType);
    builderTypeComplementBuilder.withWithMethodsComplement(withMethodsComplement);
    BuildMethodComplement buildMethodComplement = buildMethodComplementProvider
        .complement(mainType);
    builderTypeComplementBuilder.withBuildMethodComplement(buildMethodComplement);
    ValidateMethodComplement validateMethodComplement = validateMethodComplementProvider
        .complement(mainType);
    builderTypeComplementBuilder.withValidateMethodComplement(validateMethodComplement);
    return builderTypeComplementBuilder.build();
  }

}

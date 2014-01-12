package org.eclipselabs.bobthebuilder.mapper.eclipse;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.Set;

import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipselabs.bobthebuilder.model.BuildMethod;
import org.eclipselabs.bobthebuilder.model.BuilderType;
import org.eclipselabs.bobthebuilder.model.Field;
import org.eclipselabs.bobthebuilder.model.Imports;
import org.eclipselabs.bobthebuilder.model.ValidateMethod;
import org.eclipselabs.bobthebuilder.model.WithMethod;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.google.common.collect.Sets;

public class BuilderTypeMapperTest {

  @Mock
  private FieldMapper builderFieldsMapper;

  @Mock
  private BuildMethodMapper buildMethodMapper;

  @Mock
  private WithMethodsMapper withMethodsMapper;

  @Mock
  private ValidateMethodMapper validateMethodMapper;

  private BuilderTypeMapper builderTypeMapper;

  @Mock
  private IType type;

  @Mock
  private IType builderType;

  private Set<Field> builderFields;

  @Mock
  private BuildMethod buildMethod;

  private Set<WithMethod> withMethods;

  @Mock
  private ValidateMethod validateMethod;

  @Mock
  private Field field1;

  @Mock
  private Field field2;

  @Mock
  private WithMethod withMethod1;

  @Mock
  private Imports imports;

  @Mock
  private Set<Field> fields;

  @Before
  public void setUp() throws JavaModelException {
    MockitoAnnotations.initMocks(this);
    builderTypeMapper =
        new BuilderTypeMapper(
            builderFieldsMapper, buildMethodMapper, withMethodsMapper, validateMethodMapper);
    Mockito.when(builderType.getElementName()).thenReturn(BuilderTypeMapper.BUILDER_CLASS_NAME);
    Mockito.when(type.getTypes()).thenReturn(new IType[] { builderType });
    builderFields = Sets.newHashSet(field1, field2);
    Mockito.when(builderFieldsMapper.map(builderType)).thenReturn(builderFields);
    Mockito.when(buildMethodMapper.map(builderType)).thenReturn(buildMethod);
    withMethods = Sets.newHashSet(withMethod1);
    Mockito.when(withMethodsMapper.map(builderType)).thenReturn(withMethods);
    Mockito.when(validateMethodMapper.map(builderType, imports, fields)).thenReturn(validateMethod);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullBuilderType() throws JavaModelException {
    builderTypeMapper.map(null, imports, fields);
  }

  @Test
  public void testNoBuilder() throws JavaModelException {
    Mockito.when(type.getTypes()).thenReturn(new IType[] {});
    BuilderType actual = builderTypeMapper.map(type, imports, fields);
    assertNull(actual);
  }

  @Test
  public void testBuilder() throws JavaModelException {
    BuilderType actual = builderTypeMapper.map(type, imports, fields);
    BuilderType expected = new BuilderType.Builder().withBuilderFields(builderFields)
        .withBuildMethod(buildMethod)
        .withValidateMethod(validateMethod).withWithMethods(withMethods).build();
    assertEquals(expected, actual);

  }

}

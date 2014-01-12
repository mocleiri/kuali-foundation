package org.eclipselabs.bobthebuilder.mapper.eclipse;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipselabs.bobthebuilder.analyzer.FieldPredicate;
import org.eclipselabs.bobthebuilder.model.Field;
import org.eclipselabs.bobthebuilder.model.FieldAssignment;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.google.common.collect.Sets;

public class FieldAssignmentInConstructorMapperTest {

  private FieldBasedContentInMethodMapper fieldBasedContentInMethodMapper;

  @Mock
  private IMethod method;

  private Field field1;

  private Field field2;

  @Mock
  private FieldPredicate.FieldAssignment predicate;

  private String field1Name = "field1";

  private String field2Name = "field1";

  private String signature1 = "signature1";

  private String signature2 = "signature2";

  private String source = "source of the method";

  private Set<Field> fields = Sets.newHashSet();

  @Before
  public void setUp() throws JavaModelException {
    MockitoAnnotations.initMocks(this);
    fieldBasedContentInMethodMapper = new FieldBasedContentInMethodMapper();
    field1 = new Field.Builder()
        .withName(field1Name)
        .withSignature(signature1)
        .withPosition(1)
        .build();
    field2 = new Field.Builder()
        .withName(field2Name)
        .withSignature(signature2)
        .withPosition(2)
        .build();
    fields.add(field1);
    fields.add(field2);
    when(method.getSource()).thenReturn(source);
    when(predicate.match(field1Name, source, signature1)).thenReturn(true);
    when(predicate.match(field2Name, source, signature2)).thenReturn(false);
  }

  @Test
  public void testMap() throws JavaModelException {
    Set<FieldAssignment> actual = fieldBasedContentInMethodMapper.map(method, fields, predicate);
    Set<FieldAssignment> expected = Sets.newHashSet(new FieldAssignment(field1));
    assertEquals(expected, actual);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullMethod() throws JavaModelException {
    fieldBasedContentInMethodMapper.map(null, fields, predicate);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullFields() throws JavaModelException {
    fieldBasedContentInMethodMapper.map(method, null, predicate);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullInFields() throws JavaModelException {
    fieldBasedContentInMethodMapper.map(method, Sets.<Field> newHashSet((Field) null), predicate);
  }

  @Test
  public void testEmptyFields() throws JavaModelException {
    Set<FieldAssignment> actual =
        fieldBasedContentInMethodMapper.map(method, new HashSet<Field>(), predicate);
    assertTrue(actual.isEmpty());
  }
}

package org.kuali.rice.krad.service.impl

import org.junit.Test
import org.kuali.rice.core.api.criteria.AndPredicate
import org.kuali.rice.core.api.criteria.EqualIgnoreCasePredicate
import org.kuali.rice.core.api.criteria.EqualPredicate
import org.kuali.rice.core.api.criteria.NullPredicate
import static org.junit.Assert.assertEquals
import static org.junit.Assert.assertTrue
import static org.kuali.rice.krad.service.impl.PredicateFactoryLookup.createPredicate
import static org.kuali.rice.krad.service.impl.PredicateFactoryLookup.getFlagsStr

class PredicateFactoryLookupTest {

    @Test void test_getFlagsStr() {
        //valid
        assertEquals "(?i)", getFlagsStr("(?i)")
        assertEquals "(?im)", getFlagsStr("(?im)")
        assertEquals "(?im)", getFlagsStr("(?im)foo")
        assertEquals "(?f)", getFlagsStr("(?f)(?i)")

        //invalid
        assertEquals "", getFlagsStr("(?)")
        assertEquals "", getFlagsStr("foo(?i)")
    }

    @Test void test_createPredicate_null_value() {
        assertTrue createPredicate(Object.class, "foo", null) instanceof NullPredicate
    }

    @Test void test_createPredicate_string_simple() {
        assertTrue createPredicate(Object.class, "foo", "bar") instanceof EqualPredicate
    }

    @Test void test_createPredicate_string_simple_ignore_case() {
        assertTrue createPredicate(Object.class, "foo", "(?i)bar") instanceof EqualIgnoreCasePredicate
        assertTrue createPredicate(Object.class, "foo", "(?mi)bar") instanceof EqualIgnoreCasePredicate
    }

    @Test void test_createPredicate_collection_string_simple_single() {
        //optimization - will not return an "and" for single item collections
        assertTrue createPredicate(Object.class, "foo", ["bar"]) instanceof EqualPredicate
    }

    @Test void test_createPredicate_collection_string_simple_multi() {
        //this will create a query that will yield zero results but is still a literal translation
        //of what is requested
        def p = createPredicate(Object.class, "foo", ["bar", "baz"])
        assertTrue p instanceof AndPredicate
        def i = p.getPredicates().asList();
        assertEquals i.size(), 2
        assertTrue i[0] instanceof EqualPredicate
        assertTrue i[1] instanceof EqualPredicate
    }


}

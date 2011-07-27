package org.kuali.spring.util;

import org.springframework.util.StringValueResolver;

public class DefaultStringValueResolver implements StringValueResolver {
    PlaceholderStringResolver resolver;
    ValueRetriever retriever;
    String nullValue;

    public DefaultStringValueResolver() {
        this(null, null, null);
    }

    public DefaultStringValueResolver(PlaceholderStringResolver resolver, ValueRetriever retriever, String nullValue) {
        super();
        this.resolver = resolver;
        this.retriever = retriever;
        this.nullValue = nullValue;
    }

    @Override
    public String resolveStringValue(String strVal) {
        String value = resolver.resolve(strVal, retriever);
        return value.equals(nullValue) ? null : value;
    }

    public ValueRetriever getRetriever() {
        return retriever;
    }

    public void setRetriever(ValueRetriever resolver) {
        this.retriever = resolver;
    }

    public String getNullValue() {
        return nullValue;
    }

    public void setNullValue(String nullValue) {
        this.nullValue = nullValue;
    }

    public PlaceholderStringResolver getResolver() {
        return resolver;
    }

    public void setResolver(PlaceholderStringResolver resolver) {
        this.resolver = resolver;
    }

}

package org.kuali.spring.util;

import java.util.List;

public class PlaceholderString {

    String text;
    String resolvedText;
    List<Placeholder> placeholders;

    public PlaceholderString() {
        this(null);
    }

    public PlaceholderString(String text) {
        super();
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getResolvedText() {
        return resolvedText;
    }

    public void setResolvedText(String resolvedText) {
        this.resolvedText = resolvedText;
    }

    public List<Placeholder> getPlaceholders() {
        return placeholders;
    }

    public void setPlaceholders(List<Placeholder> placeholders) {
        this.placeholders = placeholders;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Text: [" + getText() + "]");
        sb.append(" Resolved Text: [" + getResolvedText() + "]");
        sb.append(" Placeholders: [");
        if (getPlaceholders() != null) {
            for (Placeholder placeholder : getPlaceholders()) {
                sb.append(placeholder.toString());
            }
        }
        sb.append("]");
        return sb.toString();
    }
}

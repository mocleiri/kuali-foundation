package org.kuali.spring.util;

public class Placeholder {
    int startIndex;
    int endIndex;
    String value;
    PlaceholderString placeholderString;

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int beginIndex) {
        this.startIndex = beginIndex;
    }

    public int getEndIndex() {
        return endIndex;
    }

    public void setEndIndex(int endIndex) {
        this.endIndex = endIndex;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public PlaceholderString getPlaceholderString() {
        return placeholderString;
    }

    public void setPlaceholderString(PlaceholderString placeholderString) {
        this.placeholderString = placeholderString;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[" + placeholderString.getText() + "]");
        sb.append("=[" + getValue() + "]");
        sb.append(" PlaceholderString: [");
        sb.append(getPlaceholderString().toString());
        sb.append("]");
        return sb.toString();
    }

}

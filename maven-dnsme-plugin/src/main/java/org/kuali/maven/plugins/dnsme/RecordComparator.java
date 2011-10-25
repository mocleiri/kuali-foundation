package org.kuali.maven.plugins.dnsme;

import java.util.Comparator;

import org.apache.commons.lang.StringUtils;
import org.kuali.maven.plugins.dnsme.beans.Record;

public class RecordComparator implements Comparator<Record> {

    @Override
    public int compare(Record record1, Record record2) {
        String name1 = record1.getName();
        String name2 = record2.getName();

        Integer nullCompare = getNullCompare(name1, name2);
        if (nullCompare != null) {
            return nullCompare;
        }

        String[] tokens1 = StringUtils.split(name1, ".");
        String[] tokens2 = StringUtils.split(name2, ".");

        String s1 = getReversedString(tokens1);
        String s2 = getReversedString(tokens2);

        return s1.compareTo(s2);
    }

    protected String getReversedString(String[] tokens) {
        StringBuilder sb = new StringBuilder();
        for (int i = tokens.length - 1; i >= 0; i--) {
            sb.append(tokens[i]);
        }
        return sb.toString();
    }

    protected Integer getNullCompare(String s1, String s2) {
        if (s1 == null && s2 == null) {
            return 0;
        }
        if (s1 != null && s2 == null) {
            return 1;
        }
        if (s1 == null && s2 != null) {
            return -1;
        }
        return null;
    }

}

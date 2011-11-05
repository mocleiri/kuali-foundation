package org.kuali.maven.plugins.ecl.filter;

import java.util.List;

public class MultipleCopyrightFilter extends CheckLinesFileFilter {
    String target = "copyright";

    @Override
    protected boolean checkLines(List<String> lines) {
        int count = 0;
        for (int i = 0; i < lines.size(); i++) {
            String s = lines.get(i);
            String lowerCase = s.toLowerCase();
            int pos = lowerCase.indexOf(target);
            if (pos != -1) {
                count++;
            }
        }
        return count > 1;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

}

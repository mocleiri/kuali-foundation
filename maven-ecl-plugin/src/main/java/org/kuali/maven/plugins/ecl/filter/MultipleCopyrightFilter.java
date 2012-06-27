package org.kuali.maven.plugins.ecl.filter;

import java.util.List;

public class MultipleCopyrightFilter extends CheckLinesFilter {
    String[] targets = { "copyright", "kuali", "foundation" };

    @Override
    protected boolean checkLines(List<String> lines) {
        int count = 0;
        for (int i = 0; i < lines.size(); i++) {
            String s = lines.get(i);
            if (contains(s.toLowerCase(), targets)) {
                count++;
            }
        }
        return count > 1;
    }

    protected boolean contains(String s, String[] targets) {
        for (String target : targets) {
            int pos = s.indexOf(target);
            if (pos == -1) {
                return false;
            }
        }
        return true;
    }

    public String[] getTargets() {
        return targets;
    }

    public void setTargets(String[] targets) {
        this.targets = targets;
    }

}

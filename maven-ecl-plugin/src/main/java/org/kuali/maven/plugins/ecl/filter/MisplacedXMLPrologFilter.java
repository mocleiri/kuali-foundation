package org.kuali.maven.plugins.ecl.filter;

import java.util.List;

public class MisplacedXMLPrologFilter extends CheckLinesFilter {

    String prologStart = "<?xml";
    String prologStop = "?>";

    @Override
    public boolean checkLines(List<String> lines) {
        int prologIndex = getPrologIndex(lines);
        return prologIndex > 0;
    }

    protected boolean isProlog(String line) {
        String lowerCase = line.toLowerCase();
        int pos1 = lowerCase.indexOf(prologStart);
        int pos2 = lowerCase.indexOf(prologStop);
        boolean flag1 = pos1 != -1 && pos2 != -1;
        boolean flag2 = pos2 > pos1;
        return flag1 && flag2;
    }

    protected int getPrologIndex(List<String> lines) {
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            if (isProlog(line)) {
                return i;
            }
        }
        return -1;
    }

    public String getPrologStart() {
        return prologStart;
    }

    public void setPrologStart(String prologStart) {
        this.prologStart = prologStart;
    }

    public String getPrologStop() {
        return prologStop;
    }

    public void setPrologStop(String prologStop) {
        this.prologStop = prologStop;
    }

}

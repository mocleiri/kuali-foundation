package org.kuali.cm.checkstyle;

public class Error implements Comparable<Error> {
    String src;
    String msg;

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public int compareTo(Error o) {
        return src.compareTo(o.getSrc());
    }
}

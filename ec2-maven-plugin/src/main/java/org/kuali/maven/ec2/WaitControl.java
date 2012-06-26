package org.kuali.maven.ec2;

public class WaitControl {
    boolean wait;
    int timeout;
    int sleep = Constants.DEFAULT_SLEEP_MILLIS;
    String state;

    public WaitControl() {
        this(false, 0, null);
    }

    public WaitControl(boolean wait, int timeout, String state) {
        super();
        this.wait = wait;
        this.timeout = timeout;
        this.state = state;
    }

    public boolean isWait() {
        return wait;
    }

    public void setWait(boolean wait) {
        this.wait = wait;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getSleep() {
        return sleep;
    }

    public void setSleep(int sleep) {
        this.sleep = sleep;
    }

}

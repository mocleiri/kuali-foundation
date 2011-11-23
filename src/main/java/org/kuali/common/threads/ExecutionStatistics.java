package org.kuali.common.threads;

public class ExecutionStatistics {
    int iterationCount;
    long executionTime;
    long threadCount;
    public long getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(long millis) {
        this.executionTime = millis;
    }

    public long getThreadCount() {
        return threadCount;
    }

    public void setThreadCount(long count) {
        this.threadCount = count;
    }

    public int getIterationCount() {
        return iterationCount;
    }

    public void setIterationCount(int iterationCount) {
        this.iterationCount = iterationCount;
    }
}

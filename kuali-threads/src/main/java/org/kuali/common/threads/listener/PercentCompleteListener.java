package org.kuali.common.threads.listener;

/**
 * Print a dot whenever there is progress of 2% or more
 */
public class PercentCompleteListener<T> extends ConsoleListener<T> {
    int percentageIncrement = 2;
    int percentCompletePrevious;

    @Override
    public void progressOccurred(int count, int total, ProgressEvent<T> event) {
        int percentComplete = (count * 100) / total;
        if (enoughProgress(percentComplete)) {
            percentCompletePrevious = percentComplete;
            out.print(progressToken);
        }
    }

    protected boolean enoughProgress(int percentComplete) {
        int needed = percentCompletePrevious + percentageIncrement;
        return percentComplete >= needed;
    }

    public int getPercentageIncrement() {
        return percentageIncrement;
    }

    public void setPercentageIncrement(int percentageIncrement) {
        this.percentageIncrement = percentageIncrement;
    }

}

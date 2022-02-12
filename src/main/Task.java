package main;

public class Task {

    private long numToBeFactored;

    public Task(long numToBeFactored) {
        this.numToBeFactored = numToBeFactored;
    }

    // start with thread num 0
    public long[] getParams(int threadNumber, int numberThreads) {

        float threadNum = threadNumber;
        float numThreads = numberThreads;

        long[] longs = new long[3];

        longs[0]  = numToBeFactored;

        if (numThreads == 1) {
            longs[1] = -1;
            longs[2] = -1;
        } else {
            longs[1] = (long) ((threadNum / numThreads) * numToBeFactored);
            longs[2] = (long) (((threadNum + 1) / numThreads) * numToBeFactored);
        }

        return longs;
    }
}

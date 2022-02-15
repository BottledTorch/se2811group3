/*
 * Course:     SE 2811
 * Term:       Winter 2020-21
 * Assignment: Final Presentation
 * Author:     Mitchell Mahnke, Alex Moran
 * Date:       2/10/22
 */


package main;

/**
 * This is a simple task, which can
 * properly divide itself for parallelization
 */
public class Task {

    private long numToBeFactored;

    /**
     * Default constructor, storing the
     * number to be factored
     * @param numToBeFactored - long
     */
    public Task(long numToBeFactored) {
        this.numToBeFactored = numToBeFactored;
    }

    /**
     * Based on the threadNum and total thread count,
     * this method will divide itself into a proper
     * chunk for parallelism.
     * @param threadNumber - current thread num
     * @param numberThreads - total threads
     * @return - params for FactoringService
     */
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

package main;

import java.util.ArrayList;
import java.util.Collection;

public class Factorer {

    public static Collection<Long> factor(long numToFactor) {
        return factor(numToFactor, 1);
    }

    public static Collection<Long> factor(long numToFactor, long start) {
        return factor(numToFactor, start, numToFactor);
    }

    /**
     * This is the method to factor
     * @param numToFactor - the number to be factored
     * @param start - the number to start with (inclusive)
     * @param end  - the number to end with (inclusive)
     * @return Collection containing all of the factors
     */
    public static Collection<Long> factor(long numToFactor, long start, long end) {
        ArrayList<Long> factors = new ArrayList<>();

        for(long i = start; i <= numToFactor && i <= end; i++) {
            if (i == 0) {
                i++;
            }
            if (numToFactor % i == 0) {
                factors.add(i);
            }
        }
        return factors;
    }

    public static void main(String[] args) {
        Factorer factorer = new Factorer();
        System.out.println(factorer.factor(10000, 0, 5000));

    }
}

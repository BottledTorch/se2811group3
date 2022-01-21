package main;

import java.util.ArrayList;
import java.util.Collection;

public class Factorer {

    public static Collection<Double> factor(double numToFactor) {
        return factor(numToFactor, 1);
    }

    public static Collection<Double> factor(double numToFactor, double start) {
        return factor(numToFactor, start, numToFactor);
    }

    /**
     * This is the method to factor
     * @param numToFactor - the number to be factored
     * @param start - the number to start with (inclusive)
     * @param end  - the number to end with (inclusive)
     * @return Collection containing all of the factors
     */
    public static Collection<Double> factor(double numToFactor, double start, double end) {
        ArrayList<Double> factors = new ArrayList<>();

        for(double i = start; i <= numToFactor && i <= end; i++) {
            if (numToFactor % i == 0) {
                factors.add(i);
            }
        }
        return factors;
    }

    public static void main(String[] args) {
        Factorer factorer = new Factorer();
        System.out.println(factorer.factor(1028));
        System.out.print(factorer.factor(1028, 1, 64) + " : ");
        System.out.println(factorer.factor(1028, 65));
    }
}

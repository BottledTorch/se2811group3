package main;

import java.util.ArrayList;

public class Class {

    public ArrayList<Integer> factor(int numToFactor) {
        ArrayList<Integer> factors = new ArrayList<>();

        for(int i = 1; i < numToFactor; i++) {
            if (numToFactor % i == 0) {
                factors.add(i);
            }
        }
        return factors;
    }
}

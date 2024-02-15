package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Simulation of one dimensional simple random walk
 *
 * @author tadaki
 */
public class Simulation {

    private final List<Walker> walkers;//list of walkers
    private double p;//probability of moving right
    private int n;//the number of walkers
    private final Random random;
    protected int t=0;
    /**
     * @param n the number of walkers
     * @param seed
     */
    public Simulation(int n,long seed) {
        this(n, 0.5,seed);
    }

    /**
     * @param n the number of walkers
     * @param p probability of moving right
     * @param seed
     */
    public Simulation(int n, double p,long seed) {
        walkers = Collections.synchronizedList(new ArrayList<>());
        this.p = p;
        this.n = n;
        //initializing walkers
        this.random = new Random(seed);
        for (int i = 0; i < n; i++) {
            walkers.add(new Walker(0, p,random));
        }
    }

    /**
     * Clear all walkers and create new list
     */
    public void initialize() {
        walkers.clear();
        for (int i = 0; i < n; i++) {
            walkers.add(new Walker(0, p,random));
        }
        t = 0;
    }

    /**
     * update one step
     */
    public void oneStep() {
        List<Integer> pList = Collections.synchronizedList(new ArrayList<>());
        //update all walkers and store positions of new walker's positions
        walkers.stream().map(w -> w.walk()).forEachOrdered(x -> pList.add(x));
        t += 1;
    }

    public List<Walker> getWalkers() {
        return walkers;
    }

    public void setP(double p) {
        this.p = p;
    }

    public void setN(int n) {
        this.n = n;
    }

}

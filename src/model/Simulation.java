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
    private final Random random;//random number generator
    protected int t = 0;//time step
    
    /**
     * Constructor
     * 
     * @param n the number of walkers
     * @param random random number generator
     */
    public Simulation(int n,Random random) {
        this(n, 0.5,random);
    }

    /**
     * Constructor
     * 
     * @param n the number of walkers
     * @param p probability of moving right
     * @param random random number generator
     */
    public Simulation(int n, double p,Random random) {
        this.p = p;
        this.n = n;
        //initialize random number generator
        this.random = random;
        //initializing walkers
        walkers = Collections.synchronizedList(new ArrayList<>());
        for (int i = 0; i < n; i++) {
            walkers.add(new Walker(0, p,random));
        }
    }

    /**
     * Clear all walkers and generate new walkers
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
        walkers.forEach(w->w.walk());
        t += 1;
    }

    //*** setters and getters ****
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

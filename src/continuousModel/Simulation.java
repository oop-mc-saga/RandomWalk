package continuousModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import randomNumbers.*;

/**
 * Simulation of one dimensional continuous random walk
 *
 * @author tadaki
 */
public class Simulation {

    private final List<Walker> walkers;//list of walkers
    private int n;//the number of walkers
    private final AbstractRandom random;//random number generator
    protected int t = 0;//time step
    
      /**
     * Constructor
     * 
     * @param n the number of walkers
     * @param random random number generator
     */
    public Simulation(int n, AbstractRandom random) {
        this.n = n;
        //initialize random number generator
        this.random =random;
        //initializing walkers
        walkers = Collections.synchronizedList(new ArrayList<>());
        for (int i = 0; i < n; i++) {
            walkers.add(new Walker(0, random));
        }
    }

    /**
     * Clear all walkers and generate new walkers
     */
    public void initialize() {
        walkers.clear();
        for (int i = 0; i < n; i++) {
            walkers.add(new Walker(0, random));
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

    public void setN(int n) {
        this.n = n;
    }

}

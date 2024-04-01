package continuousModel;

import randomNumbers.*;

/**
 * Continuous Walker class
 *
 * @author tadaki
 */
public class Walker {

    protected double x;//position of the walker
    protected final AbstractRandom random;//random number generator


    /**
     * Constructor
     * 
     * @param x initial position
     * @param random random number generator
     */
    public Walker(double x, AbstractRandom random) {
        this.random = random;
        this.x = x;
    }
    
    public Walker(AbstractRandom random) {
        this(0.,random);
    }

    /**
     * one step of random walk
     *
     * @return new position
     */
    public double walk() {
        double r = random.getNext();
        x += r;
        return x;
    }

    /**
     * return current position
     *
     * @return
     */
    public double getX() {
        return x;
    }
}

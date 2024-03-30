package model;

import java.util.Random;

/**
 * Walker class
 *
 * @author tadaki
 */
public class Walker {

    protected int x;//position of the walker
    protected final double p;//probability for moving right
    protected final Random random;//random number generator

    /**
     * Constructor
     * 
     * @param x initial position
     * @param random random number generator
     */
    public Walker(int x, Random random) {
        this(x, 0.5, random);//probability for moving right is .5
    }

    /**
     * Constructor
     * 
     * @param x initial position
     * @param p probability for moving right
     * @param random random number generator
     */
    public Walker(int x, double p, Random random) {
        this.random = random;
        this.x = x;
        this.p = p;
    }

    /**
     * one step of random walk
     *
     * @return new position
     */
    public int walk() {
        double r = random.nextDouble();
        if (r < p) {//x++ with probability p
            x++;
        } else {//x-- with probability 1-p
            x--;
        }
        return x;
    }

    /**
     * return current position
     *
     * @return
     */
    public int getX() {
        return x;
    }
}

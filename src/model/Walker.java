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
    protected final Random random;

    /**
     * @param x initial position
     * @param random
     */
    public Walker(int x, Random random) {
        this(x, 0.5, random);//probability for moving right is .5
    }

    /**
     * @param x initial position
     * @param p probability for moving right
     * @param random
     */
    public Walker(int x, double p, Random random) {
        this.random = random;
        this.x = x;
        this.p = p;
    }

    /**
     * one step
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

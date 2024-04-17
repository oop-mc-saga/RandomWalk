package continuousModel;

import java.awt.geom.Point2D;
import java.io.PrintStream;
import java.io.IOException;
import java.util.List;

import histogram.Histogram;
import randomNumbers.*;

/**
 * Main class for simulation of one dimensional simple random walk
 * 
 * @author tadaki
 */
public class Main {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        int n = 100000;//The number of walkers
        int tMax = 1000;//The number of steps
        long seed = 48L;//seed for random number generator
        AbstractRandom random = new Uniform(-1./2, 1./2, seed);
        Simulation sys = new Simulation(n, random);//create a new simulation
        for (int t = 0; t < tMax; t++) {//update the system tMax times
            sys.oneStep();
        }
        //find the range of x
        double min = Double.POSITIVE_INFINITY;
        double max = Double.NEGATIVE_INFINITY;
        for (Walker walker : sys.getWalkers()) {
            double x = walker.getX();
            if (x < min) {
                min = x;
            }
            if (x > max) {
                max = x;
            }
        }
        double w = 1.;   
        //Creating histogram
        Histogram histogram = new Histogram(
            Histogram.discretize(min, w), Histogram.discretize(max, w), w);
        for (Walker walker : sys.getWalkers()) {
            histogram.put(walker.getX());
        }
        List<Point2D.Double>pList =  histogram.calculateFrequency();
        String filename = "Uniform" 
                + "-output-" + String.valueOf(tMax) + ".txt";
        //output the histogram to a file
        try ( PrintStream out = new PrintStream(filename)) {
            pList.forEach(p -> out.println(p.x + " " + p.y));
        }
    }

}

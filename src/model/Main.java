package model;

import java.util.Random;
import java.awt.geom.Point2D;
import java.io.PrintStream;
import java.io.IOException;
import java.util.List;

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
        Random random = new Random(seed);//random number generator
        
        Simulation sys = new Simulation(n, random);//create a new simulation
        for (int t = 0; t < tMax; t++) {//update the system tMax times
            sys.oneStep();
        }
        //Creating histogram
        List<Point2D.Double> plist
                = PositionHistogram.getHist(sys.getWalkers());
        String filename = "Histogram" 
                + "-output-" + String.valueOf(tMax) + ".txt";
        //output the histogram to a file
        try ( PrintStream out = new PrintStream(filename)) {
            plist.forEach(p -> out.println(p.x + " " + p.y));
        }
    }

}

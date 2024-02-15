package model;

import java.awt.geom.Point2D;
import java.io.PrintStream;
import java.io.IOException;
import java.util.List;

/**
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
        int tmax = 1000;//The number of steps
        long seed = 48L;
        Simulation sys = new Simulation(n,seed);
        for (int t = 0; t < tmax; t++) {
            sys.oneStep();
        }
        //Creating histogram
        List<Point2D.Double> plist
                = PositionHistogram.getHist(sys.getWalkers());
        String filename = "Fundamental" 
                + "-output-" + String.valueOf(n) + ".txt";
        try ( PrintStream out = new PrintStream(filename)) {
            plist.forEach(p -> out.println(p.x + " " + p.y));
        }
    }

}

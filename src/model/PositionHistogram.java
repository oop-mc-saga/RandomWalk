package model;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Histogram of positions of random walkers
 *
 * @author tadaki
 */
public class PositionHistogram {

    /**
     * The instance of this class can not be created
     */
    private PositionHistogram() {
    }

    /**
     * Getting histogram of positions
     *
     * @param walkers target walkers
     * @return
     */
    public static List<Point2D.Double> getHist(List<Walker> walkers) {
        //observing the range
        Point p = getMinMax(walkers);
        return getHist(walkers, p.x,p.y);
    }

    /**
     * Getting histogram of positions
     *
     * @param walkers target walkers
     * @param xMin minimum value of x
     * @param xMax maximum value of x
     * @return histogram
     */
    public static List<Point2D.Double> getHist(List<Walker> walkers, 
            int xMin, int xMax) {

        //Creating an array for the histogram
        int h[] = new int[xMax - xMin + 1];
        for (Walker w : walkers) {
            int k = w.getX() - xMin;
            h[k]++;
        }
        //converting histogram array into list
        List<Point2D.Double> list
                = Collections.synchronizedList(new ArrayList<>());
        for (int i = 0; i < h.length; i += 2) {
            double x = i + xMin;//position
            //Noticing the bin width is 2
            double y = (double) h[i] / walkers.size() / 2.;
            list.add(new Point2D.Double(x, y));
        }
        return list;
    }

    public static Point getMinMax(List<Walker> walkers) {
        int xMax = walkers.get(0).getX();
        int xMin = xMax;
        for (Walker w : walkers) {
            xMax = Math.max(xMax, w.getX());
            xMin = Math.min(xMin, w.getX());
        }
        return new Point(xMin, xMax);
    }
}

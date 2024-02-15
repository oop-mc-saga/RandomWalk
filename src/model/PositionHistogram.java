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
     * @param xmin
     * @param xmax
     * @return
     */
    public static List<Point2D.Double> getHist(List<Walker> walkers, 
            int xmin, int xmax) {

        //Creating the histogram
        int h[] = new int[xmax - xmin + 1];
        for (Walker w : walkers) {
            int k = w.getX() - xmin;
            h[k]++;
        }
        //converting histogram into list
        List<Point2D.Double> list
                = Collections.synchronizedList(new ArrayList<>());
        for (int i = 0; i < h.length; i += 2) {
            double x = i + xmin;
            //Noticing the bin width is 2
            double y = (double) h[i] / walkers.size() / 2.;
            list.add(new Point2D.Double(x, y));
        }
        return list;
    }

    public static Point getMinMax(List<Walker> walkers) {
        int xmax = walkers.get(0).getX();
        int xmin = xmax;
        for (Walker w : walkers) {
            xmax = Math.max(xmax, w.getX());
            xmin = Math.min(xmin, w.getX());
        }
        return new Point(xmin, xmax);
    }
}

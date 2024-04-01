package randomNumbers.samples;

import java.io.IOException;
import java.util.function.DoubleFunction;
import randomNumbers.AbstractRandom;
import randomNumbers.Rejection;

/**
 * sin(x)^2
 *
 * @author tadaki
 */
public class SqrCos extends AbstractSample {

    public SqrCos(AbstractRandom aRandom, double min,
            double max, int numBin, int numSamples) {
        super(aRandom, min, max, numBin, numSamples);
    }

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {

        DoubleFunction<Double> probDensity = (x) -> {
            return Math.pow(Math.cos(x),2.)*(2/Math.PI);
        };
        double min = -Math.PI/2;
        double max = Math.PI/2;
        double maxFunction = 2/Math.PI;

        AbstractRandom aRandom = new Rejection(
                probDensity,min,max,maxFunction);

        int numBin = 100;
        int numSamples = 100000;

        SqrCos exp = new SqrCos(aRandom, min, max, numBin, numSamples);
        String filename = SqrCos.class.getSimpleName() + "-output.txt";
        exp.outputHistogram(filename);
    }

}

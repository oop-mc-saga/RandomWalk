package modelExt;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import model.Simulation;

/**
 * Extended simulation class with a thread
 *
 * @author tadaki
 */
public class SimulationExt extends Simulation implements Runnable {

    private volatile boolean running = false;
    private final List<ChangeListener> listeners;

    public SimulationExt(int n, Random random) {
        super(n, random);
        listeners = new ArrayList<>();
    }

    public SimulationExt(int n, double p, Random random) {
        super(n, p, random);
        listeners = new ArrayList<>();
    }

    @Override
    public void run() {
        while (running) {
            this.oneStep();
            //Every 10 steps, notify the listeners
            if (t % 10 == 0) {
                fireStateChanged();
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
            }
        }
    }
    
    private void fireStateChanged() {
        listeners.forEach(
                li -> li.stateChanged(new ChangeEvent(this)));
    }

    /**
     * Add a change listener
     *
     * @param li listener
     */
    public void addChangeListenr(ChangeListener li) {
        listeners.add(li);
    }

    /**
     * Remove a change listener
     *
     * @param li listener
     */
    public void removeChangeListener(ChangeListener li) {
        if (listeners.contains(li)) {
            listeners.remove(li);
        }
    }

    public void start() {
        running = true;
    }

    public void stop() {
        running = false;
    }
}

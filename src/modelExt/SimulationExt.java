package modelExt;

import java.util.ArrayList;
import java.util.List;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import model.Simulation;

/**
 *
 * @author tadaki
 */
public class SimulationExt extends Simulation implements Runnable {

    private volatile boolean running = false;
    private final List<ChangeListener> listeners;

    public SimulationExt(int n, long seed) {
        super(n, seed);
        listeners = new ArrayList<>();
    }

    public SimulationExt(int n, double p, long seed) {
        super(n, p, seed);
        listeners = new ArrayList<>();
    }

    @Override
    public void run() {
        while (running) {
            this.oneStep();
            if (t % 10 == 0) {
                listeners.forEach(
                        li -> li.stateChanged(new ChangeEvent(this)));
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
            }
        }
    }

    public void addChangeListenr(ChangeListener li) {
        listeners.add(li);
    }

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

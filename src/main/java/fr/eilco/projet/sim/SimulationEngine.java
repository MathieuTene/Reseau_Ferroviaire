package fr.eilco.projet.sim;

import fr.eilco.projet.model.Reseau;
import fr.eilco.projet.model.Train;

public class SimulationEngine {
    private Reseau reseau;
    private boolean paused = false;

    public SimulationEngine(Reseau reseau) {
        this.reseau = reseau;
    }

    public void step(double dt) {
        if (paused) return;
        for (Train train : reseau.getTrains()) {
            train.avancer(dt);
        }
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }

    public boolean isPaused() {
        return paused;
    }
}

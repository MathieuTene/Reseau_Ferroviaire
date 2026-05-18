package fr.eilco.projet.sim;

import fr.eilco.projet.model.Reseau;
import fr.eilco.projet.model.Train;

public class SimulationEngine {
    private Reseau reseau;

    public SimulationEngine(Reseau reseau) {
        this.reseau = reseau;
    }

    public void step(double dt) {
        for (Train train : reseau.getTrains()) {
            train.avancer(dt);
        }
    }
}

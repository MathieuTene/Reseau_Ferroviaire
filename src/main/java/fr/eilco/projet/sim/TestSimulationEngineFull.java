package fr.eilco.projet.sim;

import fr.eilco.projet.model.*;
import java.util.List;

public class TestSimulationEngineFull {
    public static void main(String [] args){
        Reseau reseau = new Reseau();

        Gare a = new Gare("A", "Gare A", 100, 300);
        Gare b = new Gare("B", "Gare B", 250, 180);
        Gare c = new Gare("C", "Gare C", 650, 420);

        reseau.ajouterNoeud(a);
        reseau.ajouterNoeud(b);
        reseau.ajouterNoeud(c);

        Segment s1 = new Segment("S1", a, b, 100.0);
        Segment s2 = new Segment("S2", b, c, 100.0);

        reseau.ajouterSegment(s1);
        reseau.ajouterSegment(s2);

        Itineraire itineraire = new Itineraire(List.of(s1, s2));
        SimulationEngine simulationEngine = new SimulationEngine(reseau);

        Train train1 = new Train("t1", 90.0, itineraire);
        reseau.ajouterTrain(train1);

        System.out.println(reseau);
        
        for(int i=0; i<3; i++) {
            simulationEngine.step(1.0);
            System.out.println("Step " + (i+1) + " : " + train1);
        }

        System.out.println("\n--- Test avec plusieurs trains (collision évitée) ---");
        // Train 2 sur le même itinéraire, vitesse plus grande
        Train train2 = new Train("t2", 120.0, itineraire);
        reseau.ajouterTrain(train2);
        
        for(int i=0; i<5; i++) {
            simulationEngine.step(1.0);
            System.out.println("Step " + (i+4) + " : " + train1);
            System.out.println("Step " + (i+4) + " : " + train2);
        }
    }
}

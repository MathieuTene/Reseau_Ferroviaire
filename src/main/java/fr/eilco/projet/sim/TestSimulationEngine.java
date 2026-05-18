package fr.eilco.projet.sim;

import fr.eilco.projet.model.*;
import java.util.List;

public class TestSimulationEngine {
    public static void main(String[] args) {

        // ##### F. Test de robustesse du système #####

        // ***** Test 1 : Déplacement simple sur un segments *****

        // Gare a = new Gare("A", "Gare A", 0, 0);
        // Gare b = new Gare("B", "Gare B", 100, 0);
        // Segment s = new Segment("S1", a, b, 100);

        // Itineraire itineraire = new Itineraire(List.of(s));
        // Train train = new Train("t1", 50, itineraire);

        // System.out.println(train);
        // train.avancer(1.0);
        // System.out.println(train);



        // ***** Test 2 : Passage au segment suivant *****

        // Gare a = new Gare("A", "Gare A", 0, 0);
        // Gare b = new Gare("B", "Gare B", 100, 0);
        // Gare c = new Gare("C", "Gare C", 200, 0);

        // Segment s1 = new Segment("S1", a, b, 100);
        // Segment s2 = new Segment("S2", b, c, 100);

        // Itineraire itineraire = new Itineraire(List.of(s1, s2));
        // Train train = new Train("t1", 100, itineraire);

        // System.out.println(train);
        // train.avancer(1.5);
        // System.out.println(train);


        // ***** Test 3 & 4 : dt grand et plusieurs dt *****

        // Gare a = new Gare("A", "Gare A", 0, 0);
        // Gare b = new Gare("B", "Gare B", 200, 0);

        // Segment s1 = new Segment("S1", a, b, 100);

        // Itineraire itineraire = new Itineraire(List.of(s1));
        // Train train = new Train("t1", 100, itineraire);
        // System.out.println(train);
        // train.avancer(10);
        // train.avancer(10);
        // train.avancer(10);
        // System.out.println(train.getSegmentCourant() + " | " + train.estTermine());



        // ##### G. Test d’intégration avec SimulationEngine #####
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

        Train train = new Train("t1", 90.0, itineraire);
        reseau.ajouterTrain(train);

        System.out.println(reseau);

        simulationEngine.step(1.0);
        System.out.println(train);
        simulationEngine.step(1.0);
        System.out.println(train);
        simulationEngine.step(1.0);
        System.out.println(train);
        simulationEngine.step(1.0);
        System.out.println(train);
        
        System.out.println("\nTest de plusieurs trains :");
        Train train2 = new Train("t2", 100.0, new Itineraire(List.of(s2, s1)));
        reseau.ajouterTrain(train2);
        
        simulationEngine.step(1.0);
        System.out.println(train);
        System.out.println(train2);
    }
}

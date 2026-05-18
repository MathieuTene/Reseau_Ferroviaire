package fr.eilco.projet.model;

import java.util.List;

public class TestRobustesseTrain {
    public static void main(String[] args) {
        System.out.println("--- Test 1 : Déplacement simple ---");
        Gare a = new Gare("A", "Gare A", 0, 0);
        Gare b = new Gare("B", "Gare B", 100, 0);
        Segment s1 = new Segment("S1", a, b, 100);
        Itineraire iti1 = new Itineraire(List.of(s1));
        Train t1 = new Train("t1", 50, iti1);
        
        System.out.println(t1);
        t1.avancer(1.0);
        System.out.println(t1 + " (Attendu: s=50)");

        System.out.println("\n--- Test 2 : Passage au segment suivant ---");
        Gare c = new Gare("C", "Gare C", 200, 0);
        Segment s2 = new Segment("S2", b, c, 100);
        Itineraire iti2 = new Itineraire(List.of(s1, s2));
        Train t2 = new Train("t2", 100, iti2);
        
        System.out.println(t2);
        t2.avancer(1.5);
        System.out.println(t2 + " (Attendu: indexSegment=1, s=50)");

        System.out.println("\n--- Test 3 : dt grand (cas critique) ---");
        // Itinéraire de taille 200 (2 segments de 100)
        Train t3 = new Train("t3", 25, iti2); // Vitesse 25, dt 10 -> parcourt 250 unités
        t3.avancer(10.0);
        System.out.println(t3 + " (Attendu: segmentCourant=null, estTermine=true)");
        System.out.println("estTermine() : " + t3.estTermine());

        System.out.println("\n--- Test 4 : plusieurs dt ---");
        Train t4 = new Train("t4", 10, iti2);
        for(int i=0; i<3; i++) {
            t4.avancer(10.0);
            System.out.println("Après dt " + (i+1) + " : " + t4);
        }
    }
}

package fr.eilco.projet.model;

public class TestSignal {
    public static void main(String[] args) {
        Gare a = new Gare("A", "Gare A", 0, 0);
        Aiguillage aiguillage = new Aiguillage("AIG1", 100, 100);
        Gare b = new Gare("B", "Gare B", 200, 200);
        Gare c = new Gare("C", "Gare C", 200, 0);

        Segment s1 = new Segment("S1", aiguillage, b, 100);
        Segment s2 = new Segment("S2", aiguillage, c, 100);
        aiguillage.definirSorties(s1, s2);

        Signal signal = new Signal("SIG1", aiguillage);

        System.out.println("1. Sortie S1 libre. Signal vert ? " + signal.estVert() + " (Attendu: true)");

        System.out.println("2. Réservation de S1 par Train1...");
        s1.reserver("Train1");
        System.out.println("   Signal vert ? " + signal.estVert() + " (Attendu: false)");

        System.out.println("3. Basculement de l'aiguillage vers S2 (S2 est libre)...");
        aiguillage.basculer();
        System.out.println("   Signal vert ? " + signal.estVert() + " (Attendu: true)");

        System.out.println("4. Libération de S1...");
        s1.liberer("Train1");
        System.out.println("5. Basculement de retour vers S1...");
        aiguillage.basculer();
        System.out.println("   Signal vert ? " + signal.estVert() + " (Attendu: true)");
    }
}

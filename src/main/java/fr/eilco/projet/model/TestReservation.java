package fr.eilco.projet.model;

public class TestReservation {
    public static void main(String[] args) {
        Gare a = new Gare("A", "Gare A", 0, 0);
        Gare b = new Gare("B", "Gare B", 100, 0);
        Segment s1 = new Segment("S1", a, b, 100);

        System.out.println("1. Réservation par t1 : " + s1.reserver("t1") + " (Attendu: true)");
        System.out.println("   Réservé par : " + s1.reservePar());

        System.out.println("2. Réservation par t2 : " + s1.reserver("t2") + " (Attendu: false)");
        System.out.println("   Réservé par : " + s1.reservePar());

        System.out.println("3. Libération par t2 (non propriétaire) :");
        s1.liberer("t2");
        System.out.println("   Réservé par : " + s1.reservePar() + " (Attendu: t1)");

        System.out.println("4. Libération par t1 :");
        s1.liberer("t1");
        System.out.println("   Est libre : " + s1.estLibre() + " (Attendu: true)");
        System.out.println("   Réservé par : " + s1.reservePar());
    }
}

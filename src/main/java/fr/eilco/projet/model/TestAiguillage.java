package fr.eilco.projet.model;

public class TestAiguillage {
    public static void main(String[] args) {
        Gare a = new Gare("A", "Gare A", 20, 50);
        Aiguillage aiguillage = new Aiguillage("B", 100, 50);
        Gare c = new Gare("C", "Gare C", 200, 100);
        Gare d = new Gare("D", "Gare D", 200, 0);

        Segment s1 = new Segment("S1", a, aiguillage, 100);
        Segment s2 = new Segment("S2", aiguillage, c, 100);
        Segment s3 = new Segment("S3", aiguillage, d, 100);

        aiguillage.definirSorties(s2, s3);

        System.out.println("Sortie active initiale : " + aiguillage.getSortieActive().getId() + " (Attendu: S2)");
        
        aiguillage.basculer();
        System.out.println("Après basculement 1 : " + aiguillage.getSortieActive().getId() + " (Attendu: S3)");
        
        aiguillage.basculer();
        System.out.println("Après basculement 2 : " + aiguillage.getSortieActive().getId() + " (Attendu: S2)");
    }
}

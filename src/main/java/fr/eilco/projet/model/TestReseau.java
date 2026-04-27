package fr.eilco.projet.model;

public class TestReseau {

    public static void main(String[] args){
        Gare a = new Gare("A", "Gare A", 0, 0);
        Gare b = new Gare("B", "Gare B", 3, 4);
        Segment s = new Segment("S1", a, b, 30);

        System.out.println(s.longueur());
        System.out.println(a.getNom());
        System.out.println(b.getNom());

        Noeud autreExtremite = s.autreExtremite(a);

        System.out.println(((Gare) autreExtremite).getNom() );

        Reseau reseau = new Reseau();
        Gare c = new Gare("C", "Gare C", 650, 420);

        reseau.ajouterNoeud(a);
        reseau.ajouterNoeud(b);
        reseau.ajouterNoeud(c);
        reseau.ajouterSegment(s);

        System.out.println(reseau);
    }
}

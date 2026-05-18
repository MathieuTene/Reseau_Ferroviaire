package fr.eilco.projet.model;

public class Aiguillage extends Noeud {
    private Segment sortie1;
    private Segment sortie2;
    private Segment sortieActive;

    public Aiguillage(String id, double x, double y) {
        super(id, x, y);
    }

    public void definirSorties(Segment s1, Segment s2) {
        this.sortie1 = s1;
        this.sortie2 = s2;
        this.sortieActive = s1; // Par défaut sortie 1
    }

    public void basculer() {
        if (sortieActive == sortie1) {
            sortieActive = sortie2;
        } else {
            sortieActive = sortie1;
        }
    }

    public Segment getSortieActive() {
        return sortieActive;
    }

    public Segment getSortie1() {
        return sortie1;
    }

    public Segment getSortie2() {
        return sortie2;
    }
}

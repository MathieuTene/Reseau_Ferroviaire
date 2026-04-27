package fr.eilco.projet.model;

public class Segment {
    private String id;
    private Noeud extremite1;
    private Noeud extremite2;
    private double vitesseMax;

    public Segment(String id, Noeud extremite1, Noeud extremite2, double vitesseMax) {
        this.id = id;
        this.extremite1 = extremite1;
        this.extremite2 = extremite2;
        this.vitesseMax = vitesseMax;
    }

    public double longueur() {
        double dx = extremite2.getX() - extremite1.getX();
        double dy = extremite2.getY() - extremite1.getY();
        return Math.sqrt(dx * dx + dy * dy);
    }

    public Noeud autreExtremite(Noeud n) {
        if (n == extremite1) {
            return extremite2;
        } else if (n == extremite2) {
            return extremite1;
        }
        return null; // Ou jeter une exception si le noeud n'appartient pas au segment
    }

    public String getId() {
        return id;
    }

    public double getVitesseMax() {
        return vitesseMax;
    }
}

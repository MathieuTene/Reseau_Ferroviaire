package fr.eilco.projet.model;

public class Gare extends Noeud {
    private String nom;

    public Gare(String id, String nom, double x, double y) {
        super(id, x, y);
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    @Override
    public String toString() {
        return "Gare " + getId();
    }
}

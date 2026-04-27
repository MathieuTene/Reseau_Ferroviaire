package fr.eilco.projet.model;

import java.util.ArrayList;
import java.util.List;

public class Reseau {
    private List<Noeud> noeuds;
    private List<Segment> segments;

    public Reseau() {
        this.noeuds = new ArrayList<>();
        this.segments = new ArrayList<>();
    }

    public void ajouterNoeud(Noeud n) {
        noeuds.add(n);
    }

    public void ajouterSegment(Segment s) {
        segments.add(s);
    }

    public List<Noeud> getNoeuds() {
        return noeuds;
    }

    public List<Segment> getSegments() {
        return segments;
    }

    @Override
    public String toString() {
        return "Votre réseau contient :\n" + segments.size() + " segments et " + noeuds.size() + " noeuds";
    }
}

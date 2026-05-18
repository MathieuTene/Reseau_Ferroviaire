package fr.eilco.projet.model;

import java.util.ArrayList;
import java.util.List;

public class Reseau {
    private List<Noeud> noeuds;
    private List<Segment> segments;
    private List<Train> trains;

    public Reseau() {
        this.noeuds = new ArrayList<>();
        this.segments = new ArrayList<>();
        this.trains = new ArrayList<>();
    }

    public void ajouterNoeud(Noeud n) {
        noeuds.add(n);
    }

    public void ajouterSegment(Segment s) {
        segments.add(s);
    }

    public void ajouterTrain(Train t) {
        trains.add(t);
    }

    public List<Noeud> getNoeuds() {
        return noeuds;
    }

    public List<Segment> getSegments() {
        return segments;
    }

    public List<Train> getTrains() {
        return trains;
    }

    @Override
    public String toString() {
        return "Votre réseau contient :\n" + segments.size() + " segment(s) " + noeuds.size() + " noeud(s) et " + trains.size() + " train(s)";
    }
}

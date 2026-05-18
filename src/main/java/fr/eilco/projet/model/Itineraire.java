package fr.eilco.projet.model;

import java.util.List;

public class Itineraire {
    private List<Segment> segments;

    public Itineraire(List<Segment> segments) {
        this.segments = segments;
    }

    public List<Segment> getSegments() {
        return segments;
    }

    public boolean estVide() {
        return segments == null || segments.isEmpty();
    }
}

package fr.eilco.projet.model;

public class Segment implements Reservable {
    private String id;
    private Noeud a;
    private Noeud b;
    private double vitesseMax;
    private String reservePar;

    public Segment(String id, Noeud a, Noeud b, double vitesseMax) {
        this.id = id;
        this.a = a;
        this.b = b;
        this.vitesseMax = vitesseMax;
        this.reservePar = null;
    }

    public double longueur() {
        double dx = b.getX() - a.getX();
        double dy = b.getY() - a.getY();
        return Math.sqrt(dx * dx + dy * dy);
    }

    public Noeud autreExtremite(Noeud n) {
        if (n == a) {
            return b;
        } else if (n == b) {
            return a;
        }
        return null;
    }

    public String getId() {
        return id;
    }

    public double getVitesseMax() {
        return vitesseMax;
    }

    public Noeud getA() {
        return a;
    }

    public Noeud getB() {
        return b;
    }

    @Override
    public boolean reserver(String trainId) {
        if (estLibre() || trainId.equals(reservePar)) {
            this.reservePar = trainId;
            return true;
        }
        return false;
    }

    @Override
    public void liberer(String trainId) {
        if (trainId.equals(reservePar)) {
            this.reservePar = null;
        }
    }

    @Override
    public boolean estLibre() {
        return reservePar == null;
    }

    @Override
    public String reservePar() {
        return reservePar;
    }
}

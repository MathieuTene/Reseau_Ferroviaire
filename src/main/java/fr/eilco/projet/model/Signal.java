package fr.eilco.projet.model;

public class Signal extends Noeud {

    private final Aiguillage aiguillage;

    public Signal(String id, Aiguillage aiguillage) {
        super(id, aiguillage.getX() + 18, aiguillage.getY() - 18); // petit décalage visuel
        this.aiguillage = aiguillage;
    }

    public Aiguillage getAiguillage() { return aiguillage; }

    /** Vert si la sortie active est libre. */
    public boolean estVert() {
        Segment s = aiguillage.getSortieActive();
        return s != null && s.estLibre();
    }
}
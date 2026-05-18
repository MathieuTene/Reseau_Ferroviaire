package fr.eilco.projet.model;

public class Train {
    private String id;
    private double vitesse;
    private Itineraire itineraire;
    private Segment segmentCourant;
    private double s;
    private int indexSegment;
    private double tempsDepart;
    private double tempsCumule = 0;

    public Train(String id, double vitesse, Itineraire itineraire) {
        this(id, vitesse, itineraire, 0.0);
    }

    public Train(String id, double vitesse, Itineraire itineraire, double tempsDepart) {
        this.id = id;
        this.vitesse = vitesse;
        this.itineraire = itineraire;
        this.tempsDepart = tempsDepart;
        this.s = 0.0;
        this.indexSegment = 0;
        
        if (itineraire != null && !itineraire.getSegments().isEmpty()) {
            this.segmentCourant = itineraire.getSegments().get(0);
            // On tente de réserver le premier segment dès le départ
            this.segmentCourant.reserver(id);
        }
    }

    public void avancer(double dt) {
        tempsCumule += dt;
        if (tempsCumule < tempsDepart) {
            return; // Le train n'est pas encore parti
        }

        if (estTermine()) {
            return;
        }

        // Si le train est bloqué en bout de segment (s == longueur), 
        // on tente de passer au suivant avant d'ajouter le mouvement du tick actuel
        if (s >= segmentCourant.longueur()) {
            tenterPassageSuivant(0);
        }

        if (estTermine() || s >= segmentCourant.longueur()) {
            return; // Toujours bloqué ou fini
        }

        s += vitesse * dt;

        // Algorithme Page 5 : "Lorsque s dépasse L = longueur(segmentCourant)"
        while (segmentCourant != null && s >= segmentCourant.longueur()) {
            double reste = s - segmentCourant.longueur();
            if (!tenterPassageSuivant(reste)) {
                break; // Bloqué, on sort de la boucle
            }
        }
    }

    private boolean tenterPassageSuivant(double reste) {
        int nextIndex = indexSegment + 1;
        if (nextIndex < itineraire.getSegments().size()) {
            Segment suivant = itineraire.getSegments().get(nextIndex);
            
            // 3. on tente de réserver le segment suivant
            if (suivant.reserver(id)) {
                // si OK libérer l'ancien segment
                segmentCourant.liberer(id);
                segmentCourant = suivant;
                indexSegment = nextIndex;
                s = reste;
                return true; // continuer (boucle)
            } else {
                // si pas ok : s = L (on s'arrête pile à la fin)
                s = segmentCourant.longueur();
                return false; // sort (train en attente)
            }
        } else {
            // Fin de l'itinéraire
            segmentCourant.liberer(id);
            segmentCourant = null;
            s = 0.0;
            return false;
        }
    }

    public boolean estTermine() {
        return segmentCourant == null;
    }

    @Override
    public String toString() {
        String segmentId = (segmentCourant != null) ? segmentCourant.getId() : "null";
        return "Train{id='" + id + "', vitesse=" + vitesse + ", indexSegment=" + indexSegment + 
               ", segmentCourant=" + segmentId + ", s=" + s + "}";
    }

    public String getId() { return id; }
    public double getVitesse() { return vitesse; }
    public Segment getSegmentCourant() { return segmentCourant; }
    public double getS() { return s; }
}

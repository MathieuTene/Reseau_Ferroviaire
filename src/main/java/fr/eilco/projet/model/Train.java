package fr.eilco.projet.model;

public class Train {
    private String id;
    private double vitesse;
    private Itineraire itineraire;
    private Segment segmentCourant;
    private double s;
    private int indexSegment;

    public Train(String id, double vitesse, Itineraire itineraire) {
        this.id = id;
        this.vitesse = vitesse;
        this.itineraire = itineraire;
        this.s = 0.0;
        this.indexSegment = 0;
        
        if (itineraire != null && !itineraire.getSegments().isEmpty()) {
            Segment first = itineraire.getSegments().get(0);
            if (first.reserver(id)) {
                this.segmentCourant = first;
            } else {
                // Si on ne peut pas réserver le premier segment, le train attend au début
                this.segmentCourant = first;
                this.vitesse = 0; // On pourrait gérer ça autrement, mais pour l'instant...
            }
        }
    }

    public void avancer(double dt) {
        if (estTermine()) {
            return;
        }

        // Si le train était arrêté car bloqué, on réessaie d'avancer s'il a de la vitesse
        // Dans ce modèle simplifié, la vitesse est fixe.
        
        s += vitesse * dt;

        while (segmentCourant != null && s >= segmentCourant.longueur()) {
            double reste = s - segmentCourant.longueur();
            
            // Tentative de passage au segment suivant
            int nextIndex = indexSegment + 1;
            if (nextIndex < itineraire.getSegments().size()) {
                Segment suivant = itineraire.getSegments().get(nextIndex);
                
                if (suivant.reserver(id)) {
                    // Libération de l'ancien segment
                    segmentCourant.liberer(id);
                    
                    // Passage au suivant
                    segmentCourant = suivant;
                    indexSegment = nextIndex;
                    s = reste;
                    // On continue la boucle pour voir si on dépasse aussi le suivant
                } else {
                    // Bloqué ! On s'arrête pile à la fin du segment actuel
                    s = segmentCourant.longueur();
                    break; 
                }
            } else {
                // Fin de l'itinéraire
                segmentCourant.liberer(id);
                segmentCourant = null;
                s = 0.0;
                break;
            }
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

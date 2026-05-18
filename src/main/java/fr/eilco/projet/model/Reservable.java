package fr.eilco.projet.model;

public interface Reservable {
    boolean reserver(String trainId);
    void liberer(String trainId);
    boolean estLibre();
    String reservePar();
}

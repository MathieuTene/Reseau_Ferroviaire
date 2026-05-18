package fr.eilco.projet;

import fr.eilco.projet.model.*;
import fr.eilco.projet.sim.SimulationEngine;
import fr.eilco.projet.ui.ReseauView;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.List;

public class MainApp extends Application {

    private Reseau reseau;
    private SimulationEngine engine;
    private ReseauView view;
    private long lastTime = 0;

    @Override
    public void start(Stage stage) {
        initModel();

        view = new ReseauView(reseau);
        BorderPane root = new BorderPane();
        root.setCenter(view);

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (lastTime == 0) {
                    lastTime = now;
                    return;
                }
                double dt = (now - lastTime) / 1_000_000_000.0;
                lastTime = now;

                engine.step(dt);
                view.redraw();
            }
        };
        timer.start();

        Scene scene = new Scene(root, 1000, 700);
        stage.setTitle("Simulateur de Réseau Ferroviaire - Séance 3");
        stage.setScene(scene);
        stage.show();
    }

    private void initModel() {
        reseau = new Reseau();
        
        Gare g1 = new Gare("G1", "Dunkerque", 50, 300);
        Aiguillage a1 = new Aiguillage("A1", 250, 300);
        Gare g2 = new Gare("G2", "Lille", 600, 150);
        Gare g3 = new Gare("G3", "Arras", 600, 450);
        
        reseau.ajouterNoeud(g1);
        reseau.ajouterNoeud(a1);
        reseau.ajouterNoeud(g2);
        reseau.ajouterNoeud(g3);
        
        Segment s1 = new Segment("S1", g1, a1, 100);
        Segment s2 = new Segment("S2", a1, g2, 120);
        Segment s3 = new Segment("S3", a1, g3, 80);
        
        reseau.ajouterSegment(s1);
        reseau.ajouterSegment(s2);
        reseau.ajouterSegment(s3);
        
        a1.definirSorties(s2, s3);
        
        Signal sig1 = new Signal("SIG1", a1);
        reseau.ajouterNoeud(sig1);
        
        // Train 1 vers Lille
        Itineraire itin1 = new Itineraire(List.of(s1, s2));
        Train t1 = new Train("Train A", 40, itin1);
        
        // Train 2 vers Arras (mais devra attendre que s1 se libère si lancé en même temps)
        Itineraire itin2 = new Itineraire(List.of(s1, s3));
        Train t2 = new Train("Train B", 30, itin2);
        
        reseau.ajouterTrain(t1);
        reseau.ajouterTrain(t2);
        
        engine = new SimulationEngine(reseau);
    }

    public static void main(String[] args) {
        launch(args);
    }
}

package fr.eilco.projet.ui;

import fr.eilco.projet.model.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;

public class ReseauView extends Pane {
    private Reseau reseau;
    private Canvas canvas;
    private Image locoImg;
    private Image railImg;

    public ReseauView(Reseau reseau) {
        this.reseau = reseau;
        this.canvas = new Canvas(900, 600);
        this.getChildren().add(canvas);
        
        canvas.widthProperty().bind(this.widthProperty());
        canvas.heightProperty().bind(this.heightProperty());
        
        canvas.widthProperty().addListener(e -> redraw());
        canvas.heightProperty().addListener(e -> redraw());

        canvas.setOnMouseClicked(event -> {
            double mouseX = event.getX();
            double mouseY = event.getY();
            
            for (Noeud n : reseau.getNoeuds()) {
                if (n instanceof Aiguillage) {
                    double dx = mouseX - n.getX();
                    double dy = mouseY - n.getY();
                    if (Math.sqrt(dx*dx + dy*dy) < 15) {
                        ((Aiguillage) n).basculer();
                        redraw();
                        break;
                    }
                }
            }
        });

        locoImg = loadImageOrNull("/images/loco.png");
        railImg = loadImageOrNull("/images/rail_transparent.png");
    }

    private Image loadImageOrNull(String path) {
        try {
            var is = getClass().getResourceAsStream(path);
            if (is == null) return null;
            return new Image(is);
        } catch (Exception e) {
            return null;
        }
    }

    public void redraw() {
        GraphicsContext g = canvas.getGraphicsContext2D();
        g.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        // 1. Dessiner les segments (rails)
        for (Segment s : reseau.getSegments()) {
            drawSegment(g, s);
        }

        // 2. Dessiner les noeuds (gares, aiguillages, signaux)
        for (Noeud n : reseau.getNoeuds()) {
            if (n instanceof Gare) {
                g.setFill(Color.RED);
                g.fillOval(n.getX() - 10, n.getY() - 10, 20, 20);
                g.setFill(Color.BLACK);
                g.fillText(((Gare) n).getNom(), n.getX() + 12, n.getY() + 5);
            } else if (n instanceof Signal) {
                Signal s = (Signal) n;
                g.setFill(s.estVert() ? Color.GREEN : Color.RED);
                g.fillOval(n.getX() - 5, n.getY() - 5, 10, 10);
            } else if (n instanceof Aiguillage) {
                g.setFill(Color.ORANGE);
                g.fillRect(n.getX() - 6, n.getY() - 6, 12, 12);
            } else {
                g.setFill(Color.BLACK);
                g.fillOval(n.getX() - 3, n.getY() - 3, 6, 6);
            }
        }

        // 3. Dessiner les trains
        for (Train t : reseau.getTrains()) {
            if (!t.estTermine()) {
                drawTrain(g, t);
            }
        }
    }

    private void drawSegment(GraphicsContext g, Segment s) {
        Noeud n1 = s.getA();
        Noeud n2 = s.getB();
        
        if (railImg != null) {
            // Dessin avec sprite répété (V3)
            double dist = s.longueur();
            double dx = n2.getX() - n1.getX();
            double dy = n2.getY() - n1.getY();
            double angle = Math.toDegrees(Math.atan2(dy, dx));
            
            g.save();
            g.translate(n1.getX(), n1.getY());
            g.rotate(angle);
            
            double railW = 20; // à ajuster
            double railH = 10;
            for (double x = 0; x < dist; x += railW) {
                g.drawImage(railImg, x, -railH/2, railW, railH);
            }
            g.restore();
        } else {
            g.setStroke(Color.GRAY);
            g.setLineWidth(3);
            g.strokeLine(n1.getX(), n1.getY(), n2.getX(), n2.getY());
        }
        
        // Afficher qui réserve le segment (pour le débug)
        if (s.reservePar() != null) {
            g.setFill(Color.BLUE);
            double midX = (n1.getX() + n2.getX()) / 2;
            double midY = (n1.getY() + n2.getY()) / 2;
            g.fillText("[" + s.reservePar() + "]", midX, midY - 5);
        }
    }

    private void drawTrain(GraphicsContext g, Train t) {
        Segment s = t.getSegmentCourant();
        double L = s.longueur();
        double u = (L <= 1e-6) ? 0 : (t.getS() / L);
        
        double x = s.getA().getX() * (1 - u) + s.getB().getX() * u;
        double y = s.getA().getY() * (1 - u) + s.getB().getY() * u;
        
        if (locoImg != null) {
            double dx = s.getB().getX() - s.getA().getX();
            double dy = s.getB().getY() - s.getA().getY();
            double angle = Math.toDegrees(Math.atan2(dy, dx));
            
            g.save();
            g.translate(x, y);
            g.rotate(angle);
            
            double scale = 0.25;
            double w = locoImg.getWidth() * scale;
            double h = locoImg.getHeight() * scale;
            g.drawImage(locoImg, -w/2, -h/2, w, h);
            g.restore();
        } else {
            g.setFill(Color.BLUE);
            g.fillRect(x - 8, y - 8, 16, 16);
        }
        g.setFill(Color.BLACK);
        g.fillText(t.getId(), x + 10, y - 10);
    }
}

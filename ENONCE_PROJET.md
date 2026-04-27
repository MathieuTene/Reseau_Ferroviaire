# Projet (≈ 15h) — Simulateur de réseau ferroviaire (Java + JavaFX)

## Contexte
Vous développez un mini-simulateur de circulation ferroviaire.  
L’application affiche un réseau simplifié (voies + nœuds) et fait circuler un ou plusieurs trains.

Le TP vise à pratiquer la POO :
- classes, encapsulation, héritage, polymorphisme
- interfaces
- exceptions métier
- collections
- (optionnel) algorithme de plus court chemin (Dijkstra) pour les plus rapides

> **Important :** on ne vous demande pas une architecture MVC complète.  
> On vise une séparation simple : **modèle** (POO pure) / **simulation** (tick) / **UI JavaFX** (affichage + événements).

---
## Rendu attendu
À la fin, votre programme doit permettre :
1. d’afficher un réseau (au minimum un petit réseau en Y)
2. de créer un ou plusieurs trains
3. de lancer/arrêter la simulation
4. d’interagir avec au moins :
   - un **aiguillage** (changer la direction)
   - un **signal** (rouge/vert)
5. d’éviter les collisions via une règle simple de sécurité (réservation/occupation)

---
## Contraintes
- Java 17+.
- JavaFX pour l’affichage (Canvas ou Pane).
- Images : uniquement libres de droit / licence compatible (ou dessin en formes JavaFX).

